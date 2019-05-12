package com.lesson3.hw.service;

import com.lesson3.hw.DAO.FileDAO;
import com.lesson3.hw.entity.File;
import com.lesson3.hw.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    private FileDAO fileDAO;

    @Autowired
    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public File put(Storage storage, File file) throws Exception {
        validateNewFile(storage, file);

        file.setStorage(storage);
        return fileDAO.update(file);
    }

    public void deleteFromStorage(Storage storage, File file) throws Exception {
        if (storage.getId() != file.getStorage().getId()) {
            throw new Exception("Error: Storage(id:" + storage.getId() + ") does not contain file(id:" + file.getId() + ")");
        }

        file.setStorage(null);
        fileDAO.update(file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        putAll(storageTo, storageFrom.getFiles());
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = fileDAO.findById(id);
        if (storageFrom.getId() != file.getStorage().getId()) {
            throw new Exception("Error: Storage(id:" + storageFrom.getId() + ") does not contain file(id:" + file.getId() + ")");
        }

        put(storageTo, file);
    }

    public File save(File file) throws Exception {
        return fileDAO.save(file);
    }

    public void delete(long id) throws Exception {
        fileDAO.delete(id);
    }

    public File update(File file) throws Exception {
        return fileDAO.update(file);
    }

    public File findById(long id) throws Exception {
        return fileDAO.findById(id);
    }

    private void putAll(Storage storage, List<File> files) throws Exception {
        validateFilesList(storage, files);

        for (File file : files) {
            file.setStorage(storage);
        }
        fileDAO.updateFilesList(files);
    }

    private static void validateFilesList(Storage storage, List<File> files) throws Exception {
        long filesSize = 0;
        for (File file : files) {
            if (!storage.isSupportedFormat(file.getFormat())) {
                throw new Exception("Error: File(id: " + file.getId() + ") has not supported format '" + file.getFormat()
                        + " in Storage(id: " + storage.getId() + ")");
            }

            filesSize += file.getSize();
        }

        if (getFreeStorageSpace(storage) < filesSize) {
            throw new Exception("Error: not enough space in Storage(id: " + storage.getId() + ") for new files");
        }
    }

    private static void validateNewFile(Storage storage, File file) throws Exception {
        if (!storage.isSupportedFormat(file.getFormat())) {
            throw new Exception("Error: File(id: " + file.getId() + ") has not supported format '" + file.getFormat()
                    + " in Storage(id: " + storage.getId() + ")");
        }

        if (getFreeStorageSpace(storage) < file.getSize()) {
            throw new Exception("Error: not enough space in Storage(id: " + storage.getId() + ") for File(id: " + file.getId() + ")");
        }
    }

    private static long getFreeStorageSpace(Storage storage) throws Exception {
        long usedSpace = 0;
        for (File file : storage.getFiles()) {
            usedSpace += file.getSize();
        }

        return storage.getStorageMaxSize() - usedSpace;
    }
}

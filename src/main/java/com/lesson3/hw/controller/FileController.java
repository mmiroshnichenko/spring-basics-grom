//package com.lesson3.hw.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.lesson3.hw.entity.File;
//import com.lesson3.hw.service.FileService;
//import com.lesson3.hw.service.StorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//
//@Controller
//public class FileController {
//    private FileService fileService;
//    private StorageService storageService;
//
//    @Autowired
//    public FileController(FileService fileService, StorageService storageService) {
//        this.fileService = fileService;
//        this.storageService = storageService;
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/put-file", params = {"storageId", "fileId"}, produces = "text/plain")
//    public @ResponseBody
//    String putFileInStorage(@RequestParam(value = "storageId") String storageId, @RequestParam(value = "fileId") String fileId) {
//        try {
//            fileService.put(
//                storageService.findById(Long.parseLong(storageId)),
//                fileService.findById(Long.parseLong(fileId)));
//
//            return "File was put in storage";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/delete-file", params = {"storageId", "fileId"}, produces = "text/plain")
//    public @ResponseBody
//    String deleteFileFromStorage(@RequestParam(value = "storageId") String storageId, @RequestParam(value = "fileId") String fileId) {
//        try {
//            fileService.deleteFromStorage(
//                    storageService.findById(Long.parseLong(storageId)),
//                    fileService.findById(Long.parseLong(fileId))
//            );
//
//            return "File was deleted from storage";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/transfer-all", params = {"storageFromId", "storageToId"}, produces = "text/plain")
//    public @ResponseBody
//    String transferAll(@RequestParam(value = "storageFromId") String storageFromId, @RequestParam(value = "storageToId") String storageToId) {
//        try {
//            fileService.transferAll(
//                storageService.findById(Long.parseLong(storageFromId)),
//                storageService.findById(Long.parseLong(storageToId))
//            );
//
//            return "Files were transfered";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/transfer-file", params = {"storageFromId", "storageToId", "fileId"}, produces = "text/plain")
//    public @ResponseBody
//    String transferFile(
//            @RequestParam(value = "storageFromId") String storageFromId,
//            @RequestParam(value = "storageToId") String storageToId,
//            @RequestParam(value = "fileId") String fileId) {
//        try {
//            fileService.transferFile(
//                    storageService.findById(Long.parseLong(storageFromId)),
//                    storageService.findById(Long.parseLong(storageToId)),
//                    Long.parseLong(fileId)
//            );
//
//            return "File was transfered";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/file", params = {"id"}, produces = "text/plain")
//    public @ResponseBody
//    String findById(@RequestParam(value = "id") String id) {
//        try {
//            return fileService.findById(Long.parseLong(id)).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.POST, value = "/file", produces = "text/plain")
//    public @ResponseBody
//    String save(HttpServletRequest req) {
//        try(ServletInputStream stream = req.getInputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            File file = new File();
//            file = objectMapper.readValue(stream, file.getClass());
//            return fileService.save(file).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/file", produces = "text/plain")
//    public @ResponseBody
//    String update(HttpServletRequest req) {
//        try(ServletInputStream stream = req.getInputStream()) {
//            ObjectMapper objectMapper = new ObjectMapper();
//            File file = new File();
//            file = objectMapper.readValue(req.getInputStream(), file.getClass());
//            return fileService.update(file).toString();
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/file", params = {"id"}, produces = "text/plain")
//    public @ResponseBody
//    String delete(@RequestParam(value = "id") String id) {
//        try {
//            fileService.delete(Long.parseLong(id));
//
//            return "File was deleted";
//        } catch (Exception e) {
//            return e.getMessage();
//        }
//    }
//}

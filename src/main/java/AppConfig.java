import com.lesson2.hw1.Route;
import com.lesson2.hw1.Service;
import com.lesson2.hw1.Step;
import com.lesson2.hw2.DAO.ItemDAO;
import com.lesson2.hw2.service.ItemService;
import com.lesson2.less.OrderDAO;
import com.lesson2.less.OrderService;
import com.lesson3.hw.DAO.FileDAO;
import com.lesson3.hw.DAO.StorageDAO;
import com.lesson3.hw.service.FileService;
import com.lesson3.hw.service.StorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OrderService orderService() {
        return new OrderService();
    }

    @Bean
    public OrderDAO orderDAO() {
        return new OrderDAO();
    }

    @Bean
    public Step step() {
        return new Step();
    }

    @Bean
    public Service service() {
        return new Service();
    }

    @Bean
    public Route route() {
        return new Route();
    }

    @Bean
    public ItemService itemService() {
        return new ItemService();
    }

    @Bean
    public ItemDAO itemDAO() {
        return new ItemDAO();
    }

    @Bean
    public StorageDAO storageDAO() {
        return new StorageDAO();
    }

    @Bean
    public StorageService storageService() {
        return new StorageService(storageDAO());
    }

    public FileDAO fileDAO() {
        return new FileDAO();
    }

    public FileService fileService() {
        return new FileService(fileDAO());
    }
}

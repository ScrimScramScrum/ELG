package Controllers;




import java.io.IOException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import springmvc.domain.User;
import springmvc.repository.ClassRepoDB;
import springmvc.repository.MultiChoiceRepoMock;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.MultipleChoiceRepoDB;
import springmvc.repository.PersonRepoDB;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleGameRepoDB;
import springmvc.repository.ResembleGameRepoMock;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResembleTaskRepoDB;
import springmvc.repository.ResembleTaskRepoMock;
import springmvc.repository.ResultRepo;
import springmvc.repository.ResultRepoDB;
import springmvc.repository.ResultRepoMock;
import springmvc.service.*;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceImpl;
import springmvc.service.GameListServiceMock;
import springmvc.service.MultiChoiceService;
import springmvc.service.ResembleTaskService;


@Configuration
public class ConfigTest{  
    @Bean
    public DataSource dataSource(){
        String url = "jdbc:derby://localhost:1527/ELGDB";
        String username = "ELGUSER";
        String password = "ELGERBEST";
        BasicDataSource bds = new BasicDataSource(); 
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setInitialSize(100);
        return bds; 
    }
    
    @Bean 
    public ResembleTaskRepo resembleTaskRepo(){
        return new ResembleTaskRepoMock(); 
    }
    
    @Bean
    public ResembleTaskService resembleTaskService(){
        return new ResembleTaskService(); 
    }
    
    @Bean
    public MultiChoiceRepository multiChoiceRepository(){
        return new MultiChoiceRepoMock();
    }
    
    @Bean
    public MultiChoiceService multiChoiceService(){
        return new MultiChoiceService();
    }
    
    @Bean
    public GameListService gameListService(){
        return new GameListServiceMock(); 
    }
    
    //KOPIERES
    @Bean
    public PersonService personService(){
        return new PersonServiceTesting();
    }
    
    //KOPIERES     
    @Bean
    public LoginService loginService(){
        return new LoginService();
    }    
       
    @Bean
    public PersonRepoDB personRepo(){
        return new PersonRepoDB();
    }
    @Bean 
     public ResembleGameRepo resembleGameRepo(){
         return new ResembleGameRepoMock(); 
     }
     
    @Bean
    public ResultService resultService(){
        return new ResultService();
    }
    
    @Bean
    public ResultRepo resultRepo(){
        return new ResultRepoMock();
    }
    
    @Bean
    public ClassRepoDB classRepoDb(){
        return new ClassRepoDB();
    } 
    
    @Bean
    public ClassService classService(){
        return new ClassService();
    }
}

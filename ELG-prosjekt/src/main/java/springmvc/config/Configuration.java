
package springmvc.config;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
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



@Controller
@EnableWebMvc  // mvc annotation
@ComponentScan(basePackages = {"springmvc.controller"}) // package containing the controllers
public class Configuration extends WebMvcConfigurationSupport {

    @Bean
    public TilesConfigurer tilesConfigurer() {
        return new TilesConfigurer();
    }

    @Bean
    public TilesViewResolver tilesViewResolver() {
        TilesViewResolver tilesViewResolver = new TilesViewResolver();
        //tilesViewResolver.setOrder(0);
        return tilesViewResolver;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename("/WEB-INF/messages");
        return source;
    }

    // equivalents for <mvc:resources/> tags
    // Hvor finnes statisk ressurser som bilder/ css/ js osv.
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }

    @Override
    @Bean
    public HandlerMapping resourceHandlerMapping() {
        AbstractHandlerMapping handlerMapping = (AbstractHandlerMapping) super.resourceHandlerMapping();
        handlerMapping.setOrder(-1);
        return handlerMapping;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
  
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
        return new ResembleTaskRepoDB(); 
    }
    
    @Bean
    public ResembleTaskService resembleTaskService(){
        return new ResembleTaskService(); 
    }
    
    @Bean
    public MultiChoiceRepository multiChoiceRepository(){
        return new MultipleChoiceRepoDB();
    }
    
    @Bean
    public MultiChoiceService multiChoiceService(){
        return new MultiChoiceService();
    }
    
    @Bean
    public GameListService gameListService(){
        return new GameListServiceImpl(); 
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
         return new ResembleGameRepoDB(); 
     }
     
    @Bean
    public ResultService resultService(){
        return new ResultService();
    }
    
    @Bean
    public ResultRepo resultRepo(){
        return new ResultRepoDB();
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

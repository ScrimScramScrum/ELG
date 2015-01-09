
package springmvc.config;

import java.sql.Connection;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Controller;

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
        String url = "jdbc:derby://localhost:1527/su1_test";
        String username = "t";
        String password = "t";
        BasicDataSource bds = new BasicDataSource(); 
        bds.setUrl(url);
        bds.setUsername(username);
        bds.setPassword(password);
        bds.setInitialSize(100);
        return bds; 
    }
}

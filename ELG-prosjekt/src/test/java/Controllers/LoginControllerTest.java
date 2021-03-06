package Controllers;

import org.springframework.context.MessageSource;
import static org.mockito.Mockito.*;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springmvc.controller.LoginController;
import springmvc.controller.MainController;
import springmvc.domain.Person;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResultRepo;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceMock;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResultService;
import springmvc.ui.SendNewPassword;

public class LoginControllerTest {
    private MockMvc mockMvc;
    private MockServletContext servletContext;
    
    
    @Mock
    PersonService personService; 
    @InjectMocks
    private LoginController loginController; 
    
    public LoginControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
 
    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/tiles/");
        viewResolver.setSuffix(".jsp");
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testLogin() throws Exception{
         this.mockMvc.perform(get("/login")).andExpect(status().isOk()).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testFirstLogin() throws Exception{
         this.mockMvc.perform(get("/firstLogin")).andExpect(status().isOk()).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testForgotPasswordFromLogin() throws Exception{
        this.mockMvc.perform(get("/forgotPasswordFromLogin")).andExpect(status().isOk()).andExpect(view().name("forgotPasswordFromLogin"));
    }
    
    @Test
    public void testSendNewPassword() throws Exception{
        this.mockMvc.perform(get("/sendNewPassword")).andExpect(status().isOk()).andExpect(view().name("forGotPasswordFromLogin"));
    }
    
     @Test
    public void testSendNewPasswordTrue() throws Exception{
        when(personService.getPerson(any(String.class))).thenReturn(new Person()); 
        // when(personService.generateNewPassword(any(Person.class))).thenReturn(true); 
       // when(personService.generateNewPassword(any(Person.class))).thenReturn(true); 
        this.mockMvc.perform(get("/sendNewPassword")).andExpect(status().isOk()).andExpect(view().name("forGotPasswordFromLogin"));
    }
    
    @Test
    public void testSendNewPwWithError() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession();
        SendNewPassword pw = new SendNewPassword(); 
       // pw.setEmail(null);
        //mockHttpSession.setAttribute("sendNewPassword", pw);
        this.mockMvc.perform(post("/sendNewPassword")
                .param("email", ""))
                //.session(mockHttpSession))
                .andExpect(model().attributeHasFieldErrors("sendNewPassword", "email"));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import org.springframework.context.MessageSource;
import static org.mockito.Mockito.*;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springmvc.controller.AdministrateController;
import springmvc.controller.MainController;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.Login;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResultRepo;
import springmvc.service.ClassService;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceMock;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResultService;
import springmvc.ui.AddNewClassId;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class AdministrateControllerTest {
    private MockMvc mockMvc;    
    
    @Mock
    private PersonService personService;
    
    @Mock
    private ClassService classService;
    @InjectMocks
    private AdministrateController administrateController; 
    
    public AdministrateControllerTest() {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(administrateController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAdminAccountAsGuest() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("GUEST");
        mockHttpSession.setAttribute("user", user); 
        this.mockMvc.perform(get("/administrateAccount").session(mockHttpSession))
                .andExpect(view().name("result")); 
    }
    
    @Test
    public void testAdministrateAccountNullUser() throws Exception{
        this.mockMvc.perform(get("/administrateAccount"))
                .andExpect(view().name("firstLogin")); 
    }
    
    @Test
    public void testAdministrateAccountValidUser() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        this.mockMvc.perform(get("/administrateAccount").session(mockHttpSession))
                .andExpect(view().name("administrateAccount")); 
    }

    @Test
    public void testChangePassWithValidUser() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        Person person = new Person(); 
        when(personService.getPerson(any(String.class))).thenReturn(person); 
        when(personService.changePassword(any(Person.class), any(String.class), any(String.class), any(String.class))).thenReturn(true); 
        this.mockMvc.perform(post("/changePassword")
                .param("oldPw", "asdasdasd")
                .param("newPw", "asdasdasdasd")
                .param("confirmPw", "asdasdasdasd")
                .session(mockHttpSession))
                .andExpect(model().attribute("changedPassword", "Passordet er endret."))
                .andExpect(view().name("administrateAccount"));
                
    }
    
    @Test
    public void testChangePassWithWrongPass() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User();        
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        when(personService.getPerson(any(String.class))).thenReturn(new Person()); 
        when(personService.changePassword(any(Person.class), any(String.class), any(String.class), any(String.class))).thenReturn(false); 
        this.mockMvc.perform(post("/changePassword")
                .param("oldPw", "asdasdasd")
                .param("newPw", "asdasdasdasd")
                .param("confirmPw", "asdasdasdasd")
                .session(mockHttpSession))
                .andExpect(model().attribute("changedPassword", "Feil. Husk at passordet må være lengre enn 8 tegn."))
                .andExpect(model().attribute("chooseSite", 1))
                .andExpect(view().name("administrateAccount"));
    }
    
     @Test
    public void testAddClassIdValidInfo() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        
        when(personService.setClassId(any(Person.class), any(String.class))).thenReturn(true); 
        when(personService.getPerson(any(String.class))).thenReturn(new Person()); 
        when(personService.changePassword(any(Person.class), any(String.class), any(String.class), any(String.class))).thenReturn(false); 
        this.mockMvc.perform(post("/addClassId")
                .param("classId", "klasse 1")
                .session(mockHttpSession))
                .andExpect(model().attribute("NewClassMessage", "Du er nå registrert i klasse: " + ancid.getClassId()))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testAddClassIdInValidInfo() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        
        when(personService.setClassId(any(Person.class), any(String.class))).thenReturn(false); 
        when(personService.getPerson(any(String.class))).thenReturn(new Person()); 
        this.mockMvc.perform(post("/addClassId")
                .param("classId", "klasse 1")
                .session(mockHttpSession))
                .andExpect(model().attribute("chooseSite", 2))
                .andExpect(model().attribute("NewClassMessage", "Feil. Klassen eksisterer ikke. "))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testMakeAdminValid() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        Person person = new Person(); 
        
        when(personService.setClassId(any(Person.class), any(String.class))).thenReturn(false); 
        when(personService.getPerson(any(String.class))).thenReturn(person);
        when(personService.makeAdmin(person, "123")).thenReturn(true); 
        this.mockMvc.perform(post("/makeNewAdmin")
                .param("makeAdminPw", "123")
                .session(mockHttpSession))
                .andExpect(model().attribute("makeAdminMessage",  "Du har nå admin-rettigheter. "))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testMakeAdminInValid() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        when(personService.makeAdmin(any(Person.class), any(String.class))).thenReturn(false); 
        this.mockMvc.perform(post("/makeNewAdmin")
                .session(mockHttpSession))
                .andExpect(model().attribute("chooseSite",  3))
                .andExpect(model().attribute("makeAdminMessage", "Feil. Administrator-passordet var ikke riktig. "))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testMakeClassValid() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        when(personService.getPerson(any(String.class))).thenReturn(new Person());
        when(personService.makeAdmin(any(Person.class), any(String.class))).thenReturn(false); 
        when(classService.registrateNewClassId("klasse 1")).thenReturn(true); 
        this.mockMvc.perform(post("/makeClass")
                .param("classId", "klasse 1")
                .session(mockHttpSession))
                .andExpect(model().attribute("makeClassMessage",  "Ny klasse ble registrert.  "))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testMakeClassInValid() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("Hei@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        AddNewClassId ancid = new AddNewClassId(); 
        ancid.setClassId("klasse 1");
        when(personService.getPerson(any(String.class))).thenReturn(new Person());
        when(personService.makeAdmin(any(Person.class), any(String.class))).thenReturn(false); 
        when(classService.registrateNewClassId("klasse 1")).thenReturn(false); 
        this.mockMvc.perform(post("/makeClass")
                .param("classId", "klasse 1")
                .session(mockHttpSession))
                .andExpect(model().attribute("chooseSite",  4))
                .andExpect(model().attribute("makeClassMessage",  "Feil, Klassen finnes fra før eller .  "))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testChooseAdministrateAsGuest() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("GUEST");
        mockHttpSession.setAttribute("user", user); 
        this.mockMvc.perform(post("/chooseAdministrateFunction")
                .param("chooseId", "3")
                .session(mockHttpSession))
                .andExpect(model().attribute("chooseSite",  5))
                .andExpect(view().name("administrateAccount"));
    }
    
    @Test
    public void testChooseAdministrateAsRegUser() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("somemail@gmail.com");
        mockHttpSession.setAttribute("user", user); 
        this.mockMvc.perform(post("/chooseAdministrateFunction")
                .param("chooseId", "3")
                .session(mockHttpSession))
                .andExpect(model().attribute("chooseSite",  3))
                .andExpect(view().name("administrateAccount"));
    }
    
}
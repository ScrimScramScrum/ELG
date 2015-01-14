package JUnit;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springmvc.controller.PersonController;
import springmvc.domain.Person;
import springmvc.service.PersonService;

/**
 *
 * @author Jorgen
 */
public class PersonControllerTest {
    
    private MockMvc mockMvc; 
    
    @Mock
    private PersonService personService;
    
    @InjectMocks
    private PersonController personController; 
    
    public PersonControllerTest() {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(personController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testNewPersonFromLogin() throws Exception{
        this.mockMvc.perform(get("/newPersonFromLogin")).andExpect(view().name("newPersonFromLogin"));
    }
    
    @Test
    public void testNewPersonFromLoginPost() throws Exception{
        when(personService.registrerPerson(any(Person.class))).thenReturn(true); 
        this.mockMvc.perform(post("/newPersonFromLogin")
                            .param("hei", "hei")).andExpect(status().isOk()).andExpect(view().name("firstLogin"));
    }
    
     @Test
    public void testNewPersonFromLoginPostFalse() throws Exception{
        when(personService.registrerPerson(any(Person.class))).thenReturn(false); 
        this.mockMvc.perform(post("/newPersonFromLogin")
                            .param("hei", "hei")).andExpect(status().isOk()).andExpect(view().name("newPersonFromLogin"));
    }
}

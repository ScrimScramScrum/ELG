/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springmvc.controller.MultiChoiceController;
import springmvc.domain.MultiChoice;
import springmvc.repository.MultiChoiceRepoMock;
import springmvc.service.MultiChoiceService;
import springmvc.service.ResultService;


public class MultiChoiceControllerTest {
    private MockMvc mockMvc; 
    
    
    
    @Mock
    private MultiChoiceService s;
    
    @Mock
    private ResultService r;
    
    @InjectMocks
    private MultiChoiceController multiChoiceController; 
    
    public MultiChoiceControllerTest() {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(multiChoiceController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testShowMultiChoice() throws Exception{
        when(s.getMultiChoice("HTML spill")).thenReturn(new MultiChoice()); 
        this.mockMvc.perform(post("/multi")
                .param("gamename", "HTML spill"))
                .andExpect(status().isOk())
                .andExpect(view().name("multichoice"));
    }
    
    
    //not working due to session attributes - will be fixed
    @Test
    public void testNextTask() throws Exception{
        MultiChoiceRepoMock repo = new MultiChoiceRepoMock(); 
        MultiChoice mc = repo.getMultiChoice("Spill 1"); 
        this.mockMvc.perform(post("/nextTask")
                .param("spillet", mc.getName()))
                .andExpect(status().isOk())
                .andExpect(view().name("multichoice"));
    }
    
}

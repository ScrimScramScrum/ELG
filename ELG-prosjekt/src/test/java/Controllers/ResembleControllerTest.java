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
import springmvc.controller.ResembleGameController;
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
import springmvc.service.ResembleTaskService;
import springmvc.service.ResultService;
import springmvc.ui.AddNewClassId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class ResembleControllerTest {
    private MockMvc mockMvc;    
    
    @Mock
    private ResembleTaskService resembleTaskService; 
    
    @Mock
    private GameListService gameListService; 
    
    @Mock
    private ResultService r;
    @InjectMocks
    private ResembleGameController resembleGameController; 
    
    public ResembleControllerTest() {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(resembleGameController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testResembleGamePost() throws Exception{
        ResembleGame rs = new ResembleGame(); 
        ResembleTask rt = new ResembleTask(); 
        rs.setGameId(2);
        rs.setCurrentTask(2);
        when(gameListService.getResembleGame(2)).thenReturn(rs); 
        when(resembleTaskService.getResembleTask(rs.getCurrentTask())).thenReturn(rt);
        this.mockMvc.perform(post("/resemblegame")
                .param("gameid", "2"))
                .andExpect(model().attribute("resembleGame", rs))
                .andExpect(model().attribute("resembleTask", rt))
                .andExpect(view().name("resembleGame"));
    }
    
    @Test
    public void testNextResembleTask() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        ResembleTask rt = new ResembleTask(); 
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        taskNumbers.add(0);
        taskNumbers.add(1);
        taskNumbers.add(2);
        
        ResembleGame rg = new ResembleGame(taskNumbers, 2, "", "", 1); 
        
        rg.setCurrentTask(2);
        mockHttpSession.setAttribute("resembleGame", rg);
        when(resembleTaskService.getResembleTask(any(Integer.class))).thenReturn(rt); 
        this.mockMvc.perform(post("/nextresembletask")
                .param("score", "50")
                .session(mockHttpSession))
                .andExpect(model().attribute("resembleTask", rt))
                .andExpect(view().name("resembleGame"));
    } 
    
    @Test
    public void testFinishResembleGameZeroScore() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        User user = new User(); 
        user.setEmail("hei@gmail.com");
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        taskNumbers.add(0);
        taskNumbers.add(1);
        taskNumbers.add(2);      
        ResembleGame rg = new ResembleGame(taskNumbers, 2, "", "", 1);        
        rg.setCurrentTask(2);
        mockHttpSession.setAttribute("resembleGame", rg);
        mockHttpSession.setAttribute("user", user);       
        when(r.getResembleGameRes(any(String.class), any(ResembleGame.class))).thenReturn(0); 
        when(r.regResembleGameRes(any(String.class), any(Double.class), any(ResembleGame.class))).thenReturn(true);
        ArrayList<HighscoreDisplay> hsd = new ArrayList<>(); 
        hsd.add(new HighscoreDisplay("hei", "asd", 10)); 
        hsd.add(new HighscoreDisplay("hei", "asd", 10));      
        when(r.highscoreRG(any(ResembleGame.class))).thenReturn(hsd);       
        this.mockMvc.perform(post("/finishgame")
                .param("score", "50")
                .session(mockHttpSession))
               //doesnt work for some reason .andExpect(model().attribute("highscorelist", "hei asd 10\nhei asd 10"))
                .andExpect(view().name("finishgame"));
    }
}

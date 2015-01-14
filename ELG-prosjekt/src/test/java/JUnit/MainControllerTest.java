package JUnit;

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
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springmvc.controller.MainController;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.Login;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;
import springmvc.domain.User;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResultRepo;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceMock;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class MainControllerTest {
    private MockMvc mockMvc;
    @Mock
    private GameListServiceMock gameListService; 
    
    @Mock
    private ResultService r;
    
    @Mock
    private LoginService loginService;
   
    @Mock
    private PersonService personService; 
    
    @Mock
    private HttpSession session;
    
    @InjectMocks
    private MainController mainController; 
    
    public MainControllerTest() {
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
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).setViewResolvers(viewResolver).build();  
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void randomURL() throws Exception{
        this.mockMvc.perform(get("/åijsdophjsdg")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testIndexURL() throws Exception{
        this.mockMvc.perform(get("/index")).andExpect(view().name("index"));
    }
    
    @Test
    public void testAboutURL() throws Exception{
        this.mockMvc.perform(get("/about")).andExpect(view().name("about"));
    }
    
    @Test
    public void testChooseGame() throws Exception{
        this.mockMvc.perform(get("/choosegame")).andExpect(view().name("chooseGame"));
    }
    
    @Test
    public void testChoosegameWhenOK() throws Exception{
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getResembleGame(1)).thenReturn(gls.getResembleGame(1)); 
        this.mockMvc.perform(post("/choosegame")
                            .param("gameid", "1")).andExpect(status().isOk()).andExpect(view().name("chooseGame"));
    }
    
    @Test
    public void testHighScoreView() throws Exception{
        this.mockMvc.perform(get("/highscore")).andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testLoginPost() throws Exception{
        Person loggedIn = new Person("email@email.com", "Chris", "banana"); 
        when(personService.getPerson(any(String.class))).thenReturn(loggedIn);
        when(loginService.compareInformation(any(Login.class))).thenReturn(true); 
        this.mockMvc.perform(post("/login").param("asd", "asd")).andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testChooseGameHighScore() throws Exception{
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getResembleGame(any(Integer.class))).thenReturn(gls.getResembleGame(1)); 
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        when(r.highscoreRG(any(ResembleGame.class))).thenReturn(new ArrayList<HighscoreDisplay>()); 
        this.mockMvc.perform(post("/choosegameHighscore")
                .param("gameid", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("chooseGameHighscore"));
    }
    //Will fail for now due to no session attributes being mocked
    @Test
    public void testCompletionListAsAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        session.setAttribute(("user"), user);
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getResembleGame(any(Integer.class))).thenReturn(gls.getResembleGame(1)); 
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(session.getAttribute("user")).thenReturn(user); 
        
        this.mockMvc.perform(get("/completionlist"))
                .andExpect(view().name("completionlist"));   
    }
}

package Controllers;

import java.util.ArrayList;
import java.util.List;
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
import springmvc.service.GameListService;
import springmvc.service.GameListServiceMock;
import springmvc.service.LoginService;
import springmvc.service.PersonService;
import springmvc.service.ResembleTaskService;
import springmvc.service.ResultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class MainControllerTest {
    private MockMvc mockMvc;
    @Mock
    private GameListServiceMock gameListService; 
    
    @Mock
    private ResembleTaskService resembleTaskService; 
    
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
    
    //@Test
    public void testPersonNotNull() throws Exception{
        MockHttpSession session = new MockHttpSession(); 
        User user = new User(); 
        session.setAttribute("user", user);
        this.mockMvc.perform(get("blabla")
                .param("email", "mal@mail.com")
                .param("password", "password")
                .param("email", "mal@mail.com").session(session))
                .andExpect(view().name("about"));
    }
    
    @Test
    public void testShowAboutInloggedUser() throws Exception{
        User user = new User(); 
        MockHttpSession session = new MockHttpSession(); 
        session.setAttribute("user", user);
        this.mockMvc.perform(get("/about")
                .param("email", "email@mail.com")
                .param("password", "password").session(session)).andExpect(view().name("notloggedin"));
    }
    
    @Test
    public void randomURL() throws Exception{
        this.mockMvc.perform(get("/åijsdophjsdg")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testIndexNullUser() throws Exception{
        this.mockMvc.perform(get("/index")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testAboutURLNullUser() throws Exception{
        this.mockMvc.perform(get("/about")).andExpect(view().name("firstLogin"));
    }
    
   // @Test
    public void testLoginAsGuest()throws Exception{
        this.mockMvc.perform(get("/loginAsguest").session(new MockHttpSession())).andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testAboutURLNotLoggedIn() throws Exception{
        User user = new User(); 
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(); 
        mockHttpServletRequest.getSession().setAttribute("user", user);
        this.mockMvc.perform(get("/about")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testAboutURLLoggedIn() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/about").session(mockHttpSession)).andExpect(view().name("about"));
    }
    
    @Test
    public void testChooseGameNullUser() throws Exception{
        this.mockMvc.perform(get("/choosegame")).andExpect(view().name("error"));
    }
    
    @Test
    public void testChooseGameNotInlogged() throws Exception{
        User user = new User(); 
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(); 
        mockHttpServletRequest.getSession().setAttribute("user", user);
        this.mockMvc.perform(get("/choosegame")).andExpect(view().name("error"));
    }
    
     @Test
    public void testChooseGameInlogged() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/choosegame").session(mockHttpSession)).andExpect(view().name("chooseGame"));
    }
    
    @Test 
    public void chooseGameNullMultiApproved() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        when(gameListService.updateApprovedMultiChoiceGames(any(ArrayList.class), any(User.class))).thenReturn(null); 
         this.mockMvc.perform(post("/choosegame")
                .param("gameid", "Spill 1")
                .param("gametype", "multichoice")
                .session(mockHttpSession))
                .andExpect(view().name("chooseGame")); 
    }
    
    @Test
    public void testChooseGameMultiChoice() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getMultiChoiceInfo("Spill 1")).thenReturn(new MultiChoiceInfo());
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        this.mockMvc.perform(post("/choosegame")
                .param("gameid", "Spill 1")
                .param("gametype", "multichoice")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("multiChoiceInfo"))
                .andExpect(view().name("chooseGame"));    
    }
    
    @Test
    public void testChooseGameResembleGame() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        GameListServiceMock gls = new GameListServiceMock(); 
        ResembleGame rs = new ResembleGame(); 
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        taskNumbers.add(1); 
        taskNumbers.add(2); 
        rs.setTaskNumbers(taskNumbers);
        
        when(gameListService.getResembleGame(1)).thenReturn(rs);
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        when(gameListService.getResembleTasks(taskNumbers)).thenReturn(new ArrayList<ResembleTask>());
        this.mockMvc.perform(post("/choosegame")
                .param("gameid", "1")
                .param("gametype", "resemble")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attributeExists("resembleInfo"))
                .andExpect(view().name("chooseGame"));    
    }
    
    @Test
    public void testHighScoreViewNullUser() throws Exception{
        this.mockMvc.perform(get("/highscore")).andExpect(view().name("firstLogin"));
    }
    
     @Test
    public void testHighScoreViewUserNotLoggedIn() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        this.mockMvc.perform(get("/highscore").session(mockHttpSession)).andExpect(view().name("firstLogin"));
    }
    
        @Test
    public void testHighScoreViewUserLoggedIn() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/highscore").session(mockHttpSession)).andExpect(view().name("chooseGameHighscore"));
    }
    

    
    @Test
    public void testLoginPostWithError() throws Exception{
        Person loggedIn = new Person("email@email.com", "Chris", "banana"); 
        when(personService.getPerson(any(String.class))).thenReturn(loggedIn);
        when(loginService.compareInformation(any(Login.class))).thenReturn(true); 
        this.mockMvc.perform(post("/login").param("this ", "asd")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testLoginPostOk() throws Exception{
        Person loggedIn = new Person("email@email.com", "Chris", "banana"); 
        when(personService.getPerson(any(String.class))).thenReturn(loggedIn);
        when(loginService.compareInformation(any(Login.class))).thenReturn(true); 
        this.mockMvc.perform(post("/login")
                .param("email ", "email@email.com")
                .param("password", "thisisapass")
                .param("email", "eamsdasd@asd.com"))
                .andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testLoginInvalidCompareInfo() throws Exception{
        Person loggedIn = new Person("email@email.com", "Chris", "banana"); 
        when(personService.getPerson(any(String.class))).thenReturn(loggedIn);
        when(loginService.compareInformation(any(Login.class))).thenReturn(false); 
        this.mockMvc.perform(post("/login")
                .param("email ", loggedIn.getEmail())
                .param("password", "thisisapass")
                .param("email", "eamsdasd@asd.com"))
                .andExpect(model().attribute("wrongPassword", "Feil brukernavn/passord. Prøv på nytt"))
                .andExpect(view().name("firstLogin"));

    }
    
    @Test
    public void testLoginPostNotOk() throws Exception{
        Person loggedIn = new Person("email@email.com", "Chris", "banana"); 
        when(personService.getPerson(any(String.class))).thenReturn(loggedIn);
        when(loginService.compareInformation(any(Login.class))).thenReturn(false); 
        this.mockMvc.perform(post("/login")
                .param("asd", "asd"))
                .andExpect(status().isOk())
                .andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testChooseGameHighScoreResembleGame() throws Exception{
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getResembleGame(any(Integer.class))).thenReturn(gls.getResembleGame(1)); 
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        when(r.highscoreRG(any(ResembleGame.class))).thenReturn(new ArrayList<HighscoreDisplay>()); 
        this.mockMvc.perform(post("/choosegameHighscore")
                .param("gameid", "1")
                .param("gametype", "resemble"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("highscore"))
                .andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testChooseGameHighScoreMultiChoice() throws Exception{
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getMultiChoiceGame("Spill 1")).thenReturn(new MultiChoice()); 
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(new ArrayList<MultiChoiceInfo>()); 
        when(r.highscoreRG(any(ResembleGame.class))).thenReturn(new ArrayList<HighscoreDisplay>()); 
        this.mockMvc.perform(post("/choosegameHighscore")
                .param("gameid", "Spill 1")
                .param("gametype", "multichoice"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("highscore"))
                .andExpect(view().name("chooseGameHighscore"));
    }
    
    @Test
    public void testChooseGameCompletionListNullUser() throws Exception{
        this.mockMvc.perform(get("/completionlist")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testChooseGameCompletionPostListNullUser() throws Exception{
        this.mockMvc.perform(post("/choosegameCompletionlist")).andExpect(view().name("error"));
    }
    
    @Test
    public void testCompletionListWithoutAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/completionlist").session(mockHttpSession))
                .andExpect(view().name("about"));
    }
    
    @Test
    public void testCompletionListAsAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/completionlist").session(mockHttpSession))
                .andExpect(view().name("completionlist"));
    }
    
    @Test
    public void testChooseCompletionWithoutAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(post("/choosegameCompletionlist")
                .param("classid", "1")
                .session(mockHttpSession))
                .andExpect(view().name("about"));
    }
    
     @Test
    public void testChooseCompletionResembleGameAsAdminNoStudentsPassed() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        //when(r.getCompletionRG(any(ResembleGame.class))).thenReturn(new ArrayList<HighscoreDisplay>()); 
        this.mockMvc.perform(post("/choosegameCompletionlist")
                .param("classid", "1")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("nopass"))
                .andExpect(view().name("completionlist"));                
    }
    
    @Test
    public void testChooseCompletionResembleGameAsAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        ArrayList<HighscoreDisplay> hsd = new ArrayList<HighscoreDisplay>(); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        when(r.getNewCompletionlistResemble(any(String.class))).thenReturn(hsd); 
        this.mockMvc.perform(post("/choosegameCompletionlist")
                .param("classid", "1")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("list"))
                .andExpect(view().name("completionlist"));
    }
    
    @Test
    public void testChooseCompletionMultiChoiceAsAdminNoStudentsPass() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        ArrayList<HighscoreDisplay> hsd = new ArrayList<HighscoreDisplay>(); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        when(r.getNewCompletionlistMulti(any(String.class))).thenReturn(new ArrayList<HighscoreDisplay>()); 
        this.mockMvc.perform(post("/choosegameCompletionlist")
                .param("classid", "1")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("nopass"))
                .andExpect(view().name("completionlist"));
    }
    
    @Test
    public void testChooseCompletionMultiChoiceAsAdmin() throws Exception{
        User user = new User("hello@mail.com", "yes", "lastname"); 
        user.setAdmin(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        ArrayList<HighscoreDisplay> hsd = new ArrayList<HighscoreDisplay>(); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        hsd.add(new HighscoreDisplay()); 
        when(r.getCompleteCompletion(any(String.class))).thenReturn(hsd); 
        this.mockMvc.perform(post("/choosegameCompletionlist")
                .param("classid", "1")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("list"))
                .andExpect(view().name("completionlist"));
    }
    
    @Test
    public void testCreateGameNullUser() throws Exception{
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", null);
        this.mockMvc.perform(get("/creategame")
                .session(mockHttpSession))
                .andExpect(view().name("firstLogin"));
    }
    
     @Test
    public void testCreateGameNotLoggedIn() throws Exception{
        User user = new User(); 
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/creategame")
                .session(mockHttpSession))
                .andExpect(view().name("notloggedin"));
    }
    
     @Test
    public void testCreateGameLoggedIn() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/creategame")
                .session(mockHttpSession))
                .andExpect(view().name("creategame"));
    }
    
    @Test
    public void testVoteGameResemble() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(post("/votegame")
                .param("gametype", "resemble")
                .param("gameid", "gameid")
                .session(mockHttpSession))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testVoteGameMulti() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(post("/votegame")
                .param("gametype", "multichoice")
                .param("gameid", "gameid")
                .session(mockHttpSession))
                .andExpect(view().name("chooseothergame"));
    }
    
    @Test
    public void testChooseOtherGameNullUser() throws Exception{
        this.mockMvc.perform(get("/chooseothergames")).andExpect(view().name("error"));
    }
    
    @Test
    public void testChooseOtherGamePostNullUser() throws Exception{
        this.mockMvc.perform(post("/chooseothergames")).andExpect(view().name("error"));
    }
    
    //@Test
    public void testChooseOtherGameNotInlogged() throws Exception{
        User user = new User(); 
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(); 
        mockHttpServletRequest.getSession().setAttribute("user", user);
        this.mockMvc.perform(get("/chooseothergames")).andExpect(view().name("error"));
    }
    
     @Test
    public void testChooseOtherGameInlogged() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/chooseothergames").session(mockHttpSession)).andExpect(view().name("chooseothergame"));
    }
    
    @Test
    public void testChooseOtherGameMultiChoice() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        GameListServiceMock gls = new GameListServiceMock(); 
        when(gameListService.getMultiChoiceInfo("Spill 1")).thenReturn(new MultiChoiceInfo());
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        this.mockMvc.perform(post("/chooseothergames")
                .param("gameid", "Spill 1")
                .param("gametype", "multichoice")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("multiChoiceInfo"))
                .andExpect(view().name("chooseothergame"));    
    }
    
    @Test
    public void testChooseOtherGameResembleGame() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        GameListServiceMock gls = new GameListServiceMock(); 
        ResembleGame rs = new ResembleGame(); 
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        taskNumbers.add(1); 
        taskNumbers.add(2); 
        rs.setTaskNumbers(taskNumbers);
        
        when(gameListService.getResembleGame(1)).thenReturn(rs);
        when(gameListService.getAllResembleGames()).thenReturn(gls.getAllResembleGames()); 
        when(gameListService.getAllMultiChoiceInfo()).thenReturn(gls.getAllMultiChoiceInfo()); 
        when(gameListService.getResembleTasks(taskNumbers)).thenReturn(new ArrayList<ResembleTask>());
        this.mockMvc.perform(post("/chooseothergames")
                .param("gameid", "1")
                .param("gametype", "resemble")
                .session(mockHttpSession))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(model().attributeExists("resembleInfo"))
                .andExpect(view().name("chooseothergame"));    
    }
    
    @Test
    public void testKodeLostTagsNullUser() throws Exception{
        this.mockMvc.perform(get("/kOdesLostTags")).andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testKodeLostTag() throws Exception{
        User user = new User(); 
        user.setInLogged(true);
        MockHttpSession mockHttpSession = new MockHttpSession(); 
        mockHttpSession.setAttribute("user", user);
        this.mockMvc.perform(get("/kOdesLostTags")
                .session(mockHttpSession))
                .andExpect(view().name("kOdesLostTags"));
    }
    
   // @Test
    public void testLogout() throws Exception{
        this.mockMvc.perform(get("logout")
                .param("email", "hei@gmail.com")
                .param("password", "heiasd"))
                .andExpect(view().name("firstLogin"));
    }
    
    @Test
    public void testMoveGameResemble() throws Exception{
        when(resembleTaskService.getResembleTask(any(Integer.class))).thenReturn(new ResembleTask());
        when(gameListService.getResembleGame(any(Integer.class))).thenReturn(new ResembleGame()); 
        when(gameListService.makeResembleGameExerciseExtra(any(Integer.class))).thenReturn(true);
        when(gameListService.makeResembleGameExercise(any(Integer.class))).thenReturn(true);
        when(gameListService.removeResembleGameFromExercise(any(Integer.class))).thenReturn(true);
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "1")
                .param("gametype", "resemble")
                .param("button", "makeextra"))
                .andExpect(view().name("about"));
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "1")
                .param("gametype", "resemble")
                .param("button", "makeexercise"))
                .andExpect(view().name("about"));
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "1")
                .param("gametype", "resemble")
                .param("button", "removeexercise"))
                .andExpect(view().name("about"));
    }
    
    @Test
    public void testMoveGameMultiChoice() throws Exception{
        when(gameListService.makeMultiGameExerciseExtra(any(String.class))).thenReturn(true); 
        when(gameListService.makeMultiGameExercise(any(String.class))).thenReturn(true); 
        when(gameListService.removeMultiGameFromExercise(any(String.class))).thenReturn(true); 
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "asd")
                .param("gametype", "multichoice")
                .param("button", "makeextra"))
                .andExpect(view().name("about"));
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "asd")
                .param("gametype", "multichoice")
                .param("button", "makeexercise"))
                .andExpect(view().name("about"));
        
        this.mockMvc.perform(post("/movegame")
                .param("gameid", "asd")
                .param("gametype", "multichoice")
                .param("button", "removeexercise"))
                .andExpect(view().name("about"));
    }
}

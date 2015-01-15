/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Controllers.ConfigTest;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleGameRepoDB;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResembleTaskRepoDB;
import springmvc.service.GameListServiceImpl;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
@WebAppConfiguration
public class GameListServiceImplTest {
    
    @Mock
    private ResembleGameRepo resembleGameRepoDB;// = mock(ResembleGameRepoDB.class); 
    @Mock
    private MultiChoiceRepository multipleChoiceRepoDB;// = mock(MultiChoiceRepository.class); 
    @Mock
    private ResembleTaskRepo resembleTaskRepoDB;// = mock(ResembleTaskRepo.class); 
    
    @InjectMocks
    private GameListServiceImpl gameList; 
    
    public GameListServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetResembleGame(){
        ResembleGame game = new ResembleGame(); 
        ResembleGame gameFromService = new ResembleGame(); 
        ArrayList<ResembleTask> resembleTasks = new ArrayList<>(); 
        ArrayList<ResembleTask> resembleTasksFromService = new ArrayList<>();
        ResembleTask t1 = new ResembleTask(0, "", "", "", "", "", 0, 0);
        ResembleTask t2 = new ResembleTask(0, "", "", "", "", "", 0, 0);
        ResembleTask t3 = new ResembleTask(0, "", "", "", "", "", 0, 0);
        resembleTasks.add(t1);
        resembleTasks.add(t2);
        resembleTasks.add(t3);
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        taskNumbers.add(0); 
        taskNumbers.add(0); 
        taskNumbers.add(0); 
        game.setTaskNumbers(taskNumbers);
        when(resembleGameRepoDB.getResembleGame(any(Integer.class))).thenReturn(gameFromService);
        when(resembleTaskRepoDB.getResembleTasksByGameId(any(Integer.class))).thenReturn(resembleTasks);
        assertEquals(game.getTaskNumbers(), gameList.getResembleGame(0).getTaskNumbers()); 
    }
    
    @Test
    public void testGetMultiChoiceInfo (){
        when(multipleChoiceRepoDB.getMultiChoiceInfo(any(String.class))).thenReturn(new MultiChoiceInfo()); 
        assertNotNull(gameList.getMultiChoiceInfo("")); 
    }
    
    @Test
    public void testGetMultiChoiceGame (){
        when(multipleChoiceRepoDB.getMultiChoice(any(String.class))).thenReturn(new MultiChoice()); 
        assertNotNull(gameList.getMultiChoiceGame("")); 
    }
    
    @Test
    public void testGetAllMultiChoiceInfo(){
        when(multipleChoiceRepoDB.getAllMultiChoiceInfo()).thenReturn(new ArrayList<MultiChoiceInfo>());
        assertNotNull(gameList.getAllMultiChoiceInfo());
    }
    
    @Test
    public void testGetResembleTasks(){
        when(resembleTaskRepoDB.getResembleTasks(any(ArrayList.class))).thenReturn(new ArrayList<ResembleTask>()); 
        assertNotNull(gameList.getResembleTasks(new ArrayList<Integer>())); 
    }
}

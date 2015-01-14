/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleTaskRepo;
import springmvc.service.GameListService;
import springmvc.service.ResultService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=ConfigTest.class,loader=AnnotationConfigContextLoader.class)
public class DatabaseJUnit {
    @Autowired
    private ResembleGameRepo resembleGameRepoDB; 
    @Autowired
    private MultiChoiceRepository multipleChoiceRepoDB; 
    @Autowired
    private ResembleTaskRepo resembleTaskRepoDB; 
    @Autowired
    private GameListService gameListService; 
    
    @Autowired
    private ResultService r;
    
    public DatabaseJUnit() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testDatabaseConncetion(){
        if(resembleGameRepoDB==null||multipleChoiceRepoDB==null ||resembleTaskRepoDB==null){
            fail("No database connection"); 
        }
    }
    
    //@Test
    public void testHighscoreInsert(){
        String email = "asdsa@gmail.com";
        String fname = "Jorg"; 
        String lname = "Wims"; 
        User user = new User(email, fname, lname); 
        String k = user.getEmail();
        ResembleGame resembleGame = gameListService.getResembleGame(1); 
        double score = 250; 
        int d = r.getResembleGameRes(k, resembleGame);
        r.regResembleGameRes(k, score, resembleGame);
        assertEquals(250, r.getResembleGameRes(k, resembleGame));
    }
}

package springmvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.MultipleChoiceRepoDB;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleGameRepoDB;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResembleTaskRepoDB;
import springmvc.repository.mappers.ResembleGameMapper;

public class GameListServiceImpl implements GameListService{
    @Autowired
    private ResembleGameRepo resembleGameRepoDB; 
    @Autowired
    private MultiChoiceRepository multipleChoiceRepoDB; 
    @Autowired
    private ResembleTaskRepo resembleTaskRepoDB; 
    
//    @Autowired
//    public void setRepoDB(ResembleTaskRepoDB resembleTaskRepoDB, ResembleGameRepoDB resembleGameRepoDB, MultipleChoiceRepoDB multipleChoiceRepoDB){
//        this.resembleTaskRepoDB = resembleTaskRepoDB;
//        this.resembleGameRepoDB = resembleGameRepoDB;
//        this.multipleChoiceRepoDB = multipleChoiceRepoDB;
//    }
//    
    
    public GameListServiceImpl(){
    }
    
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGames(){
        return resembleGameRepoDB.getAllResembleGames(); 
    }
    
    @Override
    public ArrayList<MultiChoice> getAllMultiChoiceGames(){
        return multipleChoiceRepoDB.getAllMultiChoiceGames(); 
    }
    @Override
    public ResembleGame getResembleGame(int gameId){
        ResembleGame game = resembleGameRepoDB.getResembleGame(gameId); 
        ArrayList<ResembleTask> tasks = resembleTaskRepoDB.getResembleTasksByGameId(gameId);
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        for(ResembleTask rt : tasks){
            taskNumbers.add(rt.getTaskNumber());
        }
        game.setTaskNumbers(taskNumbers);
        return game; 
    }
    
    public ResembleGame getResemleGameByName(String gameName){
        return this.resembleGameRepoDB.getResemleGameByName(gameName); 
    }
    
    @Override
    public MultiChoice getMultiChoiceGame(String gameId){
        return multipleChoiceRepoDB.getMultiChoice(gameId);
    }
    @Override
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers){
        ArrayList<ResembleTask> list = resembleTaskRepoDB.getResembleTasks(taskNumbers);
        for(ResembleTask rt : list){
            rt.setSolutionHTML(rt.getSolutionHTML().replace("\"", "\'"));
            System.out.println(rt.getSolutionHTML());
        }
        return list; 
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(){
        return multipleChoiceRepoDB.getAllMultiChoiceInfo(); 
    }
    
    @Override
    public MultiChoiceInfo getMultiChoiceInfo(String gameId){
        return multipleChoiceRepoDB.getMultiChoiceInfo(gameId); 
    }    
    
    public ArrayList<MultiChoiceInfo> updateApprovedMultiChoiceGames(ArrayList<MultiChoiceInfo> multiChoiceGames, User user){
        
        //TODO get information from the DB. This is just test DATA. 
        /*
                System.out.println("in updateApprovedMultiChoiceGames");
        for (int i = 0; i<multiChoiceGames.size();i++){
            System.out.println(multiChoiceGames.get(i));
            if (multiChoiceGames.get(i).getName().equals("CSS spill")){
                multiChoiceGames.get(i).setApproved(1);
            }
            //multiChoiceGames.get(i).setApproved(1);
        }
                */
        return multiChoiceGames;
    }

    @Override
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId) {
        return resembleGameRepoDB.insertResembleGame(gameName, info, learningGoals, difficulty, creatorId); 
    }

}

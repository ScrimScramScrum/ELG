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
import springmvc.repository.MultipleChoiceRepoDB;
import springmvc.repository.ResembleGameRepoDB;
import springmvc.repository.ResembleTaskRepoDB;
import springmvc.repository.mappers.ResembleGameMapper;

public class GameListServiceImpl implements GameListService{
    private ResembleGameRepoDB resembleGameRepoDB; 
    private MultipleChoiceRepoDB multipleChoiceRepoDB; 
    private ResembleTaskRepoDB resembleTaskRepoDB; 
    
    
    public GameListServiceImpl(){
        this.resembleTaskRepoDB = new ResembleTaskRepoDB(); 
        this.resembleGameRepoDB = new ResembleGameRepoDB(); 
        this.multipleChoiceRepoDB = new MultipleChoiceRepoDB(); 
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
        return resembleGameRepoDB.getResembleGame(gameId); 
    }
    
    @Override
    public MultiChoice getMultiChoiceGame(String gameId){
        return multipleChoiceRepoDB.getMultiChoice(gameId);
    }
    @Override
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers){
        return resembleTaskRepoDB.getResembleTasks(taskNumbers);
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(){
        return multipleChoiceRepoDB.getAllMultiChoiceInfo(); 
    }
    
    @Override
    public MultiChoiceInfo getMultiChoiceInfo(String gameId){
        return multipleChoiceRepoDB.getMultiChoiceInfo(gameId); 
    }
    
}

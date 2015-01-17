package springmvc.service;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;

public interface GameListService {
    
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public ArrayList<MultiChoice> getAllMultiChoiceGames(); 
    public ResembleGame getResembleGame(int gameId); 
    public MultiChoice getMultiChoiceGame(String gameId); 
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers);
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(); 
    public MultiChoiceInfo getMultiChoiceInfo(String gameId); 
    public ArrayList<MultiChoiceInfo> updateApprovedMultiChoiceGames(ArrayList<MultiChoiceInfo> multiChoiceGames, User user);
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId); 
    public ResembleGame getResemleGameByName(String gameName);
    public ArrayList<ResembleGame> updateApprovedResembleGames( ArrayList<ResembleGame> resembleGames, User user);       

}

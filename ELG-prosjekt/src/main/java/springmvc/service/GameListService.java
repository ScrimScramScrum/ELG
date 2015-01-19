package springmvc.service;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;

public interface GameListService {
    
    public ArrayList<ResembleGame> getAllResembleGames();
    public ArrayList<ResembleGame> getAllResembleGamesFromOving();
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra();
    public ArrayList<MultiChoice> getAllMultiChoiceGames();
    public ResembleGame getResembleGame(int gameId); 
    public MultiChoice getMultiChoiceGame(String gameId); 
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers);
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(); 
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOving();
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOvingExtra();
    public MultiChoiceInfo getMultiChoiceInfo(String gameId); 
    public ArrayList<MultiChoiceInfo> updateApprovedMultiChoiceGames(ArrayList<MultiChoiceInfo> multiChoiceGames, User user);
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId); 
    public ResembleGame getResemleGameByName(String gameName);
    public ArrayList<ResembleGame> updateApprovedResembleGames( ArrayList<ResembleGame> resembleGames, User user);       
    public ArrayList<ResembleGame> getAllResembleGamesNotInOving();
    public int getVoteCountByGameId(int gameId);
    public boolean registerResembleGameVote(String usermail, int gameId);
    public boolean makeResembleGameExercise(int gameId);
    public boolean makeResembleGameExerciseExtra(int gameId);
    public boolean removeResembleGameFromExercise(int gameId);
}


package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public interface ResembleGameRepo {
    public ResembleGame getResembleGame(int gameNumber); 
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public ArrayList<ResembleGame> getAllResembleGamesFromOving(); 
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra();
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId);
    public ResembleGame getResemleGameByName(String gameName);

    public int sqlGetScoreFromFromResebleGameWithNameAndEmail(String gamename, String email);
    
}

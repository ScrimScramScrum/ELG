
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public interface ResembleGameRepo {
    public ResembleGame getResembleGame(int gameNumber); 
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId);
    public ResembleGame getResemleGameByName(String gameName);
}


package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public interface ResembleGameRepo {
    public ResembleGame getResembleGame(int gameNumber); 
    public ArrayList<ResembleGame> getAllResembleGames(); 
}

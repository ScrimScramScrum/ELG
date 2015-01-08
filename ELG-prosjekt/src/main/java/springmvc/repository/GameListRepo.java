package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;

public interface GameListRepo {
    
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceGames(); 
    public ResembleGame getResembleGame(int gameId); 
    public MultiChoiceInfo getMultiChoiceGame(String gameId); 
}

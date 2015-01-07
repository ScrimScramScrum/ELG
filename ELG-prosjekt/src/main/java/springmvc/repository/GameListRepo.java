package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.ResembleGame;

public interface GameListRepo {
    
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public ArrayList<MultiChoice> getAllMultiChoiceGames(); 
    public ResembleGame getResembleGame(int gameId); 
    public MultiChoice getMultiChoiceGame(int gameId); 
    
}

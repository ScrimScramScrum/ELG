
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public class ResembleGameRepoMock implements ResembleGameRepo{
    private ArrayList<Integer> mockTaskNumbers = new ArrayList<>(); 
    
    public ResembleGame getResembleGame(int gameNumber){
        mockTaskNumbers.add(1); 
        mockTaskNumbers.add(2); 
        mockTaskNumbers.add(3); 
        
        return new ResembleGame(mockTaskNumbers); 
    }
    
}

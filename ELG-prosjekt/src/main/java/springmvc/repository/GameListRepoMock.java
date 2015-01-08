package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;

public class GameListRepoMock implements GameListRepo{
    //the following lists are purely mock lists - will be replaced by methods returnings lists containing objects from the database: 
    private ArrayList<ResembleGame> resembleGames; 
    private ArrayList<MultiChoiceInfo> multiChoiceGames; 
    
    public GameListRepoMock(){
        resembleGames = new ArrayList<>(); 
        multiChoiceGames = new ArrayList<>(); 
        ArrayList<Integer> liste = new ArrayList<>(); 
        liste.add(1);
        liste.add(2);
        liste.add(3);
        resembleGames.add(new ResembleGame(liste, 1)); 
        
        liste = new ArrayList<>(); 
        liste.add(1);
        liste.add(3);
        liste.add(2);
        resembleGames.add(new ResembleGame(liste, 2)); 

        liste = new ArrayList<>(); 
        liste.add(3);
        liste.add(2);
        liste.add(1);
        resembleGames.add(new ResembleGame(liste, 3));     
        
        //add mock mutlichoicegames in the list multiChoiceGames here!
        multiChoiceGames.add(new MultiChoiceInfo("Spill 1", "Test info her"));
        multiChoiceGames.add(new MultiChoiceInfo("Spill 2", "Test info her :D :D :D"));
    }

    public ArrayList<ResembleGame> getAllResembleGames(){
       return resembleGames; 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceGames(){
        return multiChoiceGames; 
    }
    
     public ResembleGame getResembleGame(int gameId){
         ResembleGame res = new ResembleGame(resembleGames.get(gameId).getTaskNumbers(),gameId); 
         return res;
    }     
}
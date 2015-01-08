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
        resembleGames.add(new ResembleGame(liste, 1, "a goal", "infomartion goes here" , 1)); 
        
        liste = new ArrayList<>(); 
        liste.add(1);
        liste.add(3);
        liste.add(2);
        resembleGames.add(new ResembleGame(liste, 2, "anotehr goal", "lots of info", 3)); 

        liste = new ArrayList<>(); 
        liste.add(3);
        liste.add(2);
        liste.add(1);
        resembleGames.add(new ResembleGame(liste, 3, "the best goal of them all", " info info info info", 2));
        
        //add mock mutlichoicegames in the list multiChoiceGames here!
        multiChoiceGames.add(new MultiChoiceInfo("Spill 1", "masse kule mål", "Test info her", 1));
        multiChoiceGames.add(new MultiChoiceInfo("Spill 2", "enda flere kule mål", "Test info her :D :D :D", 3));
    }

    public ArrayList<ResembleGame> getAllResembleGames(){
       return resembleGames; 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceGames(){
        return multiChoiceGames; 
    }
    
     public ResembleGame getResembleGame(int gameId){
         ResembleGame res = new ResembleGame(resembleGames.get(gameId).getTaskNumbers(),gameId, resembleGames.get(gameId).getLearningGoal(), resembleGames.get(gameId).getInfo(), resembleGames.get(gameId).getDifficulty()); 
         return res;
    }     
}
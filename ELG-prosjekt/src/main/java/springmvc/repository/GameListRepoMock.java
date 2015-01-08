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
        resembleGames.add(new ResembleGame(liste, 1, "a goal", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but a" , 1)); 
        
        liste = new ArrayList<>(); 
        liste.add(1);
        liste.add(3);
        liste.add(2);
        resembleGames.add(new ResembleGame(liste, 2, "anotehr goal", "Den yngste av de etterlyste mennene, 18 år gamle Hamyd Mourad, meldte seg ved en politistasjon sent onsdag i Charleville-Mézières i nærheten av grensen til Belgia.", 3)); 

        liste = new ArrayList<>(); 
        liste.add(3);
        liste.add(2);
        liste.add(1);
        resembleGames.add(new ResembleGame(liste, 3, "the best goal of them all", "G har ikke ansvar for innhold på eksterne nettsider som det lenkes til. Kopiering av materiale fra VG for bruk annet sted er ikke tillatt uten avtale.", 2));
        
        //add mock mutlichoicegames in the list multiChoiceGames here!
        multiChoiceGames.add(new MultiChoiceInfo("Spill 1", "masse kule mål", "Det sier den tidligere agenten til Telegraph. Scheuer har lang agenterfaring bak seg og jobbet blant annet med å spore opp bin Laden i perioden 1996 til 1999.\n" +
"\n" +
"– De var ikke selvmordskandidater siden de hadde på seg både hansker og masker, sier han.\n" +
"\n" +
"– Innsatsen de har lagt inn i dette tyder på at det også er en del to. Om det er et nytt angrep eller en video vet jeg ikke, sier han.\n" +
"\n" +
"Om det kommer en ny del, vil den være dramatisk, tror den tidligere CIA-agenten.", 1));
        multiChoiceGames.add(new MultiChoiceInfo("Spill 2", "enda flere kule mål", "v de tre gjerningsmennene som snakket om hva de har gjort, og hvordan de kom seg unna. Det virker for meg som om dette er en to episoders hendelse som vi nå er vitne til ", 3));
    }

    public ArrayList<ResembleGame> getAllResembleGames(){
       return resembleGames; 
    }
    
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceGames(){
        return multiChoiceGames; 
    }
    
     public ResembleGame getResembleGame(int gameId){
         for(int i = 0; i<resembleGames.size(); i++){
             if(resembleGames.get(i).getGameId()==gameId){
                return new ResembleGame(resembleGames.get(i).getTaskNumbers(), resembleGames.get(i).getGameId(), resembleGames.get(i).getLearningGoal(), resembleGames.get(i).getInfo(), resembleGames.get(i).getDifficulty()); 
            }
         }
         return null; 
    }     
     
    public MultiChoiceInfo getMultiChoiceGame(String name){
        for(int i = 0; i < multiChoiceGames.size(); i++){
            if (multiChoiceGames.get(i).getName().equals(name)){
                return multiChoiceGames.get(i);
            }
        }
        return null;
    }
}
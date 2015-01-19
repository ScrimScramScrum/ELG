package springmvc.service;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.repository.ResembleTaskRepoMock;

public class GameListServiceMock implements GameListService{
    //the following lists are purely mock lists - will be replaced by methods returnings lists containing objects from the database: 
    private ArrayList<ResembleGame> resembleGames; 
    private ArrayList<MultiChoiceInfo> multiChoiceGames; 
    private ResembleTaskRepoMock resembleTaskRepoMock; 
    
    public GameListServiceMock(){
        resembleTaskRepoMock = new ResembleTaskRepoMock(); 
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

    @Override
    public ArrayList<ResembleGame> getAllResembleGames(){
       return resembleGames; 
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(){
        return multiChoiceGames; 
    }
    
    @Override
     public ResembleGame getResembleGame(int gameId){
         for(int i = 0; i<resembleGames.size(); i++){
             if(resembleGames.get(i).getGameId()==gameId){
                return new ResembleGame(resembleGames.get(i).getTaskNumbers(), resembleGames.get(i).getGameId(), resembleGames.get(i).getLearningGoal(), resembleGames.get(i).getInfo(), resembleGames.get(i).getDifficulty()); 
            }
         }
         return null; 
    }     
     
    @Override
    public MultiChoiceInfo getMultiChoiceInfo(String name){
        for(int i = 0; i < multiChoiceGames.size(); i++){
            if (multiChoiceGames.get(i).getName().equals(name)){
                return multiChoiceGames.get(i);
            }
        }
        return null;
    }
    
    @Override
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers){
        ArrayList<ResembleTask> result = new ArrayList<>(); 
        for(Integer i : taskNumbers){
            if(resembleTaskRepoMock.getResembleTask(i)!=null){
                result.add(resembleTaskRepoMock.getResembleTask(i));
            }
        }
        return result; 
    }

    @Override
    public ArrayList<MultiChoice> getAllMultiChoiceGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MultiChoice getMultiChoiceGame(String gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> updateApprovedMultiChoiceGames(ArrayList<MultiChoiceInfo> multiChoiceGames, User user){
        System.out.println("in updateApprovedMultiChoiceGames");
        for (int i = 0; i<multiChoiceGames.size();i++){
            System.out.println(multiChoiceGames.get(i));
        }
        
        return multiChoiceGames;
    }

    @Override
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResembleGame getResemleGameByName(String gameName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ResembleGame> updateApprovedResembleGames(ArrayList<ResembleGame> resembleGames, User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOvingExtra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ResembleGame> getAllResembleGamesNotInOving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getVoteCountByGameId(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean registerResembleGameVote(String usermail, int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean makeResembleGameExercise(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean makeResembleGameExerciseExtra(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeResembleGameFromExercise(int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
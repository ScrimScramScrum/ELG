package springmvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.MultiResult;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.repository.MultiChoiceRepository;
import springmvc.repository.MultipleChoiceRepoDB;
import springmvc.repository.ResembleGameRepo;
import springmvc.repository.ResembleGameRepoDB;
import springmvc.repository.ResembleTaskRepo;
import springmvc.repository.ResembleTaskRepoDB;
import springmvc.repository.mappers.ResembleGameMapper;

public class GameListServiceImpl implements GameListService{
    @Autowired
    private ResembleGameRepo resembleGameRepoDB; 
    @Autowired
    private MultiChoiceRepository multipleChoiceRepoDB; 
    @Autowired
    private ResembleTaskRepo resembleTaskRepoDB; 
    
//    @Autowired
//    public void setRepoDB(ResembleTaskRepoDB resembleTaskRepoDB, ResembleGameRepoDB resembleGameRepoDB, MultipleChoiceRepoDB multipleChoiceRepoDB){
//        this.resembleTaskRepoDB = resembleTaskRepoDB;
//        this.resembleGameRepoDB = resembleGameRepoDB;
//        this.multipleChoiceRepoDB = multipleChoiceRepoDB;
//    }
//    
    
    public GameListServiceImpl(){
    }
    
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGames(){
        return resembleGameRepoDB.getAllResembleGames(); 
    }
    
    @Override
    public ArrayList<MultiChoice> getAllMultiChoiceGames(){
        return multipleChoiceRepoDB.getAllMultiChoiceGames(); 
    }
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOving(){ 
        return resembleGameRepoDB.getAllResembleGamesFromOving(); 
    }
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra(){ 
        return resembleGameRepoDB.getAllResembleGamesFromOvingExtra(); 
    }
    
    @Override
    public ResembleGame getResembleGame(int gameId){
        ResembleGame game = resembleGameRepoDB.getResembleGame(gameId); 
        ArrayList<ResembleTask> tasks = resembleTaskRepoDB.getResembleTasksByGameId(gameId);
        ArrayList<Integer> taskNumbers = new ArrayList<>(); 
        for(ResembleTask rt : tasks){
            taskNumbers.add(rt.getTaskNumber());
        }
        game.setTaskNumbers(taskNumbers);
        return game; 
    }
    
    public ResembleGame getResemleGameByName(String gameName){
        return this.resembleGameRepoDB.getResemleGameByName(gameName); 
    }
    
    @Override
    public MultiChoice getMultiChoiceGame(String gameId){
       /* System.out.println("AAAAAYYAYAYAYAYAAAYYAYAYAYAYAYAYAA ALLAHU AKBAR");
        ArrayList<Exercise> exList = multipleChoiceRepoDB.getMultiChoice(gameId).getExercises();
        for(Exercise ex : exList){
            String[] newAlts = new String[4];
            for(int i = 0; i < exList.size(); i++){
                newAlts[i] = ex.getAlternativeIndex(i).replace("\"", "\'");
                
            }
            ex.setAlternatives(newAlts);
            for (int i = 0; i < ex.getAlternatives().length; i++){
                
                System.out.println(ex.getAlternatives());
            }
        }*/
        return multipleChoiceRepoDB.getMultiChoice(gameId);
    }
    
    @Override
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers){
        ArrayList<ResembleTask> list = resembleTaskRepoDB.getResembleTasks(taskNumbers);
        for(ResembleTask rt : list){
            rt.setSolutionHTML(rt.getSolutionHTML().replace("\"", "\'"));
            rt.setSolutionCSS(rt.getSolutionCSS().replace("\"", "\'"));
            rt.setStartingHTML(rt.getStartingHTML().replace("\"", "\'"));
            rt.setStartingCSS(rt.getStartingCSS().replace("\"", "\'"));
            System.out.println(rt.getSolutionHTML());
        }
        return list; 
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo(){
        return multipleChoiceRepoDB.getAllMultiChoiceInfo(); 
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOving(){
        return multipleChoiceRepoDB.getAllMultiChoiceInfoFromOving(); 
    }
    
    @Override
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfoFromOvingExtra(){
        return multipleChoiceRepoDB.getAllMultiChoiceInfoFromOvingExtra(); 
    }
    
    
    @Override
    public MultiChoiceInfo getMultiChoiceInfo(String gameId){
        return multipleChoiceRepoDB.getMultiChoiceInfo(gameId); 
    }    
    
    
    
    public ArrayList<ResembleGame> updateApprovedResembleGames( ArrayList<ResembleGame> resembleGames, User user){ 
        System.out.println("--------updateApprovedResembleGames");
        System.out.println("size: "+resembleGames.size());
        for (int i = 0; i<resembleGames.size();i++){
            System.out.println("name: "+resembleGames.get(i).getGamename());
            
            int score = resembleGameRepoDB.sqlGetScoreFromFromResebleGameWithNameAndEmail(resembleGames.get(i).getGamename(), user.getEmail());
            
            if (score>=80){
                resembleGames.get(i).setApproved(1);
                System.out.println("set approved");
            }            
        }
        return resembleGames;
        
        
    }
    
    public ArrayList<MultiChoiceInfo> updateApprovedMultiChoiceGames(ArrayList<MultiChoiceInfo> multiChoiceGames, User user){

        
        for (int i = 0; i<multiChoiceGames.size();i++){
            //System.out.println(multiChoiceGames.get(i).getName());
            MultiResult multiResult = multipleChoiceRepoDB.getMultiChoiceAndUsername(multiChoiceGames.get(i).getName(), user.getEmail());
            
         
            if (multiResult!=null){
//                System.out.println("Name: "+multiChoiceGames.get(i).getName());
//                System.out.println("email: "+multiResult.getEmail());
//                System.out.println("game : "+multiResult.getIdGame());
//                System.out.println("score: "+multiResult.getScore());
                if (multiResult.getScore()>=80 && user.getEmail().equals(multiResult.getEmail())){
                    multiChoiceGames.get(i).setApproved(1);
                    
                }
                
            }
            
        }
        return multiChoiceGames;
    }

    @Override
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId) {
        return resembleGameRepoDB.insertResembleGame(gameName, info, learningGoals, difficulty, creatorId); 
    }

    public ArrayList<ResembleGame> getAllResembleGamesNotInOving(){
        ArrayList<ResembleGame> resembleGames = resembleGameRepoDB.getAllResembleGamesNotInOving();
        for(ResembleGame rg : resembleGames){
            ArrayList<ResembleTask> resembleTasks = resembleTaskRepoDB.getResembleTasksByGameId(rg.getGameId());
            ArrayList<Integer> taskNumbers = new ArrayList<>(); 
            for(ResembleTask rt : resembleTasks){
                taskNumbers.add(rt.getTaskNumber());
            }
            rg.setTaskNumbers(taskNumbers);
            rg.setVotes(getVoteCountByGameId(rg.getGameId()));
        }
        return resembleGames;
    }

    @Override
    public int getVoteCountByGameId(int gameId) {
        return this.resembleGameRepoDB.getVoteCountByGameId(gameId);
    }
    
    @Override
    public boolean registerResembleGameVote(String usermail, int gameId){
        if(this.resembleGameRepoDB.hasUserVotedResembleGame(usermail, gameId)>0){
            return false; 
        }else{
            this.resembleGameRepoDB.registerResembleGameVote(usermail, gameId); 
        }
        return true; 
    }
    
    @Override
    public boolean makeResembleGameExercise(int gameId){
        return this.resembleGameRepoDB.makeResembleGameExercise(gameId); 
    }
    
    @Override
    public boolean makeResembleGameExerciseExtra(int gameId){
        return this.resembleGameRepoDB.makeResembleGameExerciseExtra(gameId); 
    }
    
    @Override
    public boolean removeResembleGameFromExercise(int gameId){
        return this.resembleGameRepoDB.removeResembleGameFromExercise(gameId); 
    }
}

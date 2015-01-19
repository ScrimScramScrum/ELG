
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public class ResembleGameRepoMock implements ResembleGameRepo{
    
    public ResembleGame getResembleGame(int gameNumber){
        ArrayList<Integer> mockTaskNumbers = new ArrayList<>(); 
        mockTaskNumbers.add(1); 
        mockTaskNumbers.add(2); 
        mockTaskNumbers.add(3);
        
        return new ResembleGame(mockTaskNumbers, 1, "ET MAAL", "INFOASDASDASD", 3); 
    }

    @Override
    public ArrayList<ResembleGame> getAllResembleGames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public ArrayList<ResembleGame> getAllResembleGamesFromOving() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int sqlGetScoreFromFromResebleGameWithNameAndEmail(String gamename, String email) {
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
    public boolean updateUserResembleVote(String usermail, int gameId) {
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

    @Override
    public int hasUserVotedResembleGame(String usermail, int gameId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

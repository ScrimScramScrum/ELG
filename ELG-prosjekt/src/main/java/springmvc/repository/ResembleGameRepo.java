
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleGame;

public interface ResembleGameRepo {
    public ResembleGame getResembleGame(int gameNumber); 
    public ArrayList<ResembleGame> getAllResembleGames(); 
    public ArrayList<ResembleGame> getAllResembleGamesFromOving(); 
    public ArrayList<ResembleGame> getAllResembleGamesFromOvingExtra();
    public boolean insertResembleGame(String gameName, String info, String learningGoals, String difficulty, String creatorId);
    public ResembleGame getResemleGameByName(String gameName);
    public int sqlGetScoreFromFromResebleGameWithNameAndEmail(String gamename, String email);
    public ArrayList<ResembleGame> getAllResembleGamesNotInOving();
    public int getVoteCountByGameId(int gameId);
    public boolean registerResembleGameVote(String usermail, int gameId);
    public boolean updateUserResembleVote(String usermail, int gameId);
    public boolean makeResembleGameExercise(int gameId);
    public boolean makeResembleGameExerciseExtra(int gameId);
    public boolean removeResembleGameFromExercise(int gameId);
    public int hasUserVotedResembleGame(String usermail, int gameId);
}

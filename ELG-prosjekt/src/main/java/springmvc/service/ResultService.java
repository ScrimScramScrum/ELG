/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoice;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;
import springmvc.repository.ResultRepo;

/**
 *
 * @author eiriksandberg
 */
public class ResultService {
    
    private ResultRepo repo;
    
    @Autowired
        public void setRepository(ResultRepo repo){
        System.out.println("resultRepo.mock" + repo);
        this.repo=repo;
    }
    
    public boolean regMultiChoiceRes(String email, Double score, MultiChoice game){
         return repo.regMultiChoiceRes(email, score, game);
    }
    
    public int getMultiChoiceRes(String email, MultiChoice game){
        return repo.getMultiChoiceRes(email, game);
    }
    public boolean updateMultiResult(String email, double score, MultiChoice game){
        return repo.updateMultiResult(email, score, game);
    }
    
    public ArrayList<HighscoreDisplay> highscoreMC(MultiChoice game){
        return repo.highscoreMC(game);
    }
    
    public boolean regResembleGameRes(String email, Double score, ResembleGame game){
       return repo.regResembleGameRes(email, score, game);
    }
    
    public int getResembleGameRes(String email, ResembleGame game){
        return repo.getResembleGameRes(email, game);
    }
    
    public boolean updateResembleResult(String email, double score, ResembleGame game){
        return repo.updateResembleResult(email, score, game);
    }
    
    public ArrayList<HighscoreDisplay> highscoreRG(ResembleGame game){
        return repo.highscoreRG(game);
    }
    
    public int[] sortHighScores(ArrayList<HighscoreDisplay> hs) {
        int[] sorted = new int[5];
        for(HighscoreDisplay h : hs) {
            int score = h.getScore();
            if(score <= 20) sorted[0]++;
            else if(score <= 40) sorted[1]++;
            else if(score <= 60) sorted[2]++;
            else if(score <= 80) sorted[3]++;
            else sorted[4]++;
        }
        return sorted;
    } 
    
    public ArrayList <HighscoreDisplay> getCompletion(MultiChoice game){
        return repo.getCompletion(game);
    }
}

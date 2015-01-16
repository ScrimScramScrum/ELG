/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoice;
import springmvc.domain.Person;
import springmvc.domain.ResembleGame;

/**
 *
 * @author eiriksandberg
 */
public interface ResultRepo{
    
        public boolean regMultiChoiceRes(String email, Double score, MultiChoice game);
        
        public int getMultiChoiceRes(String email, MultiChoice game);
        
        public boolean updateMultiResult(String email, double score, MultiChoice game);
        
        public ArrayList<HighscoreDisplay> highscoreMC(MultiChoice game);
        
        public boolean regResembleGameRes(String email, Double score, ResembleGame game);
        
        public int getResembleGameRes(String email, ResembleGame game);
        
        public boolean updateResembleResult(String email, double score, ResembleGame game);
        
        public ArrayList<HighscoreDisplay> highscoreRG(ResembleGame game);
        
        public ArrayList <HighscoreDisplay> getCompletion(String classname);
        
        public ArrayList <HighscoreDisplay> getCompletionRG(String classname);
        
        public ArrayList <String> getAllOvinger();
        
        public ArrayList <String> getAllClasses(String email);
}

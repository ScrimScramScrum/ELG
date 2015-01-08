/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.service;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.repository.GameListRepo;

/**
 *
 * @author Jorgen
 */
public class GameListService {
    private GameListRepo repo; 
    
    @Autowired 
    public void setGameListRepo(GameListRepo repo){
        System.out.println("GameListService.setGameListRepo - autowired");
        this.repo = repo; 
    }
    public ArrayList<ResembleGame> getAllResembleGames(){
        return repo.getAllResembleGames(); 
    } 
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceGames(){
        return repo.getAllMultiChoiceGames();
    }
    
    public ResembleGame getResembleGame(int gameId){
        return repo.getResembleGame(gameId); 
    }
}

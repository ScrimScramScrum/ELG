/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;


public class MultiResult {
    int idResult;
    String email; 
    int idGame;
    int score; 

    public MultiResult(int idResult, String email, int idGame, int score) {
        this.idResult = idResult;
        this.email = email;
        this.idGame = idGame;
        this.score = score;
    }
    
    public MultiResult(){
        
    }

    
    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int Score) {
        this.score = score;
    }
    
    
    
}

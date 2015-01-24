/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

/**
 *
 * @author eiriksandberg
 */
public class HighscoreDisplay {
    private String fname;
    private String lname;
    private String classname;
    private int score;

    public HighscoreDisplay(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }
    
    public HighscoreDisplay(String fname, String lname, int score) {
        this.fname = fname;
        this.lname = lname;
        this.score = score;
    }

    public HighscoreDisplay() {}

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public int getScore() {
        return score;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public String toString(){
        return fname + " " + lname + " Score: " + score;
    }
    
    public void setClassname(String classname){
        this.classname=classname;
    }

    public String getClassname() {
        return classname;
    }
    
    
    
    
}

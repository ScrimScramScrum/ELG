/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author eiriksandberg
 */
public class MultiChoice implements Serializable {
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private String name;
    private int counter = 0;
    private boolean[] result;
    int size;
    
    public MultiChoice(){};
    
    public MultiChoice(ArrayList<Exercise> exercises, String name){
        this.exercises = exercises;
        this.name = name;
    }
    
    public int current(){
        return counter;
    }
    
    public void initMC(){
        this.result=new boolean[exercises.size()];
    }
    
    public Exercise getCurrent(){
        return exercises.get(counter);
    }
    
    public Exercise getNextExercise(){
        if (!lastExercise()){
            Exercise e = exercises.get(counter);
            counter++;
            return e;
        }
        return null;
    }
    
    public boolean lastExercise(){
        if(current() != exercises.size()){
            return false;
        }
        return true;
    }
    
    public ArrayList getExercises(){
        return exercises;
    }
    
    public String getName(){
        return name;
    }
    
    public void resetCurrent(){
        counter = 0;
    }
    
    public double getResult(){
        double right = 0;
        for (int i = 0; i<result.length;i++){
            if(result[i] == true){
                right++;
            }
        }
        double ans = right/result.length;
        return ans;
    }
    
    public void setResult(int index, boolean r){
        result[index] = r; 
    }

    
}

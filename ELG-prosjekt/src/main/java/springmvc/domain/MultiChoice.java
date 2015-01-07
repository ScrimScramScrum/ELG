/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.domain;

import java.util.ArrayList;

/**
 *
 * @author eiriksandberg
 */
public class MultiChoice {
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private String name;
    private int numberOfExercises = 0;
    private int counter = 0;
    
    public MultiChoice(){};
    
    public MultiChoice(ArrayList<Exercise> exercises, String name){
        this.exercises = exercises;
        this.name = name;
        this.numberOfExercises=exercises.size();

    }
    
    public boolean addExercise(Exercise opg){
        exercises.add(opg);
        numberOfExercises++;
        return true;
    }
    
    public int current(){
        return counter;
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
        if(current() != exercises.size()-1){
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
    
}

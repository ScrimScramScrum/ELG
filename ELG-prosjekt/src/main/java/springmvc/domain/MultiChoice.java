/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author eiriksandberg
 */
public class MultiChoice implements Serializable {
    private ArrayList<Exercise> exercises = new ArrayList<>();
    
    @NotEmpty
    private String name;
    
    private int counter = 0;
    private boolean[] result;
    int gameid;
    String creator;
    
    @NotEmpty
    String info;
    
    @NotEmpty
    String learningGoals;
    int difficulty;

    public MultiChoice() {}
    
    public MultiChoice(ArrayList<Exercise> exercises, String name) {
        this.exercises = exercises;
        this.name = name;
    }
    
    public MultiChoice(String name, String info, String learningGoals, int difficulty) {
        this.name = name;
        this.info = info;
        this.learningGoals = learningGoals;
        this.difficulty = difficulty;
    }

    public MultiChoice(ArrayList<Exercise> exercises, String name, String creator, String info, String learningGoals, int difficulty) {
        this.exercises = exercises;
        this.name = name;
        this.creator = creator;
        this.info = info;
        this.learningGoals = learningGoals;
        this.difficulty = difficulty;
    }

    public int current() {
        return counter;
    }

    public void initMC() {
        this.result = new boolean[exercises.size()];
    }

    public Exercise getCurrent() {
        return exercises.get(counter);
    }

    public Exercise getNextExercise() {
        if (!lastExercise()) {
            Exercise e = exercises.get(counter);
            counter++;
            return e;
        }
        return null;
    }

    public boolean lastExercise() {
        if (current() != exercises.size()) {
            return false;
        }
        return true;
    }

    public ArrayList getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getName() {
        return name;
    }

    public void resetCurrent() {
        counter = 0;
    }

    public double getResult() {
        double right = 0;
        for (int i = 0; i < result.length; i++) {
            if (result[i] == true) {
                right++;
            }
        }
        double ans = (right / getNumberOfQuestions()) * 100;
        return ans;
    }

    public void setResult(int index, boolean r) {
        result[index] = r;
    }

    public void setName(String newname) {
        this.name = newname;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public int getNumberOfQuestions() {
        return exercises.size();
    }

    public int getCounter() {
        return counter;
    }

    public String getCreator() {
        return creator;
    }

    public String getInfo() {
        return info;
    }

    public String getLearningGoals() {
        return learningGoals;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setLearningGoals(String learningGoals) {
        this.learningGoals = learningGoals;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    
}

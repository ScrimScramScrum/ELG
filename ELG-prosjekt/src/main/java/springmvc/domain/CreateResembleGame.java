
package springmvc.domain;

import java.io.Serializable;
import java.util.ArrayList;


public class CreateResembleGame implements Serializable{
    private ResembleGame resembleGame; 
    private ArrayList<ResembleTask> resembleTasks; 
    
    public CreateResembleGame(){
        this.resembleTasks = new ArrayList<>(); 
    }

    public ArrayList<ResembleTask> getResembleTasks() {
        return resembleTasks;
    }

    public void setResembleTasks(ArrayList<ResembleTask> resembleTasks) {
        this.resembleTasks = resembleTasks;
    }
    
    public void addResembleTask(ResembleTask rt){
        this.resembleTasks.add(rt); 
    }

    public ResembleGame getResembleGame() {
        return resembleGame;
    }

    public void setResembleGame(ResembleGame resembleGame) {
        this.resembleGame = resembleGame;
    }
}

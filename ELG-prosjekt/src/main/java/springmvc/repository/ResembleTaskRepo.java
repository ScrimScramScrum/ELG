
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleTask;

public interface ResembleTaskRepo {
    public ResembleTask getResembleTask(int taskNumber); 
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers); 
    public ArrayList<ResembleTask> getResembleTasksByGameId(int gameId);   
    public boolean insertResembleTask(String taskText, String solutionHTML, String solutionCSS, String startingHTML, String startingCSS,  int width, int height, int idGame); 
}

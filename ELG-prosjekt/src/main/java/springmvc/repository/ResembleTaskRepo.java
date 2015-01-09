
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleTask;

public interface ResembleTaskRepo {
    public ResembleTask getResembleTask(int taskNumber); 
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers); 
    public ArrayList<ResembleTask> getResembleTasksByGameId(int gameId);   
}

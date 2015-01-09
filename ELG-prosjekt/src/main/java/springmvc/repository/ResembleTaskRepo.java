
package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleTask;

public interface ResembleTaskRepo {
    public ResembleTask getResembleTask(int taskNumber); 
  //  public boolean registerResembleTask(); 
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers); 
    
}

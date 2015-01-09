package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.ResembleTask;

public class ResembleTaskRepoMock implements ResembleTaskRepo{
    private String[] mockTaskText = new String[3];
    private String[] mockSolutionHTML = new String[3];
    private String[] mockSolutionCSS = new String[3]; 
    private String[] mockStartingHTML = new String[3]; 
    private String[] mockStartingCSS = new String[3]; 
    private int mockWidth[] = new int[3];
    private int mockHeight[] = new int[3];
    
    public ResembleTaskRepoMock() {
        mockTaskText[0] = "Du skal sette bakgrunnsfargen på siden til fargen #b0c4de<br>Du skal også lage en tabell med rød farge og to kolonner der det står 'hei' (border='1')";
        mockTaskText[1] = "Her skal vi teste noe nr 1";
        mockTaskText[2] = "Her skal vi teste noe nr 2";

        mockSolutionHTML[0] = "<!DOCTYPE html><html><body>Hei!<br><table border='1'><tr><td>hei</td><td>hei</td></tr></table></body></html>";
        mockSolutionHTML[1] = "<html><body>supsup</body></html>";
        mockSolutionHTML[2] = "<html><body>TEST NR 1<br>TEST NR 2<br>TEST NR 3</body></html>";
        
        mockSolutionCSS[0] = "body {background-color: #b0c4de;} table {background-color: red}";
        mockSolutionCSS[1] = "";
        mockSolutionCSS[2] = "";
        
        mockStartingHTML[0] = "<!DOCTYPE html><html><body>Hei!</body></html>";
        mockStartingHTML[1] = "<html></html>";
        mockStartingHTML[2] = "";
        
        mockStartingCSS[0] = "body {}";
        mockStartingCSS[1] = "";
        mockStartingCSS[2] = "";
        
        mockWidth[0] = 100;
        mockWidth[1] = 200;
        mockWidth[2] = 150;
        
        mockHeight[0] = 80;
        mockHeight[1] = 150;
        mockHeight[2] = 120;
    }
    
    public ResembleTask getResembleTask(int taskNumber){
        if(taskNumber == 1) return new ResembleTask( taskNumber,  mockTaskText[0], mockSolutionHTML[0], mockSolutionCSS[0], mockStartingHTML[0], mockStartingCSS[0], mockWidth[0], mockHeight[0]); 
        else if(taskNumber == 2) return new ResembleTask( taskNumber,  mockTaskText[1], mockSolutionHTML[1], mockSolutionCSS[1], mockStartingHTML[1], mockStartingCSS[1], mockWidth[1], mockHeight[1]);
        else return new ResembleTask( taskNumber,  mockTaskText[2], mockSolutionHTML[2], mockSolutionCSS[2], mockStartingHTML[2], mockStartingCSS[2], mockWidth[2], mockHeight[2]);
    }

    @Override
    public ArrayList<ResembleTask> getResembleTasks(ArrayList<Integer> taskNumbers) {
        ArrayList<ResembleTask> list = new ArrayList<>(); 
        list.add(new ResembleTask( 1,  mockTaskText[0], mockSolutionHTML[0], mockSolutionCSS[0], mockStartingHTML[0], mockStartingCSS[0], mockWidth[0], mockHeight[0]));
        list.add(new ResembleTask( 2,  mockTaskText[1], mockSolutionHTML[1], mockSolutionCSS[1], mockStartingHTML[1], mockStartingCSS[1], mockWidth[1], mockHeight[1]));
        list.add(new ResembleTask( 3,  mockTaskText[2], mockSolutionHTML[2], mockSolutionCSS[2], mockStartingHTML[2], mockStartingCSS[2], mockWidth[2], mockHeight[2]));
        
        ArrayList<ResembleTask> list2 = new ArrayList<>(); 
        for(Integer i : taskNumbers){
            if(list.get(i).equals(i)){
                list2.add(list.get(i)); 
            }
        }
        return list2; 
    }

    @Override
    public ArrayList<ResembleTask> getResembleTasksByGameId(int gameId) {
    ArrayList<ResembleTask> list = new ArrayList<>(); 
        list.add(new ResembleTask( 1,  mockTaskText[0], mockSolutionHTML[0], mockSolutionCSS[0], mockStartingHTML[0], mockStartingCSS[0], mockWidth[0], mockHeight[0]));
        list.add(new ResembleTask( 2,  mockTaskText[1], mockSolutionHTML[1], mockSolutionCSS[1], mockStartingHTML[1], mockStartingCSS[1], mockWidth[1], mockHeight[1]));
        list.add(new ResembleTask( 3,  mockTaskText[2], mockSolutionHTML[2], mockSolutionCSS[2], mockStartingHTML[2], mockStartingCSS[2], mockWidth[2], mockHeight[2]));
        return list; 
    }
}

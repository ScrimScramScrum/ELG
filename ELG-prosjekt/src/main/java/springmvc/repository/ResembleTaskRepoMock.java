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
        mockSolutionHTML[2] = "<html><body><div id='top'>This is a menu<hr></div><div id='mid'><div id='left'><a href='#test'>test</a><br><a href='#test'>test2</a><br>Test input: <input /><div id='bottom_left'>L<br>E<br>F<br>T</div></div><div id='right'><ul><li>Test 1</li><li>Test 2</li><li>Test 3</li></ul><img src='http://intuitiontosucceed.academy/wp-content/uploads/2014/06/K2_Mount_Godwin_Austen_Chogori_Savage_Mountain1-1080x675.jpg' alt='Mountain View' style='width:304px;height:228px'></div></div></body></html>";
        
        mockSolutionCSS[0] = "body {background-color: #b0c4de;} table {background-color: red}";
        mockSolutionCSS[1] = "";
        mockSolutionCSS[2] = "body {color: white;background-color: red;font-size: 10px;}#top {background-color: gray;height: 10%;font-size: 20px;text-align: center;}#left {float: left;background-color: yellow;width: 20%;height: 50%;text-align: center;color: black;position: relative;}#right {float: left;color: yellow;background-color: blue;width: 80%;height: 50%;font-size: 15px;}#bottom_left {position: absolute;text-align: center;bottom: 0;left: 45%;font-size: 15px;}hr {width: 50%;}";
        
        mockStartingHTML[0] = "<!DOCTYPE html><html><body>Hei!</body></html>";
        mockStartingHTML[1] = "<html></html>";
        mockStartingHTML[2] = "";
        
        mockStartingCSS[0] = "body {}";
        mockStartingCSS[1] = "";
        mockStartingCSS[2] = "";
        
        mockWidth[0] = 100;
        mockWidth[1] = 200;
        mockWidth[2] = 300;
        
        mockHeight[0] = 80;
        mockHeight[1] = 150;
        mockHeight[2] = 250;
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

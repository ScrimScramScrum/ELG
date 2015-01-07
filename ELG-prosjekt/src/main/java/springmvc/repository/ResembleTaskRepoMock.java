package springmvc.repository;

import springmvc.domain.ResembleTask;

public class ResembleTaskRepoMock implements ResembleTaskRepo{
    private String mockTaskText = "Du skal sette bakgrunnsfargen på siden til fargen #b0c4de<br>Du skal også lage en tabell med rød farge og to kolonner der det står 'hei' (border='1')"
    private String mockSolutionHTML = "<!DOCTYPE html><html><body>Hei!<br><table border='1'><tr><td>hei</td><td>hei</td></tr></table></body></html>"; 
    private String mockSolutionCSS = "body {background-color: #b0c4de;} table {background-color: red}";  
    private String mockStartingHTML = "<!DOCTYPE html><html><body>Hei!</body></html>";  
    private String mockStartingCSS = "body {}"; 
    private int mockWidth = 100; 
    private int mockHeight = 80; 
    
    public ResembleTask getResembleTask(int taskNumber){
        return new ResembleTask( taskNumber,  mockTaskText, mockSolutionHTML, mockSolutionCSS, mockStartingHTML, mockStartingCSS, mockWidth, mockHeight); 
    }
}

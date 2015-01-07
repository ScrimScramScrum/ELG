package springmvc.repository;

import springmvc.domain.ResembleTask;

public class ResembleTaskRepoMock implements ResembleTaskRepo{
    
    private String mockSolutionHTML = "<!DOCTYPE html><html><body>Hei!<br><table border='1'><tr><td>hei</td><td>hei</td></tr></table></body></html>"; 
    private String mockSolutionCSS = "body {background-color: #b0c4de;} table {background-color: red}";  
    private String mockStartingHTML = "<!DOCTYPE html><html><body>Hei!</body></html>";  
    private String mockStartingCSS = ""; 
    private int mockWidth = 100; 
    private int mockHeight = 80; 
    
    public ResembleTask getResembleTask(int taskNumber){
        return new ResembleTask( taskNumber,  mockSolutionHTML, mockSolutionCSS, mockStartingHTML, mockStartingCSS, mockWidth, mockHeight); 
    }
}

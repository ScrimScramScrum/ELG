package springmvc.repository;

import springmvc.domain.ResembleTask;

public class ResembleTaskRepoMock implements ResembleTaskRepo{
    
    private String mockSolutionHTML = ""; 
    private String mockSolutionCSS = "";  
    private String mockStartingHTML = "";  
    private String mockStartingCSS = ""; 
    private int mockWidth = 0; 
    private int mockHeight = 0; 
    
    public ResembleTask getResembleTask(int taskNumber){
        return new ResembleTask( taskNumber,  mockSolutionHTML, mockSolutionCSS, mockStartingHTML, mockStartingCSS, mockWidth, mockHeight); 
    }
}

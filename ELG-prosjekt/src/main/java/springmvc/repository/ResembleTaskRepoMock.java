/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.repository;
import springmvc.domain.ResembleTask;
/**
 *
 * @author Jorgen
 */
public class ResembleTaskRepoMock implements ResembleTaskRepo{
    
    private String mockSolutionHTML; 
    private String mockSolutionCSS; 
    private String mockStartingHTML; 
    private String mockStartingCSS;
    
    public ResembleTask getResembleTask(int taskNumber){
        return new ResembleTask( taskNumber,  mockSolutionHTML, mockSolutionCSS, mockStartingHTML, mockStartingCSS); 
    }
}

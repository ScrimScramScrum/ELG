/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import springmvc.domain.MultiChoice;
import springmvc.domain.Person;

/**
 *
 * @author eiriksandberg
 */
public class ResultRepoMock implements ResultRepo {

    @Override
    public boolean regMultiChoiceRes(String email, Double score, MultiChoice game) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

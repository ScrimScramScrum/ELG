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
public interface ResultRepo {
    
        public boolean regMultiChoiceRes(String email, Double score, MultiChoice game);
}

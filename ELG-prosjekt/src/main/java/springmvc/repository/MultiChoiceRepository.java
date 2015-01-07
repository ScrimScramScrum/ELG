/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import springmvc.domain.MultiChoice;

/**
 *
 * @author eiriksandberg
 */
public interface MultiChoiceRepository {
 
    public MultiChoice getMultiChoice(MultiChoice game);
}

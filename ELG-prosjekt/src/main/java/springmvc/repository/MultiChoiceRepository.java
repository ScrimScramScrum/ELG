/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.repository;

import java.util.ArrayList;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.MultiResult;

/**
 *
 * @author eiriksandberg
 */
public interface MultiChoiceRepository {
 
    public MultiChoice getMultiChoice(String name);
    public ArrayList<MultiChoice> getAllMultiChoiceGames(); 
    public ArrayList<MultiChoiceInfo> getAllMultiChoiceInfo();
    public MultiChoiceInfo getMultiChoiceInfo(String gameId);
    public MultiResult getMultiChoiceAndUsername(String gamename, String email);
}

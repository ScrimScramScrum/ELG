/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import springmvc.domain.Exercise;
import springmvc.domain.MultiChoice;
import springmvc.service.MultiChoiceService;
import springmvc.test.MultiChoiceTest;

/**
 *
 * @author eiriksandberg
 */
@Controller
@SessionAttributes ("spillet")
public class MultiChoiceController {
    
    
    @Autowired
    private MultiChoiceService s;
    
    @RequestMapping(value = "multi", method = RequestMethod.POST)
    public String showMultiChoice(Model model, @RequestParam("gamename") String name){
        MultiChoice mc = s.getMultiChoice(name); 
        model.addAttribute("spillet", mc); 
        return "multichoice"; 
    }
    
    @RequestMapping(value = "nextTask")
    public String nextTask(Model model, @ModelAttribute("spillet") MultiChoice mc, String value, HttpServletRequest request){
        if (request.getParameter("button") != null){
            String knapp = request.getParameter("button");
            mc.getCurrent().checkAnswer(knapp);
            }
        mc.getNextExercise();
        if(mc.lastExercise()==true){
            model.addAttribute("result", mc.finnResultat());
            mc.resetCurrent();
            return "result";
            
        }
        return "multichoice"; 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package springmvc.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.Exercise;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoice;
import springmvc.domain.User;
import springmvc.service.MultiChoiceService;
import springmvc.service.PersonService;
import springmvc.service.ResultService;
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
    
    @Autowired
    private ResultService r;
    
    /*@ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req, Exception exception){
        System.out.println("Kommer hit");
        ModelAndView mav = new ModelAndView(); 
        mav.addObject("Melding", "feilmelding.generell"); 
        mav.addObject("unntak", exception); 
        mav.setViewName("about");
        return mav; 
    } */

    
    @RequestMapping(value = "multi", method = RequestMethod.POST)
    public String showMultiChoice(Model model, @RequestParam("gamename") String name){
        MultiChoice mc = s.getMultiChoice(name); 
        mc.initMC();
        model.addAttribute("spillet", mc); 
        return "multichoice"; 
    }
    
    @RequestMapping(value = "createmulti")
    public ModelAndView showCreateMultiChoice(ModelAndView mav){
        mav.setViewName("createmulti");
        return mav; 
    }
    
    @RequestMapping(value = "number", method = RequestMethod.POST)
    public ModelAndView showCreateMultiChoice(ModelAndView mav, HttpServletRequest request){
        String number = request.getParameter("number");
        int n = Integer.parseInt(number);
        mav.addObject("numberOfTasks", n);
        mav.setViewName("createmulti");
        return mav; 
    }
    
    
    @RequestMapping(value = "nextTask", method = RequestMethod.POST)
    public String nextTask(HttpSession session, Model model, @ModelAttribute(value = "spillet") MultiChoice mc, String value, HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        if (user == null){
            return "firstLogin"; 
        }
        if (request.getParameter("button") != null){
            String button = request.getParameter("button");
            mc.setResult(mc.current(), mc.getCurrent().checkAnswer(button));
            }
        mc.getNextExercise();
        if(mc.lastExercise()==true){
            model.addAttribute("result", mc.getResult());
            //******DETTE HER HER FOR Å REGISTRERE RESULTAT I DATABASEN!!!*****
            Double score = mc.getResult();            
            String k = user.getEmail();
            int d = r.getMultiChoiceRes(k, mc);
            if(d == 0){
                r.regMultiChoiceRes(k, score, mc);
            } else if(score > d){
                r.updateMultiResult(k, score, mc);
            }
            ArrayList<HighscoreDisplay> hs = r.highscoreMC(mc);
            System.out.println(hs.size());
            String melding = "";
            for (int i = 0; i<hs.size(); i++){
                melding += hs.get(i).getFname() + " " + hs.get(i).getLname() + " " + hs.get(i).getScore() +"\n";
            }
            model.addAttribute("highscorelist", melding);
            mc.resetCurrent();
            return "result";
            
        }
        return "multichoice"; 
    }
}

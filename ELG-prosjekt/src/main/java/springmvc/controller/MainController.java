/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.service.GameListService;

/**
 *
 * @author Jorgen
 */
@Controller
public class MainController {
    
    @Autowired
    private GameListService gameListService;  
    
    @RequestMapping(value = "*")
    public String showIndex(Model model){
        //model.addAttribute("melding", "melding");
        return "index"; 
    }
    
    @RequestMapping(value = "choosegame")
    public ModelAndView chooseGame(ModelAndView mav){
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames(); 
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceGames(); 
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames); 
        mav.setViewName("chooseGame");
        return mav; 
    }   
}

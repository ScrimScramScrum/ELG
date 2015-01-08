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
import org.springframework.web.bind.annotation.RequestParam;
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
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames); 
        mav.setViewName("chooseGame");
        return mav; 
    }
    
    @RequestMapping(value = "choosegame", method = RequestMethod.POST)
    public ModelAndView chooseGame(ModelAndView mav, @RequestParam("gameid") String id){
        int resemble = 0;
        String info = "test...";
        MultiChoiceInfo multiTemp = null;
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            // add info here
        } catch(Exception e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceGame(id);
            mav.addObject("multiChoiceInfo", multiTemp);
            // or info here
            System.out.println("MultiChoice");
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames(); 
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceGames();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGame");
        return mav; 
    } 
}

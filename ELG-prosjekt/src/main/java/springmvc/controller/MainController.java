/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.Login;
import springmvc.domain.MultiChoice;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceImpl;
import springmvc.service.ResultService;
import springmvc.ui.SendNewPassword;

/**
 *
 * @author Jorgen
 */
@Controller
public class MainController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private ResultService r;

    @RequestMapping(value = "index")
    public String showIndex(Model model) {
        //model.addAttribute("melding", "melding");
        return "index";
    }
    
    @RequestMapping(value = "*")
    public String person(@ModelAttribute Login login, @ModelAttribute("sendNewPassword") SendNewPassword sendNewPassword) {
        return "firstLogin";
    }
    

    @RequestMapping(value = "about")
    public String showAbout(Model model) {
        //model.addAttribute("melding", "melding");
        return "about";
    }

    /*
    @RequestMapping(value = "highscore")
    public String showHighscore(Model model, HttpSession session) {
        //model.addAttribute("melding", "melding");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("user = null");
        }
        return ((User) session.getAttribute("user")) == null ? "index" : ((User) session.getAttribute("user")).isInLogged() ? "highscore" : "index";
        // return ((User)session.getAttribute("user")).isInLogged() ? "highscore" : "index"; 
        // return "highscore"; 
    }*/

    @RequestMapping(value = "choosegame")
    public ModelAndView chooseGame(ModelAndView mav) {
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGame");
        return mav;
    }

    @RequestMapping(value = "choosegame", method = RequestMethod.POST)
    public ModelAndView chooseGame(ModelAndView mav, @RequestParam("gameid") String id) {
        int resemble = 0;
        String info = "test...";
        MultiChoiceInfo multiTemp = null;
        ResembleGame resembleTemp = null;
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            ArrayList<Integer> task_numbers = resembleTemp.getTaskNumbers();
            ArrayList<ResembleTask> temp_tasks = gameListService.getResembleTasks(task_numbers);
            mav.addObject("tasks", temp_tasks);
            mav.addObject("resembleInfo", resembleTemp);
            // add info here
        } catch (NumberFormatException e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceInfo(id);
            mav.addObject("multiChoiceInfo", multiTemp);
            // or info here
            System.out.println("MultiChoice");
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGame");
        return mav;
    }

    @RequestMapping(value = "highscore")
    public ModelAndView chooseGameHighscore(ModelAndView mav) {
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGameHighscore");
        return mav;
    }

    @RequestMapping(value = "choosegameHighscore", method = RequestMethod.POST)
    public ModelAndView chooseGameHighscore(ModelAndView mav, @RequestParam("gameid") String id) {
        int resemble = 0;
        MultiChoice multiTemp = null;
        ResembleGame resembleTemp = null;
        ArrayList<HighscoreDisplay> hs = new ArrayList<HighscoreDisplay>();
        try {
            int a = Integer.parseInt(id);
            resemble = 1;
            resembleTemp = gameListService.getResembleGame(a);
            hs = r.highscoreRG(resembleTemp);
            mav.addObject("highscore", hs);
        } catch (NumberFormatException e) {
            resemble = 2;
            multiTemp = gameListService.getMultiChoiceGame(id);
            hs = r.highscoreMC(multiTemp);
            mav.addObject("highscore", hs);
        }
        mav.addObject("gametype", resemble);
        // use session instead of getting all games every time a game get clicked?
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGames();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfo();
        mav.addObject("gamenr", id);
        //mav.addObject("info", info);
        mav.addObject("sortedScores", r.sortHighScores(hs));
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGames);
        mav.setViewName("chooseGameHighscore");
        return mav;
    }
}

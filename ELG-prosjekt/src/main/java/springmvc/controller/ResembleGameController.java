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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceImpl;
import springmvc.service.ResembleTaskService;
import springmvc.service.ResultService;

@Controller
@SessionAttributes("resembleGame")
public class ResembleGameController {
    
    @Autowired
    private ResembleTaskService resembleTaskService; 
    
    @Autowired 
    private GameListService gameListService; 
    
    @Autowired
    private ResultService r;
  /*  
    @RequestMapping(value = "resemblegame")
    public ModelAndView resembleGame(ModelAndView mav){
        ResembleGame resembleGame = resembleGameService.getResembleGame(1);
        mav.addObject("resembleGame", resembleGame);
        mav.addObject("resembleTask", resembleTaskService.getResembleTask(resembleGame.getCurrentTask())); 
        mav.setViewName("resembleGame");
        return mav; 
    }*/
    
    @RequestMapping(value = "resemblegame", method = RequestMethod.POST)
    public ModelAndView resembleGame(ModelAndView mav, @RequestParam("gameid") String id){
        int gameid = Integer.parseInt(id);
        ResembleGame resembleGame = gameListService.getResembleGame(gameid);
        mav.addObject("resembleGame", resembleGame);
        mav.addObject("resembleTask", resembleTaskService.getResembleTask(resembleGame.getCurrentTask())); 
        mav.setViewName("resembleGame");
        return mav; 
    }
    
    @RequestMapping(value ="nextresembletask", method = RequestMethod.POST)
    public ModelAndView resembleGameNext(ModelAndView mav, @ModelAttribute(value = "resembleGame") ResembleGame resembleGame, HttpServletRequest req) {
        resembleGame.setTaskNumberScore(resembleGame.getCurrentTask(), Double.parseDouble(req.getParameter("score")));
        System.out.println("NESTE SCORE: " + Double.parseDouble(req.getParameter("score")));
        resembleGame.setCurrentTask(resembleGame.getNextTask());
        mav.addObject("resembleTask", resembleTaskService.getResembleTask(resembleGame.getCurrentTask())); 
        mav.setViewName("resembleGame"); 
        return mav;
        // TODO: ADD IF ELSE (lastelement in list -> setviewname index???? 
    }
    
    @RequestMapping(value ="finishgame")
    public String resembleGameFinish(HttpSession session, ModelAndView mav, @ModelAttribute(value = "resembleGame") ResembleGame resembleGame, HttpServletRequest req) {
        resembleGame.setTaskNumberScore(resembleGame.getCurrentTask(), Double.parseDouble(req.getParameter("score")));
        System.out.println("TOTAL SCORE: " + resembleGame.getTotalScore());
        Double score = (resembleGame.getTotalScore()/resembleGame.numberOfTasks());            
            User user = (User)session.getAttribute("user");
            String k = user.getEmail();
            int d = r.getResembleGameRes(k, resembleGame);
            if(d == 0){
                r.regResembleGameRes(k, score, resembleGame);
            } else if(score > d){
                r.updateResembleResult(k, score, resembleGame);
            }
            ArrayList<HighscoreDisplay> hs = r.highscoreRG(resembleGame);
            System.out.println(hs.size());
            String melding = "";
            for (int i = 0; i<hs.size(); i++){
                melding += hs.get(i).getFname() + " " + hs.get(i).getLname() + " " + hs.get(i).getScore() +"\n";
            }
            mav.addObject("highscorelist", melding);
        // resembleGameService.updatePoints(Person person);
        return "finishgame";//finishgame
    }
}

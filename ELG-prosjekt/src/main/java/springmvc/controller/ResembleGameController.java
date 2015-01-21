/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.CreateResembleGame;
import springmvc.domain.HighscoreDisplay;
import springmvc.domain.MultiChoiceInfo;
import springmvc.domain.ResembleGame;
import springmvc.domain.ResembleTask;
import springmvc.domain.User;
import springmvc.service.GameListService;
import springmvc.service.GameListServiceImpl;
import springmvc.service.ResembleTaskService;
import springmvc.service.ResultService;

@Controller
@SessionAttributes({"resembleGame", "createResembleGame"})
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
    public ModelAndView resembleGameFinish(ModelAndView mav, HttpSession session, @ModelAttribute(value = "resembleGame") ResembleGame resembleGame, HttpServletRequest req) {
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
        
        
        ArrayList<ResembleGame> resembleGames = gameListService.getAllResembleGamesFromOving();
        ArrayList<MultiChoiceInfo> multiChoiceGames = gameListService.getAllMultiChoiceInfoFromOving();

        ArrayList<ResembleGame> resembleGamesExtra = gameListService.getAllResembleGamesFromOvingExtra();
        ArrayList<MultiChoiceInfo> multiChoiceGamesExtra = gameListService.getAllMultiChoiceInfoFromOvingExtra();

        System.out.println("ChooseGame Rdy for multiChoiceGamesWithApproved");
        //add a function to update the multiChoiceGames and resembleGames lists to a version that 	says if its done or not. 
        //updateApprovedGames(user, resembleGames, multiChoiceGames);
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApproved = gameListService.updateApprovedMultiChoiceGames(multiChoiceGames, user);
        ArrayList<ResembleGame> resembleGamesWithApproved = gameListService.updateApprovedResembleGames(resembleGames, user);
        ArrayList<MultiChoiceInfo> multiChoiceGamesWithApprovedExtra = gameListService.updateApprovedMultiChoiceGames(multiChoiceGamesExtra, user);
        ArrayList<ResembleGame> resembleGamesWithApprovedExtra = gameListService.updateApprovedResembleGames(resembleGamesExtra, user);

        if (multiChoiceGamesWithApproved == null) {
            int resemble = 0;
            mav.addObject("gametype", resemble);
            mav.addObject("resembleGames", resembleGames);
            mav.addObject("multiChoiceGames", multiChoiceGames);
            mav.addObject("resembleGamesExtra", resembleGamesWithApprovedExtra);
            mav.addObject("multiChoiceGamesExtra", multiChoiceGamesWithApprovedExtra);
            mav.setViewName("finishgame");
            return mav;
        }

        int resemble = 0;
        mav.addObject("gametype", resemble);
        mav.addObject("resembleGames", resembleGames);
        mav.addObject("multiChoiceGames", multiChoiceGamesWithApproved);
        mav.addObject("resembleGamesExtra", resembleGamesExtra);
        mav.addObject("multiChoiceGamesExtra", multiChoiceGamesExtra);
        mav.setViewName("finishgame");
        return mav;
    }
    
    @RequestMapping(value = "createresemblegame")
    public ModelAndView showCreateResembleGame(ModelAndView mav, HttpSession session){
         User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
            return mav; 
        }
        CreateResembleGame createResembleGame = new CreateResembleGame(); 
        createResembleGame.setResembleGame(new ResembleGame());
        mav.addObject("createResembleGame", createResembleGame); 
        mav.setViewName("createresemblegame");
        return mav; 
    }
    
    @RequestMapping(value = "createresembletask")
    public ModelAndView createResembleTaskView(ModelAndView mav, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user == null) {
            mav.setViewName("firstLogin");
            return mav; 
        }
        ResembleTask resembleTask = new ResembleTask(); 
        mav.addObject("createResembleTask", resembleTask);
        mav.setViewName("createresembletask");
        return mav; 
    }
    
    @RequestMapping(value="createresembletask", method = RequestMethod.POST)
    public String createResembleTask(ModelAndView mav, HttpServletRequest req, @ModelAttribute(value="createResembleTask") ResembleTask resembleTask, @ModelAttribute(value = "createResembleGame") CreateResembleGame createResembleGame){
        createResembleGame.addResembleTask(resembleTask);
        return "createresemblegame";
    }
    
    @RequestMapping(value = "submitresemblegame", method = RequestMethod.POST)
    public ModelAndView submitResembleGame(ModelAndView mav, @Valid @ModelAttribute(value = "createResembleGame") CreateResembleGame createResembleGame, BindingResult error, HttpSession session, @RequestParam("button") String button){
        User user = (User) session.getAttribute("user");
        System.out.println("ERROR: :D:D:D:D:D:D " + error.getFieldErrors());
        if (user == null) {
            mav.setViewName("firstLogin");
            return mav;         
        }
        if(button.equals("Lag deloppgave")){
            ResembleTask resembleTask = new ResembleTask(); 
            mav.addObject("createResembleTask", resembleTask);
            mav.setViewName("createresembletask");
            return mav;
        }
        
        if(error.hasErrors()){
            System.out.println("ERROR HAR ERRORS::: ASDAD***************");
            mav.setViewName("createresemblegame");
            return mav; 
        }
        ResembleGame resembleGame = createResembleGame.getResembleGame(); 
        resembleGame.setCreatorId(user.getEmail());
        
        ArrayList<ResembleTask> resembleTasks = createResembleGame.getResembleTasks();
        
        try{
            ResembleGame rgFromDB = gameListService.getResemleGameByName(resembleGame.getGamename());
            mav.addObject("message", "error.duplicateresemblename");
            mav.setViewName("createresemblegame");
            return mav; 
        } catch(EmptyResultDataAccessException e){
            gameListService.insertResembleGame(resembleGame.getGamename(), resembleGame.getInfo(), resembleGame.getLearningGoal(), ""+resembleGame.getDifficulty(), resembleGame.getCreatorId());
            int gameId = gameListService.getResemleGameByName(resembleGame.getGamename()).getGameId();
            resembleTaskService.insertResembleTasks(resembleTasks, gameId);
            mav.setViewName("about"); 
            return mav; 
        }
    }
}

/*@RequestMapping(value ="finishgame")
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
    }*/ 
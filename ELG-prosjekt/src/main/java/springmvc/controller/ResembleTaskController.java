/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import springmvc.domain.ResembleTask;
import springmvc.service.ResembleTaskService;

@Controller
public class ResembleTaskController {
    
    @Autowired
    private ResembleTaskService resembleTaskService; 
    
    @RequestMapping(value = "resembleGame")
    public ModelAndView resembleGame(ModelAndView mav){
        mav.addObject("resembleTask", resembleTaskService.getResembleTask(3)); 
        mav.setViewName("resembleGame");
        return mav; 
    }
}

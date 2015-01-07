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
import springmvc.domain.ResembleTask;
import springmvc.service.ResembleTaskService;

@Controller
public class ResembleTaskController {
    
    @Autowired
    private ResembleTaskService resembleTaskService; 
    
    @RequestMapping(value = "resembleGame")
    public String resembleGame(@ModelAttribute(value = "resembleTask") ResembleTask resembleTask){
        resembleTask.setStartingHTML("TESAASDASD");
        return "resembleGame"; 
    }
}

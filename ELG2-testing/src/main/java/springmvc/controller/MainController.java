/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Jorgen
 */
@Controller
public class MainController {
    @RequestMapping(value = "*")
    public String showIndex(Model model){
        //model.addAttribute("melding", "melding");
        return "index"; 
    }
    
    
}

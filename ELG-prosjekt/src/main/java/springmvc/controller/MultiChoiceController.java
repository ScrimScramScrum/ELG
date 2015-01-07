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
import springmvc.domain.MultiChoice;
import springmvc.service.MultiChoiceService;
import springmvc.test.MultiChoiceTest;

/**
 *
 * @author eiriksandberg
 */
@Controller
public class MultiChoiceController {
    @Autowired
    private MultiChoiceService hei;
    @RequestMapping(value = "multi")
    public String showMultiChoice(Model model, @ModelAttribute("spill") MultiChoice mc, @ModelAttribute("spillnavn") String name){
        model.addAttribute("spillet", hei.getMultiChoice("Spill 1"));
        model.addAttribute("navn", hei.getMultiChoice("Spill 1").getName());
        return "multichoice"; 
    }
}

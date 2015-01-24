/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package springmvc.controller;

import javax.servlet.http.HttpSession;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import springmvc.domain.User;

/**
 *
 * @author borgarlie
 */
@Controller
public class PdfController {
    @ExceptionHandler(Throwable.class)
    public String handleTException(Throwable t, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!(user.isInLogged())){
            return "login";
        }
        return "error";
    } 

    @ExceptionHandler(Exception.class)
    public String handleException(Throwable t, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (!(user.isInLogged())){
            return "login";
        }
        return "error";
    }
    
    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        return new FileSystemResource("/home/scripts/pdf/" + fileName + ".pdf"); 
    }
}
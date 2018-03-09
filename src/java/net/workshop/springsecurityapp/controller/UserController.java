package net.workshop.springsecurityapp.controller;

import net.workshop.springsecurityapp.entity.User;
import net.workshop.springsecurityapp.service.SecurityService;
import net.workshop.springsecurityapp.service.UserService;
import net.workshop.springsecurityapp.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *Controller for class {@link net.workshop.springsecurityapp.entity.User}'s pages.
 *
 * @author Andrii Androsiuk
 * @version 1.0
 *
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(@ModelAttribute("userForm")User userForm, BindingResult bindingResult, Model model){
        userValidator.validate(userForm, bindingResult);

        // passwords don't match , or user already exist in DB
        if (bindingResult.hasErrors()){
            return "registration";
        }

        userService.save(userForm);

        // if it's ok- save progress and invoke auto login process with parameter username and password
        securityService.autoLogin(userForm.getUseremail(), userForm.getConfirmPassword());

        return "redirect:/welcome";
    }

    //method for login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login (Model model, String error, String logout){
        if (error!=null){
            model.addAttribute("error", "Username or password  is incorrect");
        }

        if (logout!= null){
            model.addAttribute("message", "Logged out is successfully");
        }

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model){
        return "/admin";
    }
}

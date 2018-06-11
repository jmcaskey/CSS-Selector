package com.jmcaskey.auth.web;

import com.jmcaskey.auth.model.Theme;
import com.jmcaskey.auth.model.User;
import com.jmcaskey.auth.service.SecurityService;
import com.jmcaskey.auth.service.UserService;
import com.jmcaskey.auth.validator.UserValidator;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        userService.save(userForm);

        Theme theme = new Theme();
        theme.setUsername(userForm.getUsername());
        theme.setTheme("none");
        userService.save(theme);
        
        
        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }
    
    @RequestMapping(value = "/welcome/theme/get", method = RequestMethod.GET)
    @ResponseBody
    public String getTheme(Model model, Principal principal) {
    	
    	String username = principal.getName();
    		Theme theme = userService.findThemeByUsername(username);
    		
    		return theme != null ? theme.getTheme() : "dark";
    }
    @RequestMapping(value = "/welcome/theme/set", method = RequestMethod.POST)
    @ResponseBody
    public String setTheme(Model model, Principal principal, String value) {
    	String username = principal.getName();
        
        userService.update(value, username);
        
        return username;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
}

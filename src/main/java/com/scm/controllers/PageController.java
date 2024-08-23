package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;

import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", true);
        return "about";
    }

    @GetMapping("/services")
    public String servicesPage() {
        return "services";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password.");
        }
        if (logout != null) {
            model.addAttribute("message", "You have been logged out.");
        }
        return "login";
    }

   

    @GetMapping("/register")
    public String register(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "register";
    }

    @PostMapping("/do-register")
    public String processRegister(@Valid @ModelAttribute("userForm") UserForm userForm,
                                  BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = new User();
        user.setUname(userForm.getUname());
        user.setUemail(userForm.getUemail());
        user.setUpassword(userForm.getUpassword()); // Ensure password is encoded
        user.setUabout(userForm.getUabout());
        user.setUponeNo(userForm.getUponeNo());
        user.setEnabled(false);
        user.setUprofilePic("https://i.postimg.cc/FFV1syxD/rus.jpg");

        User savedUser = userService.saveUser(user);

        Message message = Message.builder().content("Registration Successful").build();
        session.setAttribute("message", message);

        return "redirect:/register";
    }
}

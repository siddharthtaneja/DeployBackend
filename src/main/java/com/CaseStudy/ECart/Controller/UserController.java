package com.CaseStudy.ECart.Controller;

import com.CaseStudy.ECart.Models.Users;
import com.CaseStudy.ECart.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @GetMapping("/getuser")
    public List<Users> getuser(){
        return userRepository.findAll();
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req, HttpServletResponse ros) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println("LogOut Servlet :"+authentication);

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(req,ros,authentication);
            req.getSession().invalidate();
        }
        return "\"logout successful\"";
    }
    @GetMapping("/getloggedin/{user}")
    public Users getLoggedin(@PathVariable("user") String user) {
        Users users = userRepository.getByEmail(user);
        return users;
    }
    @GetMapping("/loggedin")
    public Users loggedin(Principal principal){
        return userRepository.getByEmail(principal.getName());
    }

}

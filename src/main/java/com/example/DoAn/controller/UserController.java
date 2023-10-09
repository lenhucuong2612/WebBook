package com.example.DoAn.controller;

import com.example.DoAn.dto.UserDto;
import com.example.DoAn.entity.User;
import com.example.DoAn.server.UserService;
import com.example.DoAn.server.CategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryServer categoryServer;


    @GetMapping("/registration")
    public String register(Model model,@ModelAttribute("user") UserDto userDto){

        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("user", new UserDto());
        model.addAttribute("displayText","Đăng ký");
        return "register";
    }

    @PostMapping("/registration")
    public String registration( @ModelAttribute("user")UserDto userDto,Model model){
        User user=userService.findNameUser(userDto.getEmail());
        if(user==null){
            userService.save(userDto);
            model.addAttribute("message1","Registration successfully!");
            model.addAttribute("displayText","Đăng ký");
        }else{
            model.addAttribute("message2","Email đã tồn tại!");
            model.addAttribute("displayText","Đăng ký");
        }
         return "register";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("displayText","Đăng nhập");
        return "login";
    }

}

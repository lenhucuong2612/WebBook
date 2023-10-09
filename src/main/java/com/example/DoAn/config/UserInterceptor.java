package com.example.DoAn.config;

import com.example.DoAn.entity.ShoppingCart;
import com.example.DoAn.entity.User;
import com.example.DoAn.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import java.security.Principal;


public class UserInterceptor implements WebRequestInterceptor {

    private UserService userService;

    public UserInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void preHandle(WebRequest request) throws Exception {

    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String email = auth.getName();
            User user = userService.findNameUser(email);

            if (model == null) {
                model = new ModelMap();
            }
           if(user!=null){
               ShoppingCart shoppingCart = user.getCart();
               if (shoppingCart == null || shoppingCart.getTotalItems() == 0) {
                   model.addAttribute("check", "No item in your cart");
               } else {
                   model.addAttribute("totalItems", shoppingCart.getTotalItems());
                   model.addAttribute("shoppingCart", shoppingCart);
                   model.addAttribute("subTotal", shoppingCart.getTotalPrice());
               }
           }

            model.addAttribute("user", user);
        }



    }
    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {

    }
}

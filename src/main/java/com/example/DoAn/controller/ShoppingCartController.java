package com.example.DoAn.controller;

import com.example.DoAn.dto.ProductDTO;
import com.example.DoAn.entity.Product;
import com.example.DoAn.entity.ShoppingCart;
import com.example.DoAn.entity.User;
import com.example.DoAn.server.ProductServer;
import com.example.DoAn.server.ShoppingCartService;
import com.example.DoAn.server.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShoppingCartController {
    @Autowired
    private UserService userService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ProductServer productService;
    @GetMapping("/cart")
    public String cart(Model model, Principal principal, HttpSession httpSession){
        if(principal==null){
            return "redirect:/login";
        }
        String username= principal.getName();
        User user=userService.findNameUser(username);
        ShoppingCart shoppingCart=user.getCart();
        if(shoppingCart==null){
            model.addAttribute("check","No item in your cart");
        }

        model.addAttribute("subTotal", shoppingCart.getTotalPrice());
        model.addAttribute("shoppingCart", shoppingCart);

        return "giohang";
    }
    @PostMapping("/add-to-cart")
    public String addItemToCart(
            @RequestParam("id") Long productId,
            @RequestParam(value="quantity", required = false,defaultValue = "1") int quantity,
            Principal principal,
            HttpServletRequest request
    ){
        ProductDTO product = productService.findByProductById(productId);
        Long id=product.getId();
        if(principal!=null){
            String username = principal.getName();
            User customer = userService.findNameUser(username);

            ShoppingCart cart = shoppingCartService.addItemToCart(product, quantity, username);
            return "redirect:/shop/cart";
        }
        return "redirect:/login";
    }
    @PostMapping(value = "/updateCart",params = "action=update")
    public String updateShoppingCart(@RequestParam("quantity") int quantity,
                                     @RequestParam("id")Long productId,
                                     Model model,
                                     Principal principal){
        if(principal==null){
            return "redirect:/login";

        }else{
            String username=principal.getName();
            User user=userService.findNameUser(username);
            ProductDTO product = productService.findByProductById(productId);
            ShoppingCart cart=shoppingCartService.updateItemCart(product,quantity,username);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shop/cart";
        }

    }
    @PostMapping(value="updateCart",params = "action=delete")
    public String deleteItemFromCart(@RequestParam("id") Long productId,
                                     Model model,
                                     Principal principal){
        if(principal==null){
            return "redirect:/login";
        }else{
            String username=principal.getName();
            User user=userService.findNameUser(username);
            Product product=productService.findProductId(productId);
            ShoppingCart cart=shoppingCartService.deleteItemFromCart(product,user);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shop/cart";
        }
    }
    @GetMapping("/updateCart/delete/{id}")
    public String deleteCart(@PathVariable("id") Long productId,Model model,Principal principal){
        String username=principal.getName();
        User user=userService.findNameUser(username);
        Product product=productService.findProductId(productId);
        ShoppingCart cart=shoppingCartService.deleteItemFromCart(product,user);
        model.addAttribute("shoppingCart", cart);
        return "redirect:/shop/cart";
    }
}

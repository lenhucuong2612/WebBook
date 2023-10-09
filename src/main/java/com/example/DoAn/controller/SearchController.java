package com.example.DoAn.controller;

import com.example.DoAn.dto.SearchDto;
import com.example.DoAn.entity.Product;
import com.example.DoAn.entity.User;
import com.example.DoAn.repository.ProductRepository;
import com.example.DoAn.repository.UserRepository;
import com.example.DoAn.server.ProductServer;
import com.example.DoAn.server.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private ProductServer productServer;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shop/keyword/{query}")
    public ResponseEntity<SearchDto> search(@PathVariable("query") String query){
            System.out.println(query);
            List<Product> products=this.productRepository.findByProduct(query);
            int quantity=products.size();
            String name=query;

            if(products.size()>3){
                products=products.subList(0,3);
                SearchDto searchDto1=new SearchDto(products,name,quantity);
                return ResponseEntity.ok(searchDto1);
            }
        SearchDto searchDto2=new SearchDto(products,name,quantity);
        return ResponseEntity.ok(searchDto2);

    }
}

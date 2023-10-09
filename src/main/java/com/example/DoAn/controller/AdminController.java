package com.example.DoAn.controller;

import com.example.DoAn.dto.ProductDTO;
import com.example.DoAn.entity.Category;
import com.example.DoAn.entity.Product;
import com.example.DoAn.server.CategoryServer;
import com.example.DoAn.server.ProductServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
    @Autowired
    CategoryServer categoryServer;

    @Autowired
    ProductServer productService;

    @GetMapping("/admin/home")
    public String adminHome(){
        return "adminHome";
    }
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryServer.getAllCate());
        return "categories";
    }
    @GetMapping("/admin/categories/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "addCategories";
    }
    @PostMapping("/admin/categories/add")
    public String addCategory(@ModelAttribute("category") Category category){
        categoryServer.addCategory(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable("id") Long id){
        categoryServer.deleteCategory(id);
        return "redirect:/admin/categories";
    }
    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, Model model){
        Optional<Category> category=categoryServer.findById(id);
        if(category.isPresent()){
            model.addAttribute("category",category);
            return "addCategories";
        }else{
            return "404";
        }
    }

    //Product section
    @GetMapping("/admin/products")
    public String getPro(Model model){

        /*
        model.addAttribute("products",productService.getAllPro());
        return "products";

         */
        String keyword=null;
        return findPaginated(1,model,keyword);
    }
    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){

        model.addAttribute("productDTO",new ProductDTO());
        model.addAttribute("categories",categoryServer.getAllCate());
        return "productsAdd";
    }
    @PostMapping("/admin/products/add")
    public String productAddPost(Model model,
                                 @ModelAttribute("productDTO") ProductDTO productDTO,
                                 @RequestParam("productImage") MultipartFile file,
                                 @RequestParam("imgName") String imgName) throws IOException{

        Product product=new Product();
        product.setId(productDTO.getId());
        product.setISBN(productDTO.getISBN());
        product.setSKU(productDTO.getSKU());
        product.setName(productDTO.getName());
        product.setObject(productDTO.getObject());
        product.setAuthor(productDTO.getAuthor());
        product.setFormat(productDTO.getFormat());
        product.setFramework(productDTO.getFramework());
        product.setPageNumber(productDTO.getPageNumber());
        product.setPrice(productDTO.getPrice());
        product.setSalePrice(productDTO.getSalePrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());
        product.setGift(productDTO.getGift());
        product.setBoolSeries(productDTO.getBoolSeries());
        product.setCategory(categoryServer.findById(productDTO.getCategoryId()).get());
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID= file.getOriginalFilename();
            Path fileNameAndPath= Paths.get(uploadDir,imageUUID);
            Files.write(fileNameAndPath,file.getBytes());
        }else{
            imageUUID=imgName;
        }
        product.setImageName(imageUUID);
        product.setLocalDateTime(LocalDateTime.now());
        productService.save(product);

        return "redirect:/admin/products";
    }
    @GetMapping("/admin/product/update/{id}")
    public String updateProductGet(@PathVariable("id") Long id,Model model){
        Product productDTO=productService.getProductById(id).get();
        ProductDTO product=new ProductDTO();
        product.setId(productDTO.getId());
        product.setISBN(productDTO.getISBN());
        product.setSKU(productDTO.getSKU());
        product.setName(productDTO.getName());
        product.setObject(productDTO.getObject());
        product.setAuthor(productDTO.getAuthor());
        product.setFormat(productDTO.getFormat());
        product.setFramework(productDTO.getFramework());
        product.setPageNumber(productDTO.getPageNumber());
        product.setPrice(productDTO.getPrice());
        product.setSalePrice(productDTO.getSalePrice());
        product.setWeight(productDTO.getWeight());
        product.setBoolSeries(productDTO.getBoolSeries());
        product.setDescription(productDTO.getDescription());
        product.setGift(productDTO.getGift());
        product.setCategoryId(productDTO.getCategory().getId());
        product.setImageName(productDTO.getImageName());
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("productDTO",product);
        return "productsAdd";
    }
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model,
                                @Param("keyword") String keyword){
        int pageSize=3;
        Page<Product> page=productService.findPaginate(pageNo,pageSize,keyword);
        List<Product> products=page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("products",products);
        model.addAttribute("keyword",keyword);
        return "products";
    }
}

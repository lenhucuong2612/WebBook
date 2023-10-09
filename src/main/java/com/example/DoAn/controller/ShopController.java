package com.example.DoAn.controller;

import com.example.DoAn.entity.Product;
import com.example.DoAn.entity.User;
import com.example.DoAn.server.UserService;
import com.example.DoAn.server.CategoryServer;
import com.example.DoAn.server.CookieServer;
import com.example.DoAn.server.ProductServer;
import jakarta.servlet.http.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private CategoryServer categoryServer;

    @Autowired
    private ProductServer productServer;
    @Autowired
    private UserService userService;
    @Autowired
    private CookieServer cookieServer;
    @GetMapping("test")
    public String test(){
        return "index";
    }
    @GetMapping("/home")
    public String shopHome(Model model, Principal principal){
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("productNew",productServer.find7ProductNew());
        model.addAttribute("productSold",productServer.find7ProductSold());
        model.addAttribute("combo",productServer.listCombo());
        model.addAttribute("manga",productServer.listManga());
        model.addAttribute("wingsbooks",productServer.listWingsbooks());
        model.addAttribute("doremon",productServer.listDoremon());
        /*
        if(principal!=null){
            String user=principal.getName();
            String name=userService.findNameUser(user).getFirstName()+" "+userService.findNameUser(user).getLastName();
            model.addAttribute("username",name);
        }

         */
        return "home";
    }
    @RequestMapping("/category/all")
    public String categoryALl(Model model){
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("categoryProduct",productServer.getAllPro());
        return "danhmucsanpham";
    }
    @GetMapping("/category/{id}/search")
    public String findByCategoryThan100000(
            @PathVariable("id") Long id,
            @RequestParam(value = "filter-price", required = false) String filterPrices,
            Model model
    ) {
        model.addAttribute("categories", categoryServer.getAllCate());
        // Sử dụng danh sách filterPrices trong xử lý của bạn
        model.addAttribute("categoryProduct", productServer.salePriceThan(id, filterPrices));
        return "danhmucsanpham";
    }
    @GetMapping("/category/{id}/{pageNo}/sort")
    public String sort(
            @PathVariable("id") Long id,
            @PathVariable("pageNo") int pageNo,
            @RequestParam(value = "sort", required = false) String sortType,
            Model model
    ) {
        String display = null;
        if(id!=null){
            if(id==1){
                display="Manga";
            }else if(id==3){
                display="Văn học Việt Nam";
            }else if(id==2){
                display="Văn học nước ngoài";
            }else if(id==4){
                display="Truyện tranh";
            }else if(id==5){
                display="Combo";
            }else if(id==6){
                display="WingsBooks";
            }
        }
        model.addAttribute("displayText", display);
        model.addAttribute("categories", categoryServer.getAllCate());
        Page<Product> page = productServer.sortAndPaginate(id, pageNo, 12,sortType);
        List<Product> sortedProducts = page.getContent();
        model.addAttribute("categoryProduct", sortedProducts);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("size",page.getTotalElements());
        return "danhmucsanpham";
    }
    @GetMapping("/category/all/{pageNo}/sort")
    public String sortALl(
            @PathVariable("pageNo") int pageNo,
            @RequestParam(value = "sort", required = false) String sortType,
            Model model
    ) {

        model.addAttribute("displayText", "Tất cả sản phẩm");
        model.addAttribute("categories", categoryServer.getAllCate());
        Page<Product> page = productServer.sortAndPaginate( pageNo, 12,sortType);
        List<Product> sortedProducts = page.getContent();
        model.addAttribute("categoryProduct", sortedProducts);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("size", page.getTotalElements());
        return "danhmucsanpham";
    }
    @GetMapping("/category/{id}/{pageNo}/search")
    public String pageFilter(
            @PathVariable("id") Long id,
            @PathVariable("pageNo") int pageNo,
            @RequestParam(value = "search", required = false) String sortType,
            Model model
    ) {
        String display = null;
        if(id!=null){
            if(id==1){
                display="Manga";
            }else if(id==3){
                display="Văn học Việt Nam";
            }else if(id==2){
                display="Văn học nước ngoài";
            }else if(id==4){
                display="Truyện tranh";
            }else if(id==5){
                display="Combo";
            }else if(id==6){
                display="WingsBooks";
            }
        }
        model.addAttribute("displayText", display);
        model.addAttribute("categories", categoryServer.getAllCate());
        Page<Product> page = productServer.pageFilter(id, pageNo, 12,sortType);
        List<Product> sortedProducts = page.getContent();
        model.addAttribute("categoryProduct", sortedProducts);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("size",page.getTotalElements());
        return "danhmucsanpham";
    }


    @GetMapping("/home/product/{id}")
    public String findByProduct( @PathVariable("id") Long id, Model model,Principal principal){
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("product",productServer.getProductById(id).get());
        model.addAttribute("saleProduct",productServer.sale(id));
        model.addAttribute("nameCategory",productServer.getCategoryByProductId(id));
        model.addAttribute("author",productServer.getALlByAuthor(id));
        model.addAttribute("ProductByCategory", productServer.getProductByCategory(id));
        model.addAttribute("boolSeries",productServer.getAllBoolSeries(id));

        // hàng đã xem
        Cookie viewed=cookieServer.read("viewed");
        String value=id.toString();
        if(viewed!=null){
            value=viewed.getValue();
            value+=","+id.toString();
        }
        cookieServer.create("viewed", value,1);
        List<Product> view_list=productServer.findByIds(value);
        model.addAttribute("view_list", view_list);
        if(principal!=null){
            String name=principal.getName();
            User user= userService.findNameUser(name);
            model.addAttribute("user", user);
        }
        return "shopProductFind";
    }
    @GetMapping("/category/{id}/{pageNo}")
    public String Category(@PathVariable("id")Long id,@PathVariable("pageNo") int pageNo, Model model){
        model.addAttribute("categories",categoryServer.getAllCate());

        model.addAttribute("categoryProduct",productServer.getAllProductByCategory(id));
        String display = null;
        if(id!=null){
            if(id==1){
                display="Manga";
            }else if(id==3){
                display="Văn học Việt Nam";
            }else if(id==2){
                display="Văn học nước ngoài";
            }else if(id==4){
                display="Truyện tranh";
            }else if(id==5){
                display="Combo";
            }else if(id==6){
                display="WingsBooks";
            }
        }
        model.addAttribute("displayText", display);

        List<Product> listproduct=productServer.getAllProductByCategory(id);
            int pageSize = 12;
            Page<Product> page = productServer.findPaginateProductOfCategory(id, pageNo, pageSize);
            List<Product> products = page.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("categoryProduct", products);
            model.addAttribute("id",id);
            model.addAttribute("size",page.getTotalElements());
        return "danhmucsanpham";
    }
    @GetMapping("/category/all/{pageNo}")
    public String PageAllCategory(@PathVariable("pageNo") int pageNo, Model model){
        model.addAttribute("categories",categoryServer.getAllCate());
        model.addAttribute("displayText", "Tất cả sản phẩm");
            int pageSize = 12;
            Page<Product> page = productServer.findPaginate(pageNo, pageSize);
            List<Product> products = page.getContent();
            model.addAttribute("currentPage", pageNo);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("categoryProduct", products);
            model.addAttribute("size",page.getTotalElements());
        return "danhmucsanpham";
    }
    @GetMapping("/category/all")
    public String phantrang(Model model){
        return PageAllCategory(1,model);
    }
    @GetMapping("/category/{id}")
    public String danhmuc(@PathVariable("id")Long id,@PathVariable("pageNo") int pageNo,Model model){
        return Category(id,1,model);
    }
    //tim kiếm product
    @GetMapping("/product/search/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo, Model model,@Param("keyword") String keyword){
        int pageSize=12;
        Page<Product> page=productServer.findPaginate(pageNo,pageSize,keyword);
        List<Product> products=page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("products",products);
        model.addAttribute("keyword",keyword);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("size",page.getTotalElements());
        model.addAttribute("categories",categoryServer.getAllCate());
        return "timkiemsanpham";
    }
    @GetMapping("/product/search")
    public String search(Model model){
        String keyword=null;
        return findPaginated(1,model,keyword);
    }


}

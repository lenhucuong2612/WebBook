package com.example.DoAn.server;

import com.example.DoAn.dto.ProductDTO;
import com.example.DoAn.entity.Category;
import com.example.DoAn.entity.Product;
import com.example.DoAn.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductServer {
    @Autowired
    private ProductRepository productRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public ProductServer(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    public List<Product> getAllPro(){
        return productRepository.findAll();
    }
    public List<Product> find7ProductNew() {
        List<Product> productList = productRepository.findAll();
        if (productList != null && productList.size() > 7) {
            Collections.sort(productList, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    return o2.getLocalDateTime().compareTo(o1.getLocalDateTime());
                }
            });
            productList = productList.subList(0, 7); // Lấy 7 phần tử đầu tiên
        }
        return productList;
    }
    public List<Product> find7ProductSold(){
        List<Product> productList=productRepository.findAll();
        if(productList!=null){
            if(productList.size()>7){

                Collections.sort(productList, new Comparator<Product>() {
                    @Override
                    public int compare(Product o1, Product o2) {
                        return Double.compare(o2.getSold(),o1.getSold());
                    }
                });
                productList = productList.subList(0, 7);
            }
        }
        return productList;
    }
    public Product save(Product product){

        return productRepository.save(product);
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }
    public List<Product> getAllProductByCategory(Long id){
        return productRepository.findAllByCategory_Id(id);
    }

    public Page<Product> findPaginate(int pageNo, int pageSize,String keyword){


        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        if(keyword!=null){
            return productRepository.findAll(keyword,pageable);
        }
        return this.productRepository.findAll(pageable);

    }

    public int sale(Long id){
        Product product=productRepository.findById(id).get();
        int sale= (int) (product.getPrice()-product.getSalePrice());
        return sale;
    }

    public Category getCategoryByProductId(Long id){
        Product product=productRepository.findById(id).get();
        Category category=product.getCategory();
        if(category!=null){
            return category;
        }
        return null;
    }

    public List<Product> getALlByAuthor(Long id){
        Product product=productRepository.findById(id).get();
        if(product!=null){
            String name=product.getAuthor();
            List<Product> author_product= productRepository.findAllByAuthor(name);
            if(author_product.size()>5){
                return author_product.subList(0,5);
            }
            else{
                return author_product;
            }
        }
        return null;
    }

    public List<Product> getProductByCategory(Long id){
        Product product=productRepository.findById(id).get();
        if(product!=null){
            Long category=product.getCategory().getId();
            List<Product> productListCategory= productRepository.findAllByCategory_Id(category);
            if(productListCategory.size()>10){
                return productListCategory.subList(0,10);
            }
            else{
                return productListCategory;
            }
        }
        return null;
    }
    public List<Product> getAllBoolSeries(Long id){
        Product prodct=productRepository.findById(id).get();
        if(prodct!=null){
            String boolSeries= prodct.getBoolSeries();
            List<Product> product_boolSeries=productRepository.findAllByBoolSeries(boolSeries);
            if(product_boolSeries.size()>5){
                return product_boolSeries.subList(0,5);
            }else{
                return product_boolSeries;
            }
        }
        return null;
    }
    public List<Product> findByIds(String ids){
        String hql="from Product p where p.id in("+ids+")";
        TypedQuery<Product> query= entityManager.createQuery(hql,Product.class);
        List<Product> productList=query.getResultList();
        return productList;
    }
    public List<Product> listCombo(){
        long id=5;
        List<Product> combo=productRepository.findAllByCategory_Id(id);
        if(combo!=null){
            if(combo.size()>10){
                return combo.subList(0,10);
            }else{
                return combo;
            }
        }
       return null;
    }
    public List<Product> listManga(){
        long id=1;
        List<Product> combo=productRepository.findAllByCategory_Id(id);
        if(combo!=null){
            if(combo.size()>10){
                return combo.subList(0,10);
            }else{
                return combo;
            }
        }
        return null;
    }
    public List<Product> listWingsbooks(){
        long id=6;
        List<Product> combo=productRepository.findAllByCategory_Id(id);
        if(combo!=null){
            if(combo.size()>10){
                return combo.subList(0,10);
            }else{
                return combo;
            }
        }
        return null;
    }
    public List<Product> listDoremon(){
        long id=6;
        List<Product> combo=productRepository.findProductsByNameContainingDoremon();
        if(combo!=null){
            if(combo.size()>10){
                return combo.subList(0,10);
            }else{
                return combo;
            }
        }
        return null;
    }

    public List<Product> salePriceThan1000001(Long id, String filterPrice) {
        List<Product> product = productRepository.findAllByCategory_Id(id);
        List<Product> productThan1 = new ArrayList<>();

        if (filterPrice.equals("lessthan20000")) {
            for (Product p : product) {
                if (p.getSalePrice() < 20000) {
                    productThan1.add(p);
                }
            }
        } else {
            // Không có điều kiện lọc phù hợp, trả về danh sách sản phẩm ban đầu
            productThan1 = product;
        }

        return productThan1;
    }


    public List<Product> sort(Long id, String sort_string){
        List<Product> product=productRepository.findAllByCategory_Id(id);
        if(product!=null){
                switch (sort_string){
                    case "name_az":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o1.getName().compareTo(o2.getName());
                            }

                        });
                        break;
                    case "name_za":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getName().compareTo(o1.getName());
                            }

                        });

                    case "price_asc":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o1.getSalePrice(),o2.getSalePrice());
                            }
                        });
                        break;
                    case "price_desc":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o2.getSalePrice(),o1.getSalePrice());
                            }
                        });
                        break;
                    case "oldest":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
                            }
                        });
                        break;
                    case "latest":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return o2.getLocalDateTime().compareTo(o1.getLocalDateTime());
                            }
                        });
                        break;
                    case "best_selling":
                        Collections.sort(product, new Comparator<Product>() {
                            @Override
                            public int compare(Product o1, Product o2) {
                                return Double.compare(o2.getSold(),o1.getSold());
                            }
                        });
                        break;
            }
        }
        return product;
    }
    public List<Product> findByProduct(String name){
        return productRepository.findByProduct(name);
    }
    public ProductDTO findByProductById(Long id){
        ProductDTO productDTO=new ProductDTO();
        Product product=productRepository.findById(id).get();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setSalePrice(product.getSalePrice());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageName(productDTO.getImageName());
        return productDTO;
    }
    public Product findProductId(Long id){
        return productRepository.findById(id).get();
    }
    public Page<Product> findPaginateProductOfCategory(Long id, int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return productRepository.findAllByCategory_Id(id, pageable);
    }
    public Page<Product> findPaginate(int pageNo, int pageSize){
        Pageable pageable= PageRequest.of(pageNo-1,pageSize);
        return this.productRepository.findAll(pageable);

    }
    public Page<Product> sortAndPaginate(Long categoryId, int pageNo, int pageSize, String param) {

        Sort sort;
        if (param.equals("name_az")) {
            sort = Sort.by("name").ascending();
        } else if (param.equals("name_za")) {
            sort = Sort.by("name").descending();
        } else if (param.equals("price_asc")) {
            sort = Sort.by("salePrice").ascending();
        } else if (param.equals("price_desc")) {
            sort = Sort.by("salePrice").descending();
        } else if (param.equals("oldest")) {
            sort = Sort.by("localDateTime").ascending();
        } else if (param.equals("latest")) {
            sort = Sort.by("localDateTime").descending();
        } else {
            sort = Sort.unsorted(); // Không sắp xếp nếu param không phù hợp
        }

        return productRepository.findAllByCategory_Id(categoryId, PageRequest.of(pageNo - 1, pageSize, sort));
    }
    public Page<Product> sortAndPaginate( int pageNo, int pageSize, String param) {

        Sort sort;
        if (param.equals("name_az")) {
            sort = Sort.by("name").ascending();
        } else if (param.equals("name_za")) {
            sort = Sort.by("name").descending();
        } else if (param.equals("price_asc")) {
            sort = Sort.by("salePrice").ascending();
        } else if (param.equals("price_desc")) {
            sort = Sort.by("salePrice").descending();
        } else if (param.equals("oldest")) {
            sort = Sort.by("localDateTime").ascending();
        } else if (param.equals("latest")) {
            sort = Sort.by("localDateTime").descending();
        } else {
            sort = Sort.unsorted(); // Không sắp xếp nếu param không phù hợp
        }

        return productRepository.findAll( PageRequest.of(pageNo - 1, pageSize, sort));
    }
    public List<Product> salePriceThan(Long id, String filterPrice) {
        List<Product> products = productRepository.findAllByCategory_Id(id);
        List<Product> filteredProducts = new ArrayList<>();
        if (products != null) {
            for (Product p : products) {
                switch (filterPrice) {
                    case "lessthan20000":
                        if (p.getSalePrice() < 20000) {
                            filteredProducts.add(p);
                        }
                        break;
                    case "all":
                        return products;
                    case "20000-to-40000":
                        if (p.getSalePrice() >= 20000 && p.getSalePrice() < 40000) {
                            filteredProducts.add(p);
                        }
                        break;
                    case "40000-to-60000":
                        if (p.getSalePrice() >= 40000 && p.getSalePrice() < 60000) {
                            filteredProducts.add(p);
                        }
                        break;
                    case "60000-to-100000":
                        if (p.getSalePrice() >= 60000 && p.getSalePrice() < 100000) {
                            filteredProducts.add(p);
                        }
                        break;
                    case "100000-to-200000":
                        if (p.getSalePrice() >= 100000 && p.getSalePrice() < 200000) {
                            filteredProducts.add(p);
                        }
                        break;
                    case "big-than-200000":
                        if (p.getSalePrice() >= 200000) {
                            filteredProducts.add(p);
                        }
                        break;
                }
            }
        }

        return filteredProducts;
    }
    public Page<Product> pageFilter(Long categoryId, int pageNo, int pageSize, String param) {
        float a;float b;
        Pageable pageable;
        switch (param) {
            case "lessthan20000":
                 a = 0;
                 b = 20000;
                 pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);
            case "20000-to-40000":
                 a=20000;
                 b=40000;
                 pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);
            case "40000-to-60000":
                a=40000;
                b=60000;
                pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);
            case "60000-to-100000":
                a=60000;
                b=100000;
                pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);
            case "100000-to-200000":
                a=100000;
                b=200000;
                pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);
            case "big-than-200000":
                a=200000;
                b=10000000;
                pageable= PageRequest.of(pageNo-1,pageSize);
                return productRepository.pageProductFilter(categoryId, a, b, pageable);

        }
        return null;
    }
}

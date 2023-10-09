package com.example.DoAn.repository;

import com.example.DoAn.entity.Category;
import com.example.DoAn.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Query("select p FROM Product p where"
            +" CONCAT(p.name,p.author)"
            +" like %?1%")
    List<Product> findByProduct(String name);

    List<Product> findByNameContaining(String name);
    List<Product> findAllByCategory_Id(Long id);
    Page<Product> findAllByCategory_Id(Long id, Pageable pageable);
    List<Product> findAllByAuthor(String author);
    List<Product> findAllByBoolSeries(String boolSeries);
    @Query("select p FROM Product p where"
            +" CONCAT(p.name,p.author)"
            +" like %?1%")

    public Page<Product> findAll(String keyword,Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.name LIKE '%DORAEMON%'")
    List<Product> findProductsByNameContainingDoremon();
    @Query("select p from Product p where p.category.id = :id and p.salePrice >= :a and p.salePrice <= :b")
    Page<Product> pageProductFilter(@Param("id") Long id, float a,  float b, Pageable pageable);
}

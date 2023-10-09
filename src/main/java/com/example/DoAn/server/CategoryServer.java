package com.example.DoAn.server;

import com.example.DoAn.entity.Category;
import com.example.DoAn.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServer {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category addCategory(Category category){
         return categoryRepository.save(category);
    }
    public List<Category> getAllCate(){
        return  categoryRepository.findAll();
    }
    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

}

package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.Category;
import com.study.adminstore.model.network.request.CategoryApiRequest;
import com.study.adminstore.model.network.response.CategoryApiResponse;
import com.study.adminstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CategoryApiService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryApiResponse> create(final CategoryApiRequest req) {
        // request data
        // category create
        // CategoryApiResponse return

        final CategoryApiRequest categoryApiRequest = req;

        final String parentType = categoryApiRequest.getParentType();
        final String type = categoryApiRequest.getType();
        final String title = categoryApiRequest.getTitle();

        System.out.println(categoryApiRequest);
        
        if(parentType.equals("") || type.equals("") || title.equals("")) System.out.println("failed");

        final Category category = Category.builder()
                .parentType(categoryApiRequest.getParentType())
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .createdAt(LocalDateTime.now())
                .createdBy("KSJ")
                .updatedAt(LocalDateTime.now())
                .updatedBy("KSJ")
                .build();

        // 이미 존재하는 카테고리일 경우 에러처리

        categoryRepository.save(category);

        return new ResponseEntity<CategoryApiResponse>(response(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryApiResponse> read(final Long id) {
        final Optional<Category> category = categoryRepository.findById(id);
        return new ResponseEntity<CategoryApiResponse>(CategoryApiResponse.builder().build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryApiResponse> update(final CategoryApiRequest req) {

        final CategoryApiRequest categoryApiRequest = req;
        final Optional<Category> optional = categoryRepository.findById(categoryApiRequest.getId());

        return optional.map(category -> {
            category.setType(categoryApiRequest.getType())
                    .setTitle(categoryApiRequest.getTitle())
                    .setCreatedAt(LocalDateTime.now())
                    .setCreatedBy("KSJ");
            return category;
        }).map(newCategory-> categoryRepository.save(newCategory))
                .map(newCategory-> new ResponseEntity<CategoryApiResponse>(CategoryApiResponse.builder().build(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<CategoryApiResponse> delete(final Long id) {
        final Optional<Category> optional = categoryRepository.findById(id);

        return optional.map(category-> {
            categoryRepository.delete(category);
            return new ResponseEntity(HttpStatus.OK);
        })
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    public Long count() {
        return categoryRepository.count();
    }

    public List<Category> findAll() {
        final List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    private CategoryApiResponse response(final Category category) {
        final CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .parentType(category.getParentType())
                .type(category.getType())
                .title(category.getTitle())
                .build();

        return categoryApiResponse;
    }
}

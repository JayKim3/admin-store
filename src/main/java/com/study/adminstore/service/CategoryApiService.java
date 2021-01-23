package com.study.adminstore.service;

import com.study.adminstore.ifs.CrudInterface;
import com.study.adminstore.model.entity.Category;
import com.study.adminstore.model.network.Header;
import com.study.adminstore.model.network.request.CategoryApiRequest;
import com.study.adminstore.model.network.response.CategoryApiResponse;
import com.study.adminstore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class CategoryApiService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> req) {
        // request data
        // category create
        // CategoryApiResponse return

        CategoryApiRequest categoryApiRequest = req.getData();

        String type = categoryApiRequest.getType();
        String title = categoryApiRequest.getTitle();

        if(type.equals("") || title.equals("")) return Header.ERROR("failed");

        Category category = Category.builder()
                .type(categoryApiRequest.getType())
                .title(categoryApiRequest.getTitle())
                .createdAt(LocalDateTime.now())
                .createdBy("KSJ")
                .updatedAt(LocalDateTime.now())
                .updatedBy("KSJ")
                .build();

        // 이미 존재하는 카테고리일 경우 에러처리

        categoryRepository.save(category);

        return response(category);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        System.out.println(id);
        Optional<Category> optional = categoryRepository.findById(id);

        return optional.map(category-> response(category))
                .orElseGet(()->Header.ERROR("Category Id Not Found"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> req) {

        CategoryApiRequest categoryApiRequest = req.getData();
        Optional<Category> optional = categoryRepository.findById(categoryApiRequest.getId());

        return optional.map(category -> {
            category.setType(categoryApiRequest.getType())
                    .setTitle(categoryApiRequest.getTitle())
                    .setCreatedAt(LocalDateTime.now())
                    .setCreatedBy("KSJ");
            return category;
        }).map(newCategory-> categoryRepository.save(newCategory))
                .map(newCategory->response(newCategory))
                .orElseGet(()->Header.ERROR("update failed"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);

        return optional.map(category-> {
            categoryRepository.delete(category);
            return Header.OK();
        })
                .orElseGet(()->Header.ERROR("Category Id Not Found"));

    }

    public Long count() {
        return categoryRepository.count();
    }

    private Header<CategoryApiResponse> response(Category category) {
        CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .type(category.getType())
                .title(category.getTitle())
                .build();

        return Header.OK(categoryApiResponse);
    }
}

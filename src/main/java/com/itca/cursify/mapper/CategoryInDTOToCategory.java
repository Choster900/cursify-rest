package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.service.Storage.StorageService;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Component
public class CategoryInDTOToCategory implements IMapper<CategoryInDTO, Category> {

    private final StorageService storageService;
    private final HttpServletRequest request;

    public CategoryInDTOToCategory(StorageService storageService, HttpServletRequest request) {
        this.storageService = storageService;
        this.request = request;
    }

    @Override
    public Category map(CategoryInDTO in) {
        Category category = new Category();
        category.setCategoryName(in.getCategoryName());

        category.setCategoryPhoto("/media/" + in.getCategoryPhoto());

        String path = storageService.store(in.getFile());
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        category.setCreatedAtCategory(LocalDateTime.now());

        return category;
    }
}

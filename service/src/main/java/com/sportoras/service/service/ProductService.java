package com.sportoras.service.service;

import com.sportoras.database.entity.Material;
import com.sportoras.database.entity.Product;
import com.sportoras.database.repository.MaterialRepository;
import com.sportoras.database.repository.ProductRepository;
import com.sportoras.service.dto.productDto.ProductBasicDto;
import com.sportoras.service.dto.productDto.ProductCreateDto;
import com.sportoras.service.dto.productDto.ProductDtoFilter;
import com.sportoras.service.dto.productDto.ProductFullDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@CacheConfig(cacheNames = "products")
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;

    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    public List<ProductBasicDto> filterProduct(ProductDtoFilter productDtoFilter) {
        return productRepository.findAllByNameBetweenOrderByValue
                (productDtoFilter.getName(), productDtoFilter.getMinValue(), productDtoFilter.getMaxValue(), productDtoFilter.getPageable()).stream()
                .map(it -> new ProductBasicDto(it.getId(), it.getName(), it.getArticle(), it.getPicture()))
                .collect(Collectors.toList());
    }

    @Cacheable
    public List<ProductBasicDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(it -> new ProductBasicDto(it.getId(), it.getName(), it.getArticle(), it.getPicture()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Product saveProduct(ProductCreateDto productCreateDto) {
        Material material = materialRepository.findByName(productCreateDto.getMaterial());

        Product savedProduct = productRepository.save(
                Product.builder()
                        .name(productCreateDto.getName())
                        .article(productCreateDto.getArticle())
                        .picture(productCreateDto.getPicture())
                        .value(productCreateDto.getValue())
                        .material(material)
                        .build());

        return savedProduct;
    }

    public List<ProductBasicDto> findProductByMaterial(Long id) {
        return productRepository.findAllByMaterialId(id).stream()
                .map(it -> new ProductBasicDto(it.getId(), it.getName(), it.getArticle(), it.getPicture()))
                .collect(Collectors.toList());
    }
}

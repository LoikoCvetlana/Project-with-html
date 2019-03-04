package com.sportoras.web.restController;

import com.sportoras.database.entity.Product;
import com.sportoras.service.dto.productDto.ProductBasicDto;
import com.sportoras.service.dto.productDto.ProductCreateDto;
import com.sportoras.service.service.MaterialService;
import com.sportoras.service.service.ProductService;
import lombok.AllArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsController {

    ProductService productService;
    MaterialService materialService;
    private final static Logger LOGGER = LogManager.getLogger(ProductsController.class);

    @GetMapping(value = "/products/")
    public ResponseEntity<List<ProductBasicDto>> listAllProducts() {
        List<ProductBasicDto> products = productService.findAllProducts();
        if (products == null) {
            LOGGER.error("Products are not found");
            throw new EntityNotFoundException("Products not found");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products-by-material/")
    public ResponseEntity<List<ProductBasicDto>> listAllProductsByMaterial(@PathVariable("id") long id) {
        List<ProductBasicDto> products = productService.findProductByMaterial(id);
        if (products == null) {
            LOGGER.error("Products are not found");
            throw new EntityNotFoundException("Products not found");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value = "/product-save/")
    public ResponseEntity<ProductCreateDto> saveProduct(@RequestBody ProductCreateDto productCreateDto) {
        if (productService.findProductByArticle(productCreateDto.getArticle()) != null) {
            LOGGER.error("This product already exists");
            throw new EntityExistsException("Product already exists");
        }
        productService.saveProduct(productCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/product-info/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.findProductById(id);
        if (product == null) {
            LOGGER.error("Product with id " + id + " not found.");
            throw new EntityNotFoundException("Product not found");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}

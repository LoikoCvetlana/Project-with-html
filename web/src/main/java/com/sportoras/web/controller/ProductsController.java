package com.sportoras.web.controller;

import com.sportoras.database.entity.Material;
import com.sportoras.database.entity.Product;
import com.sportoras.service.dto.Material.MaterialDto;
import com.sportoras.service.dto.productDto.ProductBasicDto;
import com.sportoras.service.dto.productDto.ProductCreateDto;
import com.sportoras.service.dto.productDto.ProductDtoFilter;
import com.sportoras.service.service.MaterialService;
import com.sportoras.service.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsController {

    ProductService productService;
    MaterialService materialService;

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<ProductBasicDto> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products-by-material")
    public String getAllProducts(Model model,@RequestParam("id") Long id) {
        List<ProductBasicDto> products = productService.findProductByMaterial(id);
        MaterialDto material = materialService.findById(id);
        model.addAttribute("products", products);
        model.addAttribute("material", material);
        return "products-result";
    }

    @GetMapping("/product-info")
    public String productInfo(Model model, Long id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);

        return "/product-info";
    }

    @GetMapping("/product-save")
    public String openProductSave(Model model) {
        List<MaterialDto> materials = materialService.findAllMaterials();
        model.addAttribute("materials", materials);
        model.addAttribute("productCreateDto", new ProductCreateDto());
        return "product-save";
    }

    @PostMapping("/product-save")
    public String saveNewProduct(ProductCreateDto productCreateDto) {
        productService.saveProduct(productCreateDto);
        return "redirect:/products";
    }

//    @GetMapping("/products-filter")
//    public String openProductFilter(Model model) {
//        model.addAttribute("productDtoFilter", new ProductDtoFilter());
//        return "/products-filter";
//    }
//
//    @PostMapping("/products-filter")
//    public String filteredProducts(ProductDtoFilter productDtoFilter) {
//        List<ProductBasicDto> products = productService.filterProduct(productDtoFilter);
//        return "/products";
//    }
}

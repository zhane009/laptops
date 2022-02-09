package com.zhane.laptops.Controllers;

import com.zhane.laptops.Models.Product;
import com.zhane.laptops.Repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable long id) {
        return productRepository.getOne(id);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.saveAndFlush(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        Product oldProduct = productRepository.getOne(product.getId());
        BeanUtils.copyProperties(product, oldProduct);
        return productRepository.saveAndFlush(oldProduct);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteById(@PathVariable long id) {
        productRepository.deleteById(id);

    }
}

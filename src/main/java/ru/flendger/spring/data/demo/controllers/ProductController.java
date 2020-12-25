package ru.flendger.spring.data.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.flendger.spring.data.demo.model.Product;
import ru.flendger.spring.data.demo.repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        if (min == null && max == null) {
            return (List<Product>) productRepository.findAll();
        }

        if (max == null) {
            return productRepository.findAllByPriceGreaterThanEqual(min);
        }

        if (min == null) {
            return productRepository.findAllByPriceLessThanEqual(max);
        }

        return productRepository.findAllByPriceBetween(min, max);
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        //TODO: пока так, но как корректно вернуть ошибку, не разбирали )))
        return productRepository.findById(id)
                .orElse(null);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}

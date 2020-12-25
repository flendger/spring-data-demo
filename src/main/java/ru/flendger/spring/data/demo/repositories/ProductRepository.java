package ru.flendger.spring.data.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.flendger.spring.data.demo.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int min);
    List<Product> findAllByPriceLessThanEqual(int max);
    List<Product> findAllByPriceBetween(int min, int max);
}

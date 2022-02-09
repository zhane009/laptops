package com.zhane.laptops.Repositories;

import com.zhane.laptops.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.zhane.laptops.Repositories;

import com.zhane.laptops.Models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}

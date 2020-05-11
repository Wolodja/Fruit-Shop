package com.github.fruitshop.repository;

import com.github.fruitshop.domain.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}

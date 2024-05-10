package com.ecommerce.ptcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.ptcommerce.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<String, ProductEntity>{

}

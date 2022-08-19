package com.atcs.product.repo;

import com.atcs.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {
    Product findByProName(String name);

    Product findByProductCode(String code);

    Product findByProBrand(String brand);

    @Query("select p.proPrice from Product p where p.productCode in :codes ")
    List<String> getAllCodes( @Param("codes") List<String> postCodesList);
}

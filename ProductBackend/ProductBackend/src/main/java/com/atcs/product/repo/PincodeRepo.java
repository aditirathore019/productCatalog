package com.atcs.product.repo;

import com.atcs.product.model.Pincode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeRepo extends JpaRepository<Pincode,Long> {

}

package com.syson.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syson.ejercicio.model.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}

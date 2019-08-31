package com.syson.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syson.ejercicio.model.Auto;

@Repository
public interface AutoRepository extends JpaRepository<Auto, Long>{

}

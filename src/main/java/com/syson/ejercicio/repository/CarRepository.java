package com.syson.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syson.ejercicio.dao.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{

}

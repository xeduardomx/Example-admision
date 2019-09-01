package com.syson.ejercicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syson.ejercicio.model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long>{

}

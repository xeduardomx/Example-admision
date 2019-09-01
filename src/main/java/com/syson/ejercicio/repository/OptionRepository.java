package com.syson.ejercicio.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.syson.ejercicio.dao.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Long>{
	@Query("select o from Option o where o.shortCut in ?1 ")
	ArrayList<Option> findListSc(String[] listShortCut);
}

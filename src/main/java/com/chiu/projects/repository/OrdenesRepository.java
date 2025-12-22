package com.chiu.projects.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiu.projects.entities.Ordenes;

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Integer>{

}

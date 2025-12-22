package com.chiu.projects.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chiu.projects.entities.Productos;

@Repository
public interface ProductosRepository extends JpaRepository<Productos, Integer>{
	public List<Productos> findByOrdenId(Integer orderId);
}

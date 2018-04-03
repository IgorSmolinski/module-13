package com.example.hibernate.Invoice.dao;

import com.example.hibernate.Invoice.Product;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional

public interface ProductDao extends CrudRepository<Product,Integer>{
}

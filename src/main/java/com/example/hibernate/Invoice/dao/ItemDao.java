package com.example.hibernate.Invoice.dao;

import com.example.hibernate.Invoice.Item;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ItemDao extends CrudRepository<Item, Integer>{
}

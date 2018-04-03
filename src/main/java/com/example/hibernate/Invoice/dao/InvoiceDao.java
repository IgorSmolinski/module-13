package com.example.hibernate.Invoice.dao;

import com.example.hibernate.Invoice.Invoice;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface InvoiceDao extends CrudRepository <Invoice, Integer> {
}

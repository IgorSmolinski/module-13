package com.example.hibernate.manytomany.dao;

import com.example.hibernate.manytomany.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface EmployeeDao extends CrudRepository<Employee, Integer>{

    @Query
    List<Employee> retrieveEmployeeWithSecondName(@Param("SECONDNAME") String name);
}

package com.example.hibernate.manytomany.dao;

import com.example.hibernate.manytomany.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
//@Repository
public interface CompanyDao extends CrudRepository<Company, Integer> {

    @Query
    List<Company> retrieve3FirstLetters(@Param("X") String name);

    @Query
    List<Company> findCompanyByCompanyNameFragment(@Param("ARG") String arg);
}

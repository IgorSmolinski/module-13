package com.example.hibernate.manytomany.facade;


import com.example.hibernate.manytomany.Company;
import com.example.hibernate.manytomany.Employee;
import com.example.hibernate.manytomany.dao.CompanyDao;
import com.example.hibernate.manytomany.dao.EmployeeDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Getter
@Service
public class Searcher {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;

    public List<Company> companiesSearcher(String arg) {
        return companyDao.findCompanyByCompanyNameFragment(arg);
    }

    public List<Employee> employeesSearcher(String arg) {
        return employeeDao.findEmployeeByLastnameFragment(arg);
    }
}
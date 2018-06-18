package com.example.hibernate.manytomanydao;

import com.example.hibernate.HibernateApplication;
import com.example.hibernate.manytomany.Company;
import com.example.hibernate.manytomany.Employee;
import com.example.hibernate.manytomany.dao.CompanyDao;
import com.example.hibernate.manytomany.dao.EmployeeDao;
import com.example.hibernate.manytomany.facade.Searcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacadeTestSuite {

    @Autowired
    Searcher searcher;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    CompanyDao companyDao;

    @Test
    public void shouldFindEmployeeByStringFragment() {
        //Given
        Employee employee1 = new Employee("Igor", "Smolinski");
        Employee employee2 =new Employee("Piotr", "Mazur");

        employeeDao.save(employee1);
        employeeDao.save(employee2);

        //When
        List<Employee> employeeListResult = searcher.employeesSearcher("%Smo%");

        //Then
        assertThat(employeeListResult.size()).isEqualTo(1);
        assertThat(employeeListResult.get(0).getFirstName()).isEqualTo("Igor");


        try {
            employeeDao.deleteAll();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }

    @Test
    public void shouldFindCompanyNameByStringFragment() {
        //Given
        Company company1 = new Company("Microsoft");
        Company company2 = new Company("Cannonials");
        Company company3 = new Company("ista");

        companyDao.save(company1);
        companyDao.save(company2);
        companyDao.save(company3);

        //When
        List<Company> companyListResult = searcher.companiesSearcher("%soft%");

        //Then
        assertThat(companyListResult.size()).isEqualTo(1);
        assertThat(companyListResult.get(0).getName()).isEqualTo("Microsoft");


        try {
            companyDao.deleteAll();
        } catch (Exception e) {
            System.out.println("Something went wrong!");
        }
    }
}
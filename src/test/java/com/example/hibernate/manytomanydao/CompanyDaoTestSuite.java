package com.example.hibernate.manytomanydao;

import com.example.hibernate.manytomany.Company;
import com.example.hibernate.manytomany.Employee;
import com.example.hibernate.manytomany.dao.CompanyDao;
import com.example.hibernate.manytomany.dao.EmployeeDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void SaveManyToMany(){
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //Then
        Assert.assertNotEquals(0, softwareMachineId);
        Assert.assertNotEquals(0, dataMaestersId);
        Assert.assertNotEquals(0, greyMatterId);

        //CleanUp
                try {
              companyDao.delete(softwareMachineId);
               companyDao.delete(dataMaestersId);
               companyDao.delete(greyMatterId);
           } catch (Exception e) {
             //do nothing
            }
    }

    @Test
    public void QueryEmployeeTest(){
        //Given
        Employee johnSmith = new Employee("John", "Smith");
        Employee stephanieClarckson = new Employee("Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee("Linda", "Kovalsky");

        employeeDao.save(johnSmith);
        int idJohnSmith = johnSmith.getId();
        employeeDao.save(stephanieClarckson);
        int idStephanieClarkson = stephanieClarckson.getId();
        employeeDao.save(lindaKovalsky);
        int idLindaKovalsky = lindaKovalsky.getId();


        //When
        List<Employee> employeesSearchByName = employeeDao.retrieveEmployeeWithSecondName("Smith");

        //Then
        Assert.assertEquals(1,employeesSearchByName.size());

        //CleanUp
        try {
            employeeDao.delete(idJohnSmith);
            employeeDao.delete(idLindaKovalsky);
            employeeDao.delete(idStephanieClarkson);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void QueryCompanyTest(){
        //Given

        Company softwareMachine = new Company("Software Machine");
        Company dataMaesters = new Company("Data Maesters");
        Company greyMatter = new Company("Grey Matter");
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //When
        List<Company> companyListStartsWith = companyDao.retrieve3FirstLetters("Sof");

        //Then
        Assert.assertEquals(1, companyListStartsWith.size());

        //CleanUp
        try {
            companyDao.delete(softwareMachineId);
            companyDao.delete(dataMaestersId);
            companyDao.delete(greyMatterId);
        } catch (Exception e) {
            //do nothing
        }
    }
}

package com.example.hibernate.invoice.dao;

import com.example.hibernate.Invoice.Invoice;
import com.example.hibernate.Invoice.Item;
import com.example.hibernate.Invoice.Product;
import com.example.hibernate.Invoice.dao.InvoiceDao;
import com.example.hibernate.Invoice.dao.ItemDao;
import com.example.hibernate.Invoice.dao.ProductDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceDaoTestSuite {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ProductDao productDao;

    @Test
    public void testInvoiceDaoSave(){
        //Given
        Product kielbasa = new Product("kiełbsa");
        Product ziemniak = new Product("ziemniak");
        Product czosnek = new Product("Czosnek");
        Product cebula = new Product("Cebula");

        productDao.save(kielbasa);
        productDao.save(ziemniak);
        productDao.save(czosnek);
        productDao.save(cebula);

        Item item1 = new Item(new BigDecimal(32),3,new BigDecimal(66));
        Item item2 = new Item(new BigDecimal(10),2, new BigDecimal(20));
        Item item3 = new Item(new BigDecimal(5),8, new BigDecimal(40));
        Item item4 = new Item(new BigDecimal(3), 5, new BigDecimal(15));
        Item item5 = new Item (new BigDecimal(30), 5, new BigDecimal(150));


        item1.setProduct(kielbasa);
        item2.setProduct(ziemniak);
        item3.setProduct(czosnek);
        item4.setProduct(cebula);
        item5.setProduct(kielbasa);

        itemDao.save(item1);
        itemDao.save(item2);
        itemDao.save(item3);
        itemDao.save(item4);
        itemDao.save(item5);


        Invoice invoice1 = new Invoice("1/2018");
        invoice1.getItems().add(item1);
        invoice1.getItems().add(item2);
        invoice1.getItems().add(item3);
        invoice1.getItems().add(item4);
        invoice1.getItems().add(item5);


        //When
        invoiceDao.save(invoice1);
        int id = invoice1.getId();

        //Then
        Assert.assertNotEquals(0,id);

        //CleanUp
        invoiceDao.delete(id);
        itemDao.delete(item1);
        itemDao.delete(item2);
        itemDao.delete(item3);
        itemDao.delete(item4);
        itemDao.delete(item5);
        productDao.delete(kielbasa);
        productDao.delete(cebula);
        productDao.delete(czosnek);
        productDao.delete(ziemniak);






    }


}

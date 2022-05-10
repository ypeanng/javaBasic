package com.yzp.elasticsearch_springboot;

import com.yzp.elasticsearch_springboot.elasticsearch.EmpRepository;
import com.yzp.elasticsearch_springboot.entity.Emp;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

@SpringBootTest
public class TestEmpRespository {

    @Autowired
    private EmpRepository repository;

    @Test
    public void testSave(){
        Emp emp = new Emp();
        emp.setId("123");
        emp.setName("杨召朋");
        emp.setAge(33);
        emp.setBir(Date.from(LocalDate.of(1987, Month.JANUARY,12).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        emp.setContent("领导世界步入长久和平发展年年代");
        emp.setAddress("北京");
        repository.save(emp);
    }

    @Test
    public void testSearch(){


        Iterable<Emp> all = repository.findAll(Sort.by(Order.desc("age")));

        all.forEach(emp -> {
            System.out.println(emp.getName());
        });
    }
    //分页
    @Test
    public void testFindPage(){
        Page<Emp> search = repository.search(QueryBuilders.matchAllQuery(), PageRequest.of(0,20));
        search.forEach(emp -> {
            System.out.println(emp);
        });
    }

}

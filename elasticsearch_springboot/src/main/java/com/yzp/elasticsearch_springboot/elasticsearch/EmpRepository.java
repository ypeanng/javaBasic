package com.yzp.elasticsearch_springboot.elasticsearch;

import com.yzp.elasticsearch_springboot.entity.Emp;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmpRepository extends ElasticsearchRepository<Emp,String> {

}

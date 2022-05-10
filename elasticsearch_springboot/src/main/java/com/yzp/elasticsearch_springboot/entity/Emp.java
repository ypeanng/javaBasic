package com.yzp.elasticsearch_springboot.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "ems",type = "emp")
public class Emp {
    @Id
    String id;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    String name;
    @Field(type = FieldType.Integer)
    int age;
    @Field(type=FieldType.Date)
    Date bir;
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    String content;
    @Field(type = FieldType.Keyword)
    String address;
}

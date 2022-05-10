package com.yzp.esconfig;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Emp {
    String id;
    String name;
    int age;
    Date bir;
    String content;
    String address;
}

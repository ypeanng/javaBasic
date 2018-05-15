package javaapi.CallByValue;

import lombok.Data;

@Data
public class Person {
    String name;
    String age;
    String sex;
    Child child;
}

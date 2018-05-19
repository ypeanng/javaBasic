package javaapi.jdk.CallByValue;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Person {
    @JsonProperty("@n")
    String name;
    @JsonProperty("a")
    String age;
    @JsonProperty("s")
    String sex;
    @JsonProperty("c")
    Child child;
}

package webflux.b2_create;

public class PersonEntity {

    private int age;
    private String name;

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
            "age=" + age +
            ", name='" + name + '\'' +
            '}';
    }
}

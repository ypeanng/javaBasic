package javaapi.CallByValue;

public class CallByValue {

    public static void main(String[] args) {
        //封装类型
        String input = "tease|dfa";
        splitFirstPart(input);
        System.out.println(input);
        System.out.printf("========================");
        //类传递
        Person person = new Person();
        person.setAge("3");
        person.setName("yang zhaopeng");
        person.setSex("1");
        Child girl = new Child();
        girl.setName("Bella");
        person.setChild(girl);
        changeName(person);
        System.out.println("========================");
        System.out.println(person.name);
        System.out.println(person.getChild().getName());
        System.out.println("========================");
        changePerson(person);
        System.out.println(person.getName());
        System.out.println("========================");
        String countryName = "china::beijing";
        String test = countryName;
        countryName = countryName.split("::")[0];
        System.out.println("test is "+test);
        System.out.println("countryName is "+countryName);
    }

    private static void changePerson(Person person) {
        //再次被初始化，不会传递回去
        person = new Person();;
        person.setName("another");

    }


    private static void changeName(Person person) {

        person.setName("han xuemeng");
        person.getChild().setName("Lucia");

    }

    private static void splitFirstPart(String input) {
        input = input.split("\\|")[0];
    }
}

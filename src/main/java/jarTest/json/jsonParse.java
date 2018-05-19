package jarTest.json;

import javaapi.jdk.CallByValue.Person;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class jsonParse {

    /**
     * 文本在经过html转换并再次转换回文本时，转义字符会被转义为元字符而不是转义字符。
     * &amp; --> &
     * 如何控制&amp;不被转义为& ？œ
     * @param args
     */
    public static void main(String[] args) {
        Person person = new Person();
        String name = "test&amp;是否可以展示";
        System.out.println(name);
        String nameConv = name.replace("&amp;","&amp;amp;");
        person.setName(nameConv);
        System.out.println(person.getName());
        String afterJson = JsonUtil.toJson(person);

        Document document = Jsoup.parse(afterJson);
        afterJson = document.body().text();
        Person person1 = JsonUtil.fromJson(afterJson,Person.class);
        System.out.println(person1.getName());
    }
}

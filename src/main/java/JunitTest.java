import java.util.ArrayList;
import java.util.List;

public class JunitTest {
    static List<Integer> forbidOtaSaleBusinessTypes=new ArrayList<>();
    static {
        forbidOtaSaleBusinessTypes.add(4);
        forbidOtaSaleBusinessTypes.add(11);
    }
    public static void main(String[] args) {
        class A{
            boolean forbidOtaSale;
            public boolean getForbidOtaSale(){
                return forbidOtaSale;
            }
        }
        A a = new A();
        System.out.println(a.getForbidOtaSale());


        if(forbidOtaSaleBusinessTypes.contains(null)){
            System.out.println("OK");
        }else {
            System.out.println("no OK");
        }

        if(forbidOtaSaleBusinessTypes.contains(11)){
            System.out.println("contains");
        }


    }
}

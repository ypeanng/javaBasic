package function_java;

import java.util.function.Function;

public class FunctionTest {

    public static void main(String[] args) {
        System.out.println(calc(10, v -> v * 2));
        System.out.println(calc2(10, v -> 2 * v, v -> v + 3));
    }

    static Integer calc(Integer a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

    //先执行 compose 后的function
    static Integer calc2(Integer a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
        return function1.compose(function2).apply(a);
    }
}

package webflux.a1_zip;

import java.time.Duration;
import reactor.core.publisher.Flux;

public class MainCls {

    public static void main(String[] args) throws InterruptedException {
        Flux<String> pilots = Flux.range(1, 100)
            .delayElements(Duration.ofSeconds(2))
            .map(pilot -> {
                return pilot + "号飞行员";
            });
        Flux<String> planes = Flux.range(1, 100)
            .delayElements(Duration.ofSeconds(2))
            .map(plane -> {
                return plane + "号飞机";
            });
        Flux<Integer> nums = Flux.range(1, 100)
            .delayElements(Duration.ofSeconds(2))
            .map(plane -> {
                return plane;
            });
        Flux.zip(pilots, planes,nums)
            .map(tuple -> {
                String pilot = tuple.getT1();
                String plane = tuple.getT2
                    ();
                Integer s = tuple.getT3();
                return pilot + "-" + plane + " 起飞" + "：：："+s;
            })
            .subscribe(System.out::println);

        String result = "";
        Flux.zip(pilots, planes, (A, B) -> {
            return A + B + " 起飞啦";
        })
            .subscribe(System.out::println);

        Thread.sleep(100000);
    }
}

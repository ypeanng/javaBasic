package javaapi.jdk.ConcurrentTest;

import io.netty.util.concurrent.CompleteFuture;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class ExecutorServiceTest {

    public static void main(String[] args) {
        List<String> cunt = new ArrayList<>();
        cunt.add("2");
        cunt.add("3");
        cunt.add("4");
        cunt.add("5");
        cunt.add("6");
        List<CompletableFuture> futures = new ArrayList<>();
        for (String num : cunt) {
            CompletableFuture<String> names = CompletableFuture.supplyAsync(()->{
                return num;
            });
            futures.add(names);
        }
        //TODO join 怎么设置超时？
        futures.stream()
//            .map(CompletableFuture::join)
            .map((future)->{
                try {
                    return future.get(4, TimeUnit.SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                return null;
            })
            .collect(Collectors.toList())
        .forEach(str->{
            System.out.println(str);
        });
//        executorService.submit()
    }
}

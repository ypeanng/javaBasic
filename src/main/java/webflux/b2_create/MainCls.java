package webflux.b2_create;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class MainCls {

    // ParseJson 处理时间比较长，通过create 以及 开线程的方式，实现异步非阻塞的处理
    public static void main(String[]  arg) throws InterruptedException {
        String json = "dfasdfwefasdfasoidfhnvaosdfnasd";
        log.info("{}-{}",Thread.currentThread().getName(),Thread.currentThread().getId());
        Mono.fromSupplier(()->{
            return new PersonEntity();
        }).subscribe();

        Mono.<PersonEntity>create(monoSink -> {
            log.info("{}-{}",Thread.currentThread().getName(),Thread.currentThread().getId());


            ParseJson.parse(json,new ParseBack<PersonEntity>(){
                @Override
                public void onFailed(Throwable t) {
                    log.info("failed {}-{}",Thread.currentThread().getName(),Thread.currentThread().getId());
                    monoSink.error(t);
                }
                @Override
                public void onSuccess(PersonEntity entity){
                    log.info("success {}-{}",Thread.currentThread().getName(),Thread.currentThread().getId());
                    monoSink.success(entity);
                }
            });
            monoSink.onDispose(()->{
                log.info("dispose {}-{}",Thread.currentThread().getName(),Thread.currentThread().getId());
            });
        }).map(n->{
            log.info("success | {}",n.toString());
            return n;
        }).subscribe(System.out::println);
        Thread.sleep(10000000);
    }
}

package webflux.b2_create;

import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParseJson {

    public static void parse(String json, ParseBack parseback) {
        log.info("{}-{}", Thread.currentThread().getName(), Thread.currentThread().getId());
        //异步很重要，否则依然是阻塞调用
        CompletableFuture.runAsync(() -> {
            PersonEntity entity = new PersonEntity();
            try {
                int div = 0;
//                int ns = 1/div;
                entity.setAge(18);
                entity.setName("周彤");
//                    PersonEntity entity = JsonUtil.parse(json,Entity.class);
                parseback.onSuccess(entity);
            } catch (Exception ex) {
                parseback.onFailed(ex);
            }
        });
    }
}

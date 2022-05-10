package webflux.b2_create;

public interface ParseBack<T> {
    void onSuccess(T t);
    void onFailed(Throwable t);
}

package reentranlock;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.LockSupport;

public class PiersLock {
    private volatile int status = 0;
    private Queue<Thread> threadQueue = new LinkedBlockingQueue<>();
    public void lock(){
        while(!compareAndSet(0,1)){
            park();
        }
        System.out.println("lock success");
        unlock();
    }

    private boolean compareAndSet(int i, int i1) {
        //实现CAS
        if(status==i){
            status = i1;
            return true;
        }
        return false;
    }

    private void park(){
        threadQueue.add(Thread.currentThread());
        LockSupport.park();
    }
    private void unlock(){
        status= 0;
        lock_notify();
    }
    private void lock_notify(){
        Thread thread = threadQueue.poll();
        LockSupport.unpark(thread);
    }
}



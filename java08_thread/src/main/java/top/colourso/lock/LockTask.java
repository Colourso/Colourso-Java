package top.colourso.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @date 2022/3/13 22:50
 */
public class LockTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(LockTask.class);

    // 多个任务使用同一个锁，需要共同拥有一个对象，那就是作为构造参数来构造任务了
    private Lock lock;
    private Condition condition;

    public LockTask(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        log.info("get lock");
        // 获取锁
        lock.lock();
        try {
            // 等待
            condition.await();
            log.info("{} is await", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("err", e);
        } finally {
            // 释放锁
            lock.unlock();
        }
    }
}

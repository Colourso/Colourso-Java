package top.colourso.lock;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @date 2022/3/13 22:49
 */
public class MyLockTask {

    private static final Logger log = LoggerFactory.getLogger(MyLockTask.class);

    public static void main1(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        LockTask task = new LockTask(lock,condition);
        Thread t1 = new Thread(task);
        t1.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            log.error("err",e);
        }
        // 获取锁
        lock.lock();
        log.info("signal a thread");
        // 唤醒单个等待的线程
        condition.signal();
        // 释放锁
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Integer> datas = Lists.newArrayList();
        ProdLock prod = new ProdLock(lock,condition,datas);
        ConsLock cons = new ConsLock(lock,condition,datas);

        Thread t1 = new Thread(prod);
        Thread t2 = new Thread(cons);

        Thread.sleep(3000);

        t1.start();
        t2.start();

    }
}

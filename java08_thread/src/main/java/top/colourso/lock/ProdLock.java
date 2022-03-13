package top.colourso.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 生产者
 *
 * @date 2022/3/13 23:04
 */
public class ProdLock implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ProdLock.class);
    private static int max = 10;

    private Lock lock;
    private Condition condition;
    private List<Integer> datas;

    public ProdLock(Lock lock, Condition condition, List<Integer> datas) {
        this.lock = lock;
        this.condition = condition;
        this.datas = datas;
    }

    // 向容器中生产数据，生产完成之后等待，被唤醒之后继续生产

    @Override
    public void run() {
        int num = 0;
        while (true) {
            // 1.获取锁
            lock.lock();
            if (datas.size() >= max) {
                // 2.唤醒消费者，然后等待
                log.info("prod# datas is max, call cons and await");
                condition.signal();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 3.释放锁
                    lock.unlock();
                }
            } else {
                log.info("prod# produce a product:{}", num);
                datas.add(num);
                num++;
            }
        }
    }
}

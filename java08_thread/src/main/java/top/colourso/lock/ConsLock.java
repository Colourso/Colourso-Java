package top.colourso.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 消费者
 * @date 2022/3/13 23:04
 */
public class ConsLock implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(ConsLock.class);
    private static int min = 0;

    private Lock lock;
    private Condition condition;
    private List<Integer> datas;

    public ConsLock(Lock lock, Condition condition, List<Integer> datas) {
        this.lock = lock;
        this.condition = condition;
        this.datas = datas;
    }

    // 消费者，消费数据，消费完之后唤醒生产者然后等待

    @Override
    public void run() {
        while (true){
            lock.lock();
            if(datas.size()<= min){
                // 2.唤醒
                log.info("cons# datas is empty, call prod & await");
                condition.signalAll();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }else{
                log.info("cons# consumer a data:{}", datas.get(datas.size()-1));
                datas.remove(datas.size()-1);
            }
        }
    }
}

package top.colourso.pordcousmer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 生产者进行生产数据，生产之后唤醒消费者，然后自身等待，
 * 直到消费者消费之后再唤醒生产者
 *
 * @date 2022/3/6 21:59
 */
public class Produce implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Produce.class);

    private Data data;

    public Produce(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            synchronized (data){
                if (data.getMsg() == null) {
                    log.info("produce# produce a msg:{}", i);
                    data.setMsg("produce a msg " + i++);
                    // 唤醒消费者
                    data.notify();
                    // 自身等待
                    try {
                        data.wait();
                    } catch (Exception e) {
                        log.error("err: ", e);
                    }
                }
            }
        }
    }
}

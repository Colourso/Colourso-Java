package top.colourso.pordcousmer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @date 2022/3/6 22:06
 */
public class Consumer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    // 1 两个不同的线程如何使用同一个锁？ 使用同一个对象作为他们的属性
    private Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (data) {
                if (data.getMsg() != null) {
                    log.info("consumer# consumer a data:{}", data.getMsg());
                    data.setMsg(null);
                    // 唤醒生产者
                    data.notify();
                    // 自身等待
                    try {
                        data.wait();
                    } catch (Exception e) {
                        log.error("err:", e);
                    }
                }
            }
        }
    }
}

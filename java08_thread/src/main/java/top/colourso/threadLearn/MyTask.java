package top.colourso.threadLearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @date 2022/3/6 19:13
 */
public class MyTask implements Runnable {

    private static int titck = 10;
    private static final Logger log = LoggerFactory.getLogger(MyTask.class);

    @Override
    public void run() {
        while (titck > 0) {
            // 同步代码块 使用this、对象类型、类类型作为锁
            // 同步方法使用this来作为锁
            // 静态同步方法使用类类型作为锁
            synchronized (MyTask.class) {
                if (titck > 0) {
                    log.info("thread:{} sale a piao:{}", Thread.currentThread().getName(), titck);
                    titck--;
                }
            }
        }
    }
}

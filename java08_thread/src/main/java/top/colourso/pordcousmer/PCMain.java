package top.colourso.pordcousmer;

import sun.awt.windows.ThemeReader;

/**
 * @date 2022/3/6 22:11
 */
public class PCMain {

    public static void main(String[] args) {

        Data d1 = new Data();
        Produce produce = new Produce(d1);
        Consumer consumer = new Consumer(d1);

        Thread t1= new Thread(produce);
        Thread t2 = new Thread(consumer);

        t1.start();
        t2.start();
    }

}

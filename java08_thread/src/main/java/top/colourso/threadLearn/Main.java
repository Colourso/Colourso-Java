package top.colourso.threadLearn;

/**
 * @date 2022/3/6 21:32
 */
public class Main {

    public static void main(String[] args) {
        MyTask myTask = new MyTask();

        new Thread(myTask).start();
        new Thread(myTask).start();
        new Thread(myTask).start();
        Thread t1 = new Thread(myTask);
        t1.start();


    }


}

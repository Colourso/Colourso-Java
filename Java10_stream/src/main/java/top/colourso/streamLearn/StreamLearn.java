package top.colourso.streamLearn;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 流的学习
 * @date 2021/12/19 21:17
 */
public class StreamLearn {

    //1. 存储 顺序输出的任意java对象实例，
    //2. 用于 内存计算、业务逻辑
    //3.不同于List, stream的元素惰性计算，并没有预先存储在内存中，而是实时计算
    // 可以存储有限或者无限个元素
    //4.特点，一个stream可以轻易转为另一个stream，
    // 因为惰性计算的存在，stream之间的转换指存储了转换规则，并没有发生任何计算
    // 可以把stream的操作写为链式操作


    /* 使用数组、Collection的引入，将现有序列变为Stream，元素固定 */
    public void test1(){

        /**
         * list of方法在java9中引入
         */
        // Stream stream = List.of("1","2","3").;

        Stream<String> stream1 = Lists.newArrayList("1","2","3").stream();
        // System.out::println 这是传入方法的引用
        // 方法引用是指，方法参数一致，返回类型相同，这是就指两者签名一致
        // 方法签名只看参数类型和返回类型，不看方法名称，也不看类的继承关系。
        stream1.forEach(System.out::println);
    }

    /* 基于Supplier的引入，可变序列的stream */
    public void test2(){
        Stream<Integer> natual = Stream.generate(new NatualSupplier());

        // 不使用Limit会进入死循环，这将会计算一个无线序列
        natual.limit(20).forEach(System.out::println);
    }

    class NatualSupplier implements Supplier<Integer> {
        int n = 0;

        @Override
        public Integer get() {
            n++;
            return n;
        }
    }

    /* 基本类型的Stream，免去频繁的装箱拆箱操作 */
    public void test3(){
        IntStream is = Arrays.stream(new int[] {1,2,3});
        is.forEach(System.out::println);
    }

}

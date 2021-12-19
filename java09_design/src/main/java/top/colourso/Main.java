package top.colourso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.colourso.Create.Factory.NumberFactory;

/**
 * @date 2021/12/14 0:02
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /* 测试工厂方法 */
    public static void test1(){
        /* 实际的调用方可以忽略真正的实现类和实际的产品（BigDecimal）
        * 这样的好处就是允许创建产品的代码独立变换，而不影响调用方
        *  */
        NumberFactory factory = NumberFactory.getFactory();
        Number number = factory.parse("123.44");
        logger.info("number:[{}]",number);
    }

    /* 查看Integer.valueoOf()的静态工厂 */
    public static void test2(){
        /* 静态工厂：使用静态方法创建产品 */
        Integer.valueOf(100);

        /*
        工厂方法可以隐藏创建产品的细节，且不一定每次都会真正创建产品，
        完全可以返回缓存的产品，从而提升速度并减少内存消耗。
         public static Integer valueOf(int i) {
             if (i >= IntegerCache.low && i <= IntegerCache.high)
             return IntegerCache.cache[i + (-IntegerCache.low)];
             return new Integer(i);
         }
         */

    }

    public static void main(String[] args) {
        test1();
    }

}

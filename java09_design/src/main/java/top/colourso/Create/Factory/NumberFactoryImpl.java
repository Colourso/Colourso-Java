package top.colourso.Create.Factory;

import java.math.BigDecimal;

/**
 * @date 2021/12/14 0:06
 */
public class NumberFactoryImpl implements NumberFactory{
    @Override
    public Number parse(String s) {
        return new BigDecimal(s);
    }
}

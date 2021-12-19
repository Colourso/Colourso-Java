package colourso.Create.Factory;

/**
 * @date 2021/12/14 0:05
 */
public interface NumberFactory {
    /**
     * 创建方法
     */
    Number parse(String s);

    /**
     * 获取工厂实例
     */
    static NumberFactory getFactory(){
        return impl;
    }

    static NumberFactory impl = new NumberFactoryImpl();
}

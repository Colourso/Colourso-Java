package top.colourso.proxy;

import java.lang.reflect.Proxy;

/**
 * @date 2022/10/19 9:10
 */
public class Main {

    public static void main(String[] args) {
        JDKProxy proxy = new JDKProxy(new RealOrderService());
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        // 生成的类保存到文件
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        // 生成代理类
        OrderService service = (OrderService) Proxy.newProxyInstance(classLoader, new Class[]{OrderService.class}, proxy);
        // 方法调用
        System.out.println(service.getOrderInfo("1"));
    }
}

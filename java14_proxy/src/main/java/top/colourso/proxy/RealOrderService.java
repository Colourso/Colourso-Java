package top.colourso.proxy;

/**
 * @date 2022/10/20 23:56
 */
public class RealOrderService {
    // 实际执行的方法
    public String invoke(String orderId){
        // 通过和服务建立连接，使用网络请求，进行序列化反序列化等，最终获取到请求的结果
        return "a order info " + orderId;
    }
}

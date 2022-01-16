package top.colourso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.colourso.impl.UserDaoImpl;

/**
 * @date 2022/1/16 16:39
 */
public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        // 内部参数未beanId的名字
//        UserDaoImpl userDao= (UserDaoImpl) context.getBean("userDao");
//
//        userDao.doing();


        test2();
    }


    public static void test2(){
        logger.info("测试不同配置下的两个bean是否相同");

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDaoImpl userDao1= (UserDaoImpl) context.getBean("userDao");

        UserDaoImpl userDao2= (UserDaoImpl) context.getBean("userDao");

        logger.info("prototype: 1:{}",userDao1);
        logger.info("prototype: 2:{}",userDao2);
    }

}

package top.colourso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.colourso.impl.UserDaoImpl;

/**
 * @date 2022/1/16 17:29
 */


public class UserDaoTest {

    public void testSingleton(){

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDaoImpl userDao = (UserDaoImpl) app.getBean("userDao");
        userDao.doing();


    }

}

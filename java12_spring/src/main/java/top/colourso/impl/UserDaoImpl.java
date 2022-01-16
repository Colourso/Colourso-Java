package top.colourso.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.colourso.UserDao;

/**
 * @date 2022/1/16 16:37
 */
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);


    @Override
    public void doing() {
        logger.info("doing...");
    }
}

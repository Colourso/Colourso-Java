package top.colourso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.colourso.Maps.MapLearn;

/**
 * @date 2021/12/13 23:41
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        logger.info("main# start");

        MapLearn mapLearn = new MapLearn();
        mapLearn.testNullGetMap();

        logger.info("main# end");
    }
}

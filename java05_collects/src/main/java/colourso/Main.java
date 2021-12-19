package colourso;

import colourso.listLearn.ListLearn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @date 2021/12/20 0:03
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);




    public static void main(String[] args) {

        //logger.info("main# start");

//        MapLearn mapLearn = new MapLearn();
//        mapLearn.testNullGetMap();

        System.out.println("1");
        ListLearn listLearn = new ListLearn();
        listLearn.testObjContainList2();

        //logger.info("main# end");
    }
}

package colourso.listLearn;

import colourso.listLearn.model.Item;
import colourso.listLearn.model.OutItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2021/12/19 23:25
 */
public class ListLearn {

    private static final Logger logger = LoggerFactory.getLogger(ListLearn.class);

    /*
    * 测试一个包含list的对象被new之后直接访问对象时的情况
    * */

    public static class Obj{
        private int cunt;
        private List<String> list;

        public int getCunt() {
            return cunt;
        }

        public void setCunt(int cunt) {
            this.cunt = cunt;
        }

        public List<String> getList() {
            return list;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }

    public void testObjContainList(){
        Obj obj = new Obj();
        for(String s : obj.getList()){
            logger.info("testObjContainList# s:{}",s);
        }
    }

    public void testObjContainList2(){
        System.out.println("11");
        OutItem outItem = new OutItem();
        logger.info("testObjContainList2");
        for (Item item : outItem.getItems()){
            logger.info("testObjContainList2# item:{}",item);
        }

    }

}

package colourso.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 2021/12/13 23:34
 */

public class MapLearn {

    /* 1.如果map中get不存在的内容，会报错吗？ | 不会 */
    public void testNullGetMap(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("1","wang");
        map.put("2","li");
        String s = map.get("3");
        System.out.println(s); // null
    }


}

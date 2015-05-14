package royalplate2.royalplate.data;

import com.parse.ParseObject;

/**
 * Created by operamac on 5/7/15.
 */
public class OrdersInQueueData extends ParseObject {

    OrdersInQueueData(){}

    public int getTableNum() {
        return getInt("TableNo");
    }
    public void setTableNum(int tableNo) {
        put("TableNo", tableNo);
    }


}

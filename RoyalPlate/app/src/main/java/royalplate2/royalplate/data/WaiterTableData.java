package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 5/16/15.
 */
@ParseClassName("WaiterTable")


public class WaiterTableData extends ParseObject {

    /**
     * Created by hetu on 5/2/15.
     */


        public WaiterTableData(){}

        public String getWaiter() {
            return getString("WaiterName");
        }


        public void setWaiter(String waiterName) {
            put("WaiterName",waiterName);
        }
        public String getTable(){
            return getString("TableNo");
        }
        public void setTable(String table){
            put("TableNo", table);
        }


    }

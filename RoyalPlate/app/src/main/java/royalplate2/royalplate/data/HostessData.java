package royalplate2.royalplate.data;

import com.parse.ParseObject;

/**
 * Created by hetu on 5/2/15.
 */
public class HostessData extends ParseObject{
    HostessData(){}

    public String getTable() {

        return getString("TableNumber");
    }
    public void setTable(String tableNo) {

        put("Number", tableNo);

    }
     public String getWaiter()

     {
         return getString("WaiterName");
     }


    public void setWaiter(String waiterName)
    {

        put("WaiterName", waiterName);
    }

}


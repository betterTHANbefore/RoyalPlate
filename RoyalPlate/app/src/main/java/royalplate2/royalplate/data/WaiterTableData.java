package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 5/16/15.
 */
@ParseClassName("WaiterTable")
public class WaiterTableData extends ParseObject {

        public WaiterTableData(){}





    public String getGuestName(){return getString("GuestName");}
    public void setGuestName(String guestname){
        put("GuestName", guestname);
    }

    public String getNoGuest(){return getString("NoOfGuest");}
    public void setNoOfGuest(String noofguest){ put("NoOfGuest", noofguest); }

    public String getTable(){ return getString("TableNo"); }
    public void setTable(String table){
        put("TableNo", table);
    }

    public String getWaiter() {
        return getString("WaiterName");
    }
    public void setWaiter(String waiterName) {
        put("WaiterName",waiterName);
    }

    public String getDate(){return getString("Date");}
    public void setDate(String date){
        put("Date", date);
    }

    public String getTime(){return getString("Time");}
    public void setTime(String time){
        put("Time", time);
    }

    public String getName() {
        return getString("ItemName");
    }
    public void setName(String itemName) {
        put("ItemName", itemName);
    }







    }

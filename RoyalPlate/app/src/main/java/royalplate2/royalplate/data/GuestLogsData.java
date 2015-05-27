
package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sh on 5/17/15.
 */
@ParseClassName("GuestLogsParse")

public class GuestLogsData extends ParseObject {
    public GuestLogsData() {}

    public String getGuestName(){return getString("GuestName");}
    public void setGuestName(String guestname){
        put("GuestName", guestname);
    }
    public String getNoGuest(){return getString("NoOfGuest");}
    public void setNoOfGuest(String noofguest){
        put("NoOfGuest", noofguest);
    }

    public String getTableNo(){return getString("TableNo");}
    public void setTableNo(String tableno){
        put("TableNo", tableno);
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
    public String getWaiterName(){return getString("WaiterName");}
    public void setWaiterName(String waitername){
        put("WaiterName", waitername);
    }
    public void setName(String itemName) {
        put("ItemName", itemName);
    }

    public String getPayment(){ return getString("Payment");}
    public void setPayment(String payment){ put("Payment", payment);}
}


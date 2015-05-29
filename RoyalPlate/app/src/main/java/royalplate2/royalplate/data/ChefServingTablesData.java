package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 5/16/15.
 */
@ParseClassName("ChefServingTablesParse")
public class ChefServingTablesData extends ParseObject {

        public ChefServingTablesData(){}




//    public String getItemName(){
//        return getString("ItemName");
//
//    }
//    public void setItemName( String itemname){
//        put("ItemName", itemname);
//    }

//
//    public String getItemPrice(){
//        return getString("ItemPrice"); }
//
//    public void setItemPrice(String itemprice)
//    {
//        put("ItemPrice", itemprice);
//    }


    public String getNoOfItems(){  return getString("NoOfItems"); }

    public void setNoOfItems(String noofitems){
        put("NoOfItems", noofitems);
    }




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

//    public String getWaiter() {
//        return getString("WaiterName");
//    }
//    public void setWaiter(String waiterName) {
//        put("WaiterName",waiterName);
//    }
//
//    public String getDate(){return getString("Date");}
//    public void setDate(String date){
//        put("Date", date);
//    }
//
//    public String getTime(){return getString("Time");}
//    public void setTime(String time){
//        put("Time", time);
//    }

    public String getName() {
        return getString("ItemName");
    }
    public void setName(String itemName) {
        put("ItemName", itemName);
    }







    }

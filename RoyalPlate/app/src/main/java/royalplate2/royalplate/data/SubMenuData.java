package royalplate2.royalplate.data;

import com.parse.ParseObject;

/**
 * Created by operamac on 4/24/15.
 */

public class SubMenuData extends ParseObject {

    public SubMenuData() {}
    public String getName() {

        return getString("ItemName");
    }
    public void setName(String itemName) {

        put("ItemName", itemName);
    }
    public int getID(){

        return getInt("ItemID");
    }
    public void setID(int ID){
        put("ItemID", ID);
    }

    public double getPrice() {
        return getDouble("ItemPrice");
    }

    public void setPrice(double price) {

        put("ItemPrice", price);
    }
}

package royalplate2.royalplate.data;

import com.parse.ParseObject;

/**
 * Created by operamac on 4/24/15.
 */

// This MenuData is the parent of all ***MenuData classes.
public class MenuData extends ParseObject {

    public MenuData() {}

    public String getName() {
        return getString("ItemName");
    }
    public void setName(String itemName) {
        put("ItemName", itemName);
    }

    public double getPrice() {
        return getDouble("ItemPrice");
    }

    public void setPrice(double price) {

        put("ItemPrice", price);
    }
}


package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sh on 5/17/15.
 */
//@ParseClassName("Table1")
public class TableItemData extends ParseObject {
    public TableItemData() {}

    public String getName() {
        return getString("ItemName");
    }
    public void setName(String itemName) {
        put("ItemName", itemName);
    }
}



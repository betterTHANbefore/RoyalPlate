
package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by sh on 5/17/15.
 */
@ParseClassName("Table1")
public class ChefSideOrderedListData extends ParseObject {
    public ChefSideOrderedListData() {}

    public String getName() {
        return getString("Test");
    }
    public void setName(String itemName) {
        put("Test", itemName);
    }
}



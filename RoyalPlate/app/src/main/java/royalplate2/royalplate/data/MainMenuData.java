package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 4/24/15.
 */
@ParseClassName("MenuParse")
public class MainMenuData extends ParseObject
{

    public MainMenuData() {}

    public String getMenuName() {

        return getString("MenuType");
    }

    public void setMenuName(String itemName) {

        put("MenuType", itemName);
    }


}

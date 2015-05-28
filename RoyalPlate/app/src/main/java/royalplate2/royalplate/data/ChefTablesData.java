package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 4/24/15.
 */
@ParseClassName("ChefTablesParse")
public class ChefTablesData extends ParseObject
{

    public ChefTablesData() {}

    public String getItemName(){
        return getString("ItemName");

    }
    public void setItemName( String itemname){
        put("ItemName", itemname);
    }


    public String getItemPrice(){
        return getString("ItemPrice"); }

    public void setItemPrice(String itemprice)
    {
        put("ItemPrice", itemprice);
    }


    public String getNoOfItems(){  return getString("NoOfItems"); }

    public void setNoOfItems(String noofitems){
        put("NoOfItems", noofitems);
    }

    public String getTableNo(){
        return getString("TableNo");

    }
    public void setTableNo(String tableno){
        put("TableNo", tableno);
    }

}

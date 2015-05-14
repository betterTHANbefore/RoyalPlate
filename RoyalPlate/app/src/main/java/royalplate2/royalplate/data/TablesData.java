package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 5/2/15.
 */
@ParseClassName("TablesParse")
public class TablesData extends ParseObject{
   public TablesData(){}
    public String getTable(){
    return getString("TableNumber");
}

    public void setTable(String tablenumber) {
        put("TableNubmer", tablenumber);
    }
}

//
//public class TablesData extends HostessData{
//
//    public TablesData(){}
//
//}

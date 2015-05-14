package royalplate2.royalplate.data;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by hetu on 5/2/15.
 */
//@ParseClassName("User")
@ParseClassName("WaiterParse")

public class WaiterData extends ParseObject{


        public WaiterData(){}

    public String getWaiter() {
        return getString("WaiterName");
    }


    public void setWaiter(String waiterName) {
        put("WaiterName",waiterName);
    }

//    public String getUser(){
//        return getString("username");
//
//    }
//    public void setUser(String user){
//        put("username", user);
//
//   }
}
//

//public class WaiterData extends HostessData{
//
//
//    public WaiterData(){}


//}


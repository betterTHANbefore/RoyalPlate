package royalplate2.royalplate;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;
import royalplate2.royalplate.data.AppertizerMenuData;
import royalplate2.royalplate.data.BurgerMenuData;
import royalplate2.royalplate.data.ChefSideOrderedListData;
import royalplate2.royalplate.data.DessertsMenuData;
import royalplate2.royalplate.data.DrinkMenuData;
import royalplate2.royalplate.data.EntreesMainData;
import royalplate2.royalplate.data.EntreesMenuData;
import royalplate2.royalplate.data.FreshSaladsData;
import royalplate2.royalplate.data.HaveitallMenuData;
import royalplate2.royalplate.data.KidsMenuData;
import royalplate2.royalplate.data.LunchComboMenuData;
import royalplate2.royalplate.data.MainMenuData;
import royalplate2.royalplate.data.NewBarMenuData;
import royalplate2.royalplate.data.OrderedListData;
import royalplate2.royalplate.data.SandwichMenuData;
import royalplate2.royalplate.data.TablesData;
import royalplate2.royalplate.data.TwoTwentyData;
import royalplate2.royalplate.data.WaiterData;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by operamac on 4/14/15.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // connect to parse account
        Parse.initialize(this,
                getString(R.string.parse_app_id),
                getString(R.string.parse_client_id));

        ParseObject.registerSubclass(AppertizerMenuData.class);
        ParseObject.registerSubclass(BurgerMenuData.class);
        ParseObject.registerSubclass(DessertsMenuData.class);
        ParseObject.registerSubclass(DrinkMenuData.class);
        ParseObject.registerSubclass(EntreesMenuData.class);
        ParseObject.registerSubclass(EntreesMainData.class);
        ParseObject.registerSubclass(FreshSaladsData.class);
        ParseObject.registerSubclass(HaveitallMenuData.class);
        ParseObject.registerSubclass(KidsMenuData.class);
        ParseObject.registerSubclass(LunchComboMenuData.class);
        ParseObject.registerSubclass(MainMenuData.class);
        ParseObject.registerSubclass(NewBarMenuData.class);
        ParseObject.registerSubclass(SandwichMenuData.class);
        ParseObject.registerSubclass(TwoTwentyData.class);
        ParseObject.registerSubclass(TablesData.class);
        ParseObject.registerSubclass(WaiterData.class);
        ParseObject.registerSubclass(ChefSideOrderedListData.class);
        ParseObject.registerSubclass(WaiterTableData.class);
        ParseObject.registerSubclass(OrderedListData.class);

        Log.i("Application", "Initialized");
    }
}

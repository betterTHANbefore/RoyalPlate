package royalplate2.royalplate;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseObject;
import royalplate2.royalplate.data.AppertizerMenuData;
import royalplate2.royalplate.data.BurgerMenuData;
import royalplate2.royalplate.data.ChefServingTablesData;
import royalplate2.royalplate.data.GuestBillData;
import royalplate2.royalplate.data.GuestLogsData;
import royalplate2.royalplate.data.OrderedListLogsData;
import royalplate2.royalplate.data.TableItemData;
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
import royalplate2.royalplate.data.TableItemData1;
import royalplate2.royalplate.data.TableItemData2;
import royalplate2.royalplate.data.TableItemData3;
import royalplate2.royalplate.data.TableItemData4;
import royalplate2.royalplate.data.TableItemData5;
import royalplate2.royalplate.data.TableItemData6;
import royalplate2.royalplate.data.TableItemData7;
import royalplate2.royalplate.data.TableItemData8;
import royalplate2.royalplate.data.TableItemData9;
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
        ParseObject.registerSubclass(ChefServingTablesData.class);
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
        ParseObject.registerSubclass(GuestBillData.class);
        ParseObject.registerSubclass(GuestLogsData.class);
        ParseObject.registerSubclass(WaiterTableData.class);
        ParseObject.registerSubclass(TableItemData1.class);
        ParseObject.registerSubclass(TableItemData2.class);
        ParseObject.registerSubclass(TableItemData3.class);
        ParseObject.registerSubclass(TableItemData4.class);
        ParseObject.registerSubclass(TableItemData5.class);
        ParseObject.registerSubclass(TableItemData6.class);
        ParseObject.registerSubclass(TableItemData7.class);
        ParseObject.registerSubclass(TableItemData8.class);
        ParseObject.registerSubclass(TableItemData9.class);
        ParseObject.registerSubclass(OrderedListData.class);
        ParseObject.registerSubclass(OrderedListLogsData.class);


        Log.i("Application", "Initialized");
    }
}
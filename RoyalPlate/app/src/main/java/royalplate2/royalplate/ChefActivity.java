package royalplate2.royalplate;


/*******************************************************************
 * USER EXPLICITE Intent to send the notification to Waiter Activity
 *
 *******************************************************************/


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by hetu on 4/10/15.
 */
public class ChefActivity extends Activity {

    ListView listview;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_activity);
        listview = (ListView) findViewById(R.id.itemlist);

        TextView subMenuTitle;

        String tableNumStr;
        /*****************************************************************
         * subMenuTitle TextView set the title according to which button
         * is pressed.
         * ImageView for tp set the images according to submenu to left
         * and right
         ****************************************************************/
        subMenuTitle = (TextView) findViewById(R.id.submenuTitle_textview);
        subMenuTitle.setText(getIntent().getExtras().getString("title"));

        ImageView icon_right = (ImageView) findViewById(R.id.imageRight_icon);
        ImageView icon_left = (ImageView) findViewById(R.id.imageLeft_icon);
        /***************************************************************
         * get the text from the textview
         ***************************************************************/
        // title = subMenuTitle.getText().toString();

        tableNumStr = getIntent().getExtras().getString("tableNum");
        int tableNum = Integer.parseInt(tableNumStr);

        switch (tableNum) {
            case 1:
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("AppetizerMenuParse");
                break;

//            case "BEVERAGES":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.fries);
//                icon_left.setImageResource(R.drawable.chocolatemilk);
//                loadItems("DrinkMenuParse");
//                break;
//
//            case "BURGERS":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.fries);
//                icon_left.setImageResource(R.drawable.chocolatemilk);
//                loadItems("BurgerMenuParse");
//                break;
//
//            case "DESSERTS":
//                loadItems("DessertsMenuParse");
//                break;
//
//            case "ENTREES":
//                loadItems("EntreesMenuParse");
//                break;
//
//            case "ENTREES & MAIN DISHES":
//                loadItems("EntreesMainMenuParse");
//                break;
//
//            case "FRESH SALADS":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.gardensalad);
//                icon_left.setImageResource(R.drawable.spinachsalad);
//                loadItems("FreshSaladsParse");
//                break;
//
//            case "HAVE IT ALL":
//                // Image appears in ImageView widgets from the source file
//
//                icon_right.setImageResource(R.drawable.springrolls);
//                icon_left.setImageResource(R.drawable.springrolls);
//                loadItems("HaveitallMenuParse");
//                break;
//
//            case "KIDS":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.fries);
//                loadItems("KidsMenuParse");
//                break;
//
//            case "LUNCH COMBOS":
//                loadItems("LunchCombosMenuParse");
//                break;
//
//            case "NEW BAR MENU":
//                loadItems("NewBarMenuParse");
//                break;
//
//            case "SANDWICHES":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.fries);
//                icon_left.setImageResource(R.drawable.chocolatemilk);
//                loadItems("SandwichMenuParse");
//                break;
//
//            case "2 FOR $20":
//                // Image appears in ImageView widgets from the source file
//                icon_right.setImageResource(R.drawable.fries);
//                icon_left.setImageResource(R.drawable.chocolatemilk);
//                loadItems("TwoTwenty");
//                break;

            default:
                subMenuTitle.setText(getIntent().getExtras().getString("To go Main Manu"));
        }

        Button confirmBtn;
        confirmBtn = (Button) findViewById(R.id.confirmButton);
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Confirm Button ","Pressed");
//                Intent confirmIntent = new Intent(getApplicationContext(), MenuActivity.class);
//                startActivity(confirmIntent);
            }
        });

        /*****************************************************************************************
         * if order confirmed, order button send all the items ordered by the customer to the CHEF
         ******************************************************************************************/
//        Button orderBtn = (Button) findViewById(R.id.orderbutton);
//        orderBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent orderBtnIntent = new Intent(getApplicationContext(), SendOrder.class);
////                final ParseQuery<ParseObject> items = ParseQuery.getQuery("KidsMenuParse");
////
////                menuAdapter = new MenuAdapter(SubMenuActivity.this, items);
////                listview.setAdapter(menuAdapter);
//                // this textview will be the confirmed order list
////                TextView tv = (TextView)view.findViewById(R.id.label);
////                String orderList = tv.getText().toString(); //get the text
////                orderBtnIntent.putExtra("Ordered Items List", orderList);
////                orderBtnIntent.putExtra("table no", 1);
//                orderBtnIntent.putExtra("table no", 1);
//                startActivity(orderBtnIntent);
//            }
//        });
    }

    private void loadItems(String str) {

        final ParseQuery<ParseObject> items = ParseQuery.getQuery(str);
        items.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> items, ParseException e) {
                menuAdapter = new MenuAdapter(ChefActivity.this, items);
                listview.setAdapter(menuAdapter);
            }
        });
    }
}

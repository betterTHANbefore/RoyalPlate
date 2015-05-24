package royalplate2.royalplate;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import royalplate2.royalplate.adapter.SubMenuAdapter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hetu on 4/11/15.
 */
public class SubMenuActivity extends FragmentActivity implements SimpleGestureFilter.SimpleGestureListener{

    ListView listview;
    SubMenuAdapter menuAdapter;
    SharedPreferences shared;
    SharedPreferences preferences;
    String tableNo;
    String itemName;
    String noOfItems;
    String title;
    String itemCost;
    //    String submenutitle;
//    TextView subMenuTitle;
    TextView tableNoTextview;
    Intent intent;
    public static final String ORDEREDLISTSHARED = "orderedlistSharedPreferences";


    private SimpleGestureFilter detector;
    private boolean rightSwipeFlag = false;
    Set<String> orderedItemList = new HashSet<String>();

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                Log.i("SWIPE","TO THE RIGHT");
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;
        }

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        // We only care about right swipe that intents to go back to MenuActivity

        if (direction == SimpleGestureFilter.SWIPE_RIGHT) {

            tableNo = getIntent().getExtras().getString("tableNo");


//            itemName = getIntent().getExtras().getString("Item Name");
//            noOfItems = getIntent().getExtras().getString("No of Items");
//            itemCost = getIntent().getExtras().getString("Item Cost");



            noOfItems = shared.getString("No of Items", "");
            itemName = shared.getString("Item Name", "");
            itemCost = shared.getString("Item Cost", " ");
            tableNo = shared.getString("tableNo", "");





            intent = new Intent(this, MenuActivity.class);
            intent.putExtra("tableNo", tableNo);
//            intent.putExtra("iniPrice" , 0);
//            intent.putExtra("iniNoOfItem", 0);
//            intent.putExtra("No of Items", noOfItems);
//            intent.putExtra("Item Name", itemName );
//            intent.putExtra("Item Cost", itemCost);

            Log.i("LOG", itemName +  "   " + noOfItems  + " "+ itemCost);
            Log.i("LOG", itemName +  "   " + noOfItems);


            startActivity(intent);
        }
    }

    @Override
    public void onDoubleTap() {

    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.submenu_activity);
        listview = (ListView) findViewById(R.id.itemlist);

        /*****************
         * Create orderedlistSharedPreferences table to store
         * no of items, itemname and price
         */



        shared = PreferenceManager.getDefaultSharedPreferences(this);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        TextView subMenuTitle;
        TextView tableNo;
        String title;

        /*****************************************************************
         * subMenuTitle TextView set the title according to which button
         * is pressed.
         * ImageView for tp set the images according to submenu to left
         * and right
         ****************************************************************/

        subMenuTitle = (TextView) findViewById(R.id.submenuTitle_textview);
        // TO DO : below need to be made pretty

        subMenuTitle.setText(getIntent().getExtras().getString("title"));

//        final SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
//        subMenutitle = shared.getString("title", "");
//        subMenuTitle.setText( subMenutitle);
        //   tableNo = getIntent().getExtras().getString("tableNo"); // pass table no to adapter

        ImageView icon_right = (ImageView) findViewById(R.id.imageRight_icon);
        ImageView icon_left = (ImageView) findViewById(R.id.imageLeft_icon);
        /***************************************************************
         * get the text from the textview
         ***************************************************************/

        noOfItems = shared.getString("No of Items", "");
        itemName = shared.getString("Item Name", "");
        itemCost = shared.getString("Item Cost", " ");





        // saveOrderedList("hello", "2","2.00");
//        noOfItems = shared.getString("No of Items", null);
//        itemName = shared.getString("Item Name",null);
//        itemCost = shared.getString("Item Cost", null);
//

//        shared.edit().clear().apply();


        Log.i("Tag", " SubActivity  ++"   + itemName + "   "+ noOfItems + " "+ itemCost);

        final ListView list = (ListView) findViewById(R.id.itemlist);
        list.post(new Runnable() {
            @Override
            public void run() {
                list.setSelection(0);
            }
        });

        title = getIntent().getExtras().getString("title");
        Log.i("Tag", " SubActivity title"   + title);

        // title = shared.getString("title", null);

        switch (title) {
            case "APPETIZER":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("AppetizerMenuParse");
                break;

            case "BEVERAGES":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("DrinkMenuParse");
                break;

            case "BURGERS":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("BurgerMenuParse");
                break;

            case "DESSERTS":
                loadItems("DessertsMenuParse");
                break;

            case "ENTREES":
                loadItems("EntreesMenuParse");
                break;

            case "ENTREES & MAIN DISHES":
                loadItems("EntreesMainMenuParse");
                break;

            case "FRESH SALADS":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.gardensalad);
                icon_left.setImageResource(R.drawable.spinachsalad);
                loadItems("FreshSaladsParse");
                break;

            case "HAVE IT ALL":
                // Image appears in ImageView widgets from the source file

                icon_right.setImageResource(R.drawable.springrolls);
                icon_left.setImageResource(R.drawable.springrolls);
                loadItems("HaveitallMenuParse");
                break;

            case "KIDS":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                loadItems("KidsMenuParse");
                break;

            case "LUNCH COMBOS":
                loadItems("LunchCombosMenuParse");
                break;

            case "NEW BAR MENU":
                loadItems("NewBarMenuParse");
                break;

            case "SANDWICHES":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("SandwichMenuParse");
                break;

            case "2 FOR $20":
                // Image appears in ImageView widgets from the source file
                icon_right.setImageResource(R.drawable.fries);
                icon_left.setImageResource(R.drawable.chocolatemilk);
                loadItems("TwoTwenty");
                break;

            default:
                intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
//               subMenuTitle.setText(getIntent().getExtras().getString("To go Main Manu"));
        }

        // this contains OrderListFragment class
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentContainer, new OrderListFragment()).commit();

        // Detect touched area
        detector = new SimpleGestureFilter(this,this);

//        Intent backIntent = new Intent(this, OrderListFragment.class);

        JSONObject tempJSON = new JSONObject();
        try {
            tempJSON.put("itemName", itemName);
            tempJSON.put("itemPrice", itemCost);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        addItemToSet(tempJSON);

//        JSONObject tempJSON = new JSONObject();
//        try {
//            tempJSON.put("itemName", "Sue");
//            tempJSON.put("itemPrice", 2.99);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }



        JSONObject tempJSON2 = new JSONObject();
        try {
            tempJSON2.put("itemName", "lolo");
            tempJSON2.put("itemPrice", 3.99);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        addItemToSet(tempJSON2);
        updateOrderedList();
    }

    void updateOrderedList() {
//        private void addItemToSet(Pair pair) {
//            try {
//                tempJsonObj.put("itemName", "Barley Juice" );
//                tempJsonObj.put("itemPrice", 2.99);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
////            orderedItemList.add(pair);
//        }
//
//        private void removeItemFromSet(Pair pair) {
//            orderedItemList.remove(pair);
//        }

//        JSONObject tempJsonObj = new JSONObject(
//
//        );

//        @Override
//        protected String doInBackground(String... params) {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences preferences = getSharedPreferences("OrderedItemSet", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
//        editor.put
//            JSONObject tempJsonObj = new JSONObject();

//            try {
//                tempJsonObj.put("itemName", "Barley Juice" );
//                tempJsonObj.put("itemPrice", 2.99);
//            } catch (JSONException e) {
//                e.printStackTrace();
////            }
//            orderedItemList.add("Sue");
//            orderedItemList.add("jeong");
//            orderedItemList.add("ha");
//            orderedItemList.add("what");
//            editor.putStringSet("Item Set", orderedItemList.toString());
        editor.putStringSet("OrderedItemSet", orderedItemList);
//            editor.putString
        editor.apply();

//            Set<String> test = shared.getStringSet("OrderedItemSet", new HashSet<String>());
//            Log.i("OrderedItemSet", test.toString());
//            Log.i("Item Name", itemName);
//            Log.i("No of Items", noOfItems);
//            return null;
//        }
    }

//
//    public void saveOrderedList(String itemName, String numItems){
//
//        // getting data value from SubMenuAdapter
//        // passing data value to OrderListFragment class
//        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = shared.edit();
////        editor.put
//        editor.putString("Item Name", itemName);
//        editor.putString("No of Items", noOfItems);
//        editor.apply();
//        Log.i("Item Name", itemName);
//        Log.i("No of Items", noOfItems);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private void loadItems(String str) {
        //  final int itemCost = getIntent().getExtras().getInt("iniPrice");

        final ParseQuery<ParseObject> items = ParseQuery.getQuery(str);
        items.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> items, ParseException e) {
                // menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items, itemCost, tableNumber );
//                menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items, itemCost, tableNo, SubMenuActivity.this );
                menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items,  tableNo, SubMenuActivity.this );

                listview.setAdapter(menuAdapter);
            }
        });
    }


    // empty constructor

    public SubMenuActivity(){}


    public void saveOrderedList( String noOfItems, String itemName, String itemcost){

        // getting data value from SubMenuAdapter
        // passing data value to OrderListFragment class
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences orderedlistSharedPreferences = getSharedPreferences(ORDEREDLISTSHARED, mode);



        SharedPreferences.Editor editor = orderedlistSharedPreferences.edit();
        //   editor.putString("tableNo", tableno);
        editor.putString("No of Items", noOfItems);
        editor.putString("Item Name", itemName);
        editor.putString("Item Cost", itemcost);
        editor.apply();

//        SharedPreferences.Editor editor = shared.edit();
////        editor.put
//        editor.putString("Item Name", itemName);
//        editor.putString("No of Items", noOfItems);
//        editor.putString("Item Cost", itemcost);
//        editor.apply();

        Log.i("tag" , "SubMenuActivity saveorder  " + noOfItems  + "    " + itemName+ "  " + itemcost);

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Log.i("TAG", "onSave");
//        final  TextView itempricetextview = (TextView) findViewById(R.id.cost);
//        final EditText noofitemedittext = (EditText) findViewById(R.id.no_of_items);
//
////
////        CharSequence textview = itemnametextview.getText();
////        CharSequence edittext = noofitemedittext.getText().toString();
////
////        outState.putCharSequence("savedItemPrice", (CharSequence) noofitemedittext);
////        outState.putCharSequence("savedNoItem", (CharSequence) itempricetextview);
//
//        String itemprice = itempricetextview.getText().toString();
//        String noofitems= noofitemedittext.getText().toString();
//        outState.putString("Price", itemprice);
//        outState.putString("NoItem", noofitems);
//        Log.i("TAG", " saveed  "  + itemprice);
//
//
//
//    }
//
//
////
//    @Override
//    protected void onRestoreInstanceState(Bundle savedState) {
//        super.onRestoreInstanceState(savedState);
//
//        Log.i("TAG", "onRestore");
//        final  TextView itemnametextview = (TextView) findViewById(R.id.cost);
//        final EditText noofitemedittext = (EditText) findViewById(R.id.no_of_items);
//
//
//        CharSequence textview = savedState.getCharSequence("savedItemPrice");
//        CharSequence edittext = savedState.getCharSequence("savedNoofItems");
//
//        itemnametextview.setText(textview);
//        noofitemedittext.setText(edittext);
//
//
//    }
//        outState.putCharSequence("savedItemPrice", (CharSequence) noofitemedittext);
//        outState.putCharSequence("savedNoItem", (CharSequence) itempricetextview);

//        String itemprice = itempricetextview.getText().toString();
//        String noofitems= noofitemedittext.getText().toString();
//        outState.putString("Price", itemprice);
//        outState.putString("NoItem", noofitems);
//        Log.i("TAG", " saveed  "  + itemprice);

    //}


    // }

    private void addItemToSet(JSONObject jsonObject) {
//        orderedItemList.add(jsonObject);
//        try {
//            tempJsonObj.put("itemName", "Barley Juice" );
//            tempJsonObj.put("itemPrice", 2.99);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        orderedItemList.add(jsonObject.toString());
        Log.i("JSON", orderedItemList.toString());
    }

    private void removeItemFromSet(JSONObject jsonObject) {
        orderedItemList.remove(jsonObject);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

        Log.i("TAG", "onRestore");
        final  TextView itemnametextview = (TextView) findViewById(R.id.itemcost_textviewid);
        final EditText noofitemedittext = (EditText) findViewById(R.id.noOfitem_edittextid);

        CharSequence textview = savedState.getCharSequence("savedItemPrice");
        CharSequence edittext = savedState.getCharSequence("savedNoofItems");

        itemnametextview.setText(textview);
        noofitemedittext.setText(edittext);
    }

}


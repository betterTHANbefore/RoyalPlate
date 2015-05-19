package royalplate2.royalplate;

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
import royalplate2.royalplate.adapter.SubMenuAdapter;

import java.util.List;

/**
 * Created by hetu on 4/11/15.
 */
public class SubMenuActivity extends FragmentActivity implements SimpleGestureFilter.SimpleGestureListener{

    ListView listview;
    SubMenuAdapter menuAdapter;
    SharedPreferences shared;
    String tableNo;
    String itemName;
    String noOfItems;

    private SimpleGestureFilter detector;
    private boolean rightSwipeFlag = false;

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

            itemName = getIntent().getExtras().getString("Item Name");
            noOfItems = getIntent().getExtras().getString("No of Items");
            tableNo = getIntent().getExtras().getString("tableNo");


            Intent intent = new Intent(this, MenuActivity.class);
            intent.putExtra("tableNo", tableNo);
//            intent.putExtra("iniPrice" , 0);
//            intent.putExtra("iniNoOfItem", 0);

            intent.putExtra("Item Name", itemName );
            intent.putExtra("No of Items", noOfItems);

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

        shared = PreferenceManager.getDefaultSharedPreferences(this);


        TextView subMenuTitle;
        TextView tableNo;
        Button goToMenuBtn;
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
//        subMenuTitle.setText(getIntent().getExtras().getString("title") + "    "+ getIntent().getExtras().getString("tableNo"));

     //   tableNo = getIntent().getExtras().getString("tableNo"); // pass table no to adapter

        ImageView icon_right = (ImageView) findViewById(R.id.imageRight_icon);
        ImageView icon_left = (ImageView) findViewById(R.id.imageLeft_icon);
        /***************************************************************
         * get the text from the textview
         ***************************************************************/
        Log.i("Test1", " SubActivit  "   + itemName + "   "+ noOfItems);


        title = getIntent().getExtras().getString("title");


        final ListView list = (ListView) findViewById(R.id.itemlist);
        list.post(new Runnable() {
            @Override
            public void run() {
                list.setSelection(0);
            }
        });


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
                subMenuTitle.setText(getIntent().getExtras().getString("To go Main Manu"));
        }


        // this contains OrderListFragment class
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentContainer, new OrderListFragment()).commit();

        // Detect touched area
        detector = new SimpleGestureFilter(this,this);

//        Intent backIntent = new Intent(this, OrderListFragment.class);



    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }

    private void loadItems(String str) {
        final int itemCost = getIntent().getExtras().getInt("iniPrice");

        final ParseQuery<ParseObject> items = ParseQuery.getQuery(str);
        items.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> items, ParseException e) {
               // menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items, itemCost, tableNumber );
                menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items, itemCost, tableNo, SubMenuActivity.this );

                listview.setAdapter(menuAdapter);
            }
        });
    }


    // empty constructor

    public SubMenuActivity(){}


    public void saveOrderedList( String noOfItems,String itemname, String itemcost){

        // getting data value from SubMenuAdapter
        // passing data value to OrderListFragment class

        SharedPreferences.Editor editor = shared.edit();
        editor.putString("No of Items", noOfItems);
        editor.putString("Item Name", itemname);
        editor.putString("Item Cost", itemcost);

        editor.apply();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.i("TAG", "onSave");
        final  TextView itempricetextview = (TextView) findViewById(R.id.cost);
        final EditText noofitemedittext = (EditText) findViewById(R.id.no_of_items);

//
//        CharSequence textview = itemnametextview.getText();
//        CharSequence edittext = noofitemedittext.getText().toString();
//
//        outState.putCharSequence("savedItemPrice", (CharSequence) noofitemedittext);
//        outState.putCharSequence("savedNoItem", (CharSequence) itempricetextview);

        String itemprice = itempricetextview.getText().toString();
        String noofitems= noofitemedittext.getText().toString();
        outState.putString("Price", itemprice);
        outState.putString("NoItem", noofitems);
        Log.i("TAG", " saveed  "  + itemprice);



    }


//
    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);

        Log.i("TAG", "onRestore");
        final  TextView itemnametextview = (TextView) findViewById(R.id.cost);
        final EditText noofitemedittext = (EditText) findViewById(R.id.no_of_items);


        CharSequence textview = savedState.getCharSequence("savedItemPrice");
        CharSequence edittext = savedState.getCharSequence("savedNoofItems");

        itemnametextview.setText(textview);
        noofitemedittext.setText(edittext);


    }

}



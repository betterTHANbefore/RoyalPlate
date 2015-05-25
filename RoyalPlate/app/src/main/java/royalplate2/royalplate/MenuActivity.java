package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.MainMenuAdapter;
import royalplate2.royalplate.data.MainMenuData;

import java.util.List;


/**
 * Created by hetu on 4/9/15.
 */
public class MenuActivity extends Activity implements SimpleGestureFilter.SimpleGestureListener {

    GridView gridview;
    MainMenuAdapter mainMenuAdapter;
    ImageView previousImageview;
    ImageView nextImageview;
    private Button orderedButton;
    private TextView tableNumView;

    private String menuItemName;
    String tableNum;
    String itemName;
    String noOfItems;
    String itemCost;
    String username;
    String tableno;
    // SharedPreferences shared;// = PreferenceManager.getDefaultSharedPreferences(this);


    private SimpleGestureFilter detector;
    private boolean leftSwipeFlag = false;
    public static final String LOGINSHARED = "loginSharedPreferences";

    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";
    public static final String ORDEREDLISTSHARED = "orderedlistSharedPreferences";


    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {
            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                Log.i("SWIPE","TO THE LEFT");
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;
        }

        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        /******************************************************************************************
         * We only care about left swipe that intents to go to MainMenuActivity
         ******************************************************************************************/

        if (direction == SimpleGestureFilter.SWIPE_LEFT) {

            Intent  intent = new Intent(this, SubMenuActivity.class);
            // putExtra params need to be set up correctly accordingly what we need to pass
            intent.putExtra("title", "menuItemName");
            intent.putExtra("tableNo", "tableno");
            intent.putExtra("Item Name", itemName);
            intent.putExtra("No of Items", noOfItems);
            intent.putExtra("Item Cost", itemCost);
            intent.putExtra("userName", username);
            startActivity(intent);
        }
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainmenu_activity);

        // tableNumView = (TextView) findViewById(R.id.table_num_view);

        /******************************************************************************************
         * Loads MainMenu Items name from parse using MenuAdapter
         ******************************************************************************************/
        loadMainMenuItems();

        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
        username = loginSharedPreferences.getString("userName", "");

        SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
        tableno = assignedtablesSharedPreferences.getString("tableNo", "");


        SharedPreferences orderedlistSharedPreferences = getSharedPreferences(ORDEREDLISTSHARED, mode);
        noOfItems = orderedlistSharedPreferences.getString("No of Items", "");
        itemName = orderedlistSharedPreferences.getString("Item Name", "");
        itemCost = orderedlistSharedPreferences.getString("Item Cost","");
        //  tableNum = orderedlistSharedPreferences.getString("tableNo", "");


        Log.i("Tag", "MenuActivity  "   + itemName + "   "+ noOfItems + " " + itemCost);

        gridview = (GridView) findViewById(R.id.menulist_right);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Button listBtn  = (Button) parent.getChildAt(position).findViewById(R.id.mainmenu);
                menuItemName = listBtn.getText().toString();
                // tableNum = getIntent().getExtras().getString("tableNo");

//                itemName = getIntent().getExtras().getString("Item Name");
//                noOfItems = getIntent().getExtras().getString("No of Items");

//                gridviewIntent.putExtra("title", menuItemName);
//                gridviewIntent.putExtra("tableNo", tableNum);
//                gridviewIntent.putExtra("Item Name", itemName);
//                gridviewIntent.putExtra("No of Items", noOfItems);
//                gridviewIntent.putExtra("Item Cost", itemCost);

                //startActivity(gridviewIntent);
                //   SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);

//                SharedPreferences.Editor editor = shared.edit();
//                editor.putString("title", menuItemName);
//                editor.putString("tableNo", tableNum);
//                editor.putString("Item Name", itemName);
//                editor.putString("No of Items", noOfItems);
//                editor.putString("Item Cost", itemCost);

//                editor.apply();
                Intent    intent = new Intent(MenuActivity.this, SubMenuActivity.class);


                intent.putExtra("title", menuItemName);
                //  intent.putExtra("tableNo", tableno);
//                intent.putExtra("tableNo", tableNum);


//                intent.putExtra("Item Name", itemName);
//                intent.putExtra("No of Items", noOfItems);
//                intent.putExtra("Item Cost", itemCost);
//                noOfItems = shared.getString("No of Items", "");
//
//                itemName = shared.getString("Item Name", "");
//                itemCost = shared.getString("Item Cost","");
//                tableNum = shared.getString("tableNo", "");


                startActivity(intent);
//                Log.i("Tag", "MenuActivit_2  "    + itemName + "   "+ noOfItems + " " + itemCost);


            }
        });

        /**********************************************************************************************
         * Detect touched area
         **********************************************************************************************/

        detector = new SimpleGestureFilter(this,this);

        /**********************************************************************************************
         * Previous Image Icon go back to Assigned table activity. (List of assigned Tables)
         **********************************************************************************************/

        previousImageview = (ImageView) findViewById(R.id.previousImageviewid);

        previousImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AssignedTableActivity.class);
                startActivity(intent);
            }
        });

        /**********************************************************************************************
         * Next Image Icon go back to Submeuactivity.
         **********************************************************************************************/

//        nextImageview = (ImageView) findViewById(R.id.nextImageviewid);
//
//        nextImageview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MenuActivity.this, SubMenuActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        this.detector.onTouchEvent(ev);
        return super.dispatchTouchEvent(ev);
    }





    /***********************************************************************************************
     * This function loads the data from the parse, where the class is
     * called "MenuParse". It uses MainMenuAdapter. And Listview to
     * display the data.
     **********************************************************************************************/
    private void loadMainMenuItems() {
        final ParseQuery<MainMenuData> mainMenuItems = ParseQuery.getQuery("MenuParse");
        mainMenuItems.findInBackground(new FindCallback<MainMenuData>() {

            @Override
            public void done(List<MainMenuData> mainMenuItems, ParseException e) {
                mainMenuAdapter = new MainMenuAdapter(MenuActivity.this, mainMenuItems);
                gridview.setAdapter(mainMenuAdapter);
                Log.i("menuparseloading?", "Yeah?");

            }
        });
    }
}




/**********************************************
 * if user clicks on the RoyalPlate_logo, it will
 * activate the SelectActivity.
 *************************************************/

//     final ImageView  royalplate = (ImageView) findViewById(R.id.royalplate_logo);
//        royalplate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent royalplateIntent = new Intent(getApplicationContext(), SelectActivity.class);
//                startActivity(royalplateIntent);
//            }
//        });










package royalplate2.royalplate;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashSet;
import java.util.List;

import royalplate2.royalplate.adapter.MainMenuAdapter;
import royalplate2.royalplate.adapter.OrderedListAdapter;
import royalplate2.royalplate.data.MainMenuData;
import royalplate2.royalplate.data.OrderedListData;

/**
 * Created by operamac on 4/29/15.
 */

// This class belongs to SubMenuActivity
public class OrderListFragment extends Fragment {

    private View v;
    private TextView tv;
    TextView displayList;
    ListView ordereditemslistview;
    OrderedListAdapter orderedListAdapter;

    //String tableNumStr;
//    String itemName;
//    String noOfItems;

    String itemName;
    String noOfItems;
    String itemCost;

    private String tableNumStr;
    private String privous = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        v = inflater.inflate(R.layout.fragment_orderlist, container, false);

        ordereditemslistview = (ListView) v.findViewById(R.id.ordereditems_listview);


        /*******************************************************************************************
         * Retrieving data values from MenuActivity through SharedPreferences and Intent.
         ******************************************************************************************/


        SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(getActivity());
        noOfItems = shared.getString("No of Items", "");
        itemName = shared.getString("Item Name", "");
        itemCost = shared.getString("Item Cost","");

        tableNumStr = getActivity().getIntent().getExtras().getString("tableNo");
//        itemName = getActivity().getIntent().getExtras().getString("Item Name");
//        noOfItems = getActivity().getIntent().getExtras().getString("No of Items");
//        itemCost =  getActivity().getIntent().getExtras().getString("Item Cost");

        /*******************************************************************************************
         * Set Table No as a title in Ordered item list
         ******************************************************************************************/

        tv = (TextView) v.findViewById(R.id.tableNo_textview);
        tv.setText(tableNumStr);


        /*******************************************************************************************
         * Set Data values to Parse Class(OrderedListParse) through OrderListData java class
         ******************************************************************************************/
        OrderedListData orderedListData = new OrderedListData();
            orderedListData.setItemName(itemName);
            orderedListData.setItemPrice(itemCost);
            orderedListData.setTableNo(tableNumStr);
            orderedListData.setNoOfItems(noOfItems);
            orderedListData.saveInBackground();

        Log.i("OF2", itemName + "  " + noOfItems + " " + itemCost);
        shared.edit().clear().apply();


        loadOrderedList();

        Log.i("Tag", "Load Ordered List");
//        ParseObject poTest = new ParseObject("Table1");
//        poTest.put("Test", "Mange Juice");
//        poTest.put("Test", "Mil Juice");
//        poTest.put("Test", "other Juice");
//        poTest.put("Test", "barley Juice");

//        poTest.saveInBackground();

//        TextView tv = (TextView) v.findViewById(R.id.tableNo_textview);
//        tv.setText("Table " + tableNumStr);

//        TextView listOfitems = (TextView) v.findViewById(R.id.orderedlist);
//
//        Log.i("OF", itemName + "   "+ noOfItems);
//        listOfitems.setText(itemName + " " + noOfItems);


        /**************************************************************************
         * getting itemName and noOfItems from Submenu Activity
         *
         * Go to SubMenuAdapter where actual data values are passed thru INTENT
         **************************************************************************/


//        TextView noOfitemsTextview = (TextView) v.findViewById(R.id.orderednoOfItems);
//        TextView listOfitemsTextview = (TextView) v.findViewById(R.id.orderedItemName);

//      String  itemName = getActivity().getIntent().getExtras().getString("Item Name");
//     String   noOfItems = getActivity().getIntent().getExtras().getString("No of Items");
//        Bundle arg = getArguments();
//        Log.i("ARGUMENT", arg.toString() );
//        itemName = getActivity().getIntent().getExtras().getString("Item Name");
//        noOfItems = getActivity().getIntent().getExtras().getString("No of Items");
//







//        itemName = getActivity().getIntent().getExtras().getString("tableNo");
//        noOfItems = getActivity().getIntent().getExtras().getString("title");

        Log.i("OF", itemName + "  "+ noOfItems + " "+  itemCost);

//        noOfitemsTextview.setText(noOfItems);
//        listOfitemsTextview.setText(itemName);

        /* Textview to display item name and no dinamically added to Scrollview


         */
       // LinearLayout ll = (LinearLayout) v.findViewById(R.id.linearlayout);


       // TextView displayList = new TextView(getActivity());
//        displayList = new TextView(getActivity());
//        displayList.setTextSize(15);
//        displayList.setTextColor(getResources().getColor(R.color.antiquewhite));
//        displayList.setTypeface(null,Typeface.BOLD);
//
//        privous = displayList.getText().toString();
//
//      //  if(!privous.equals(null) && )
//
//
//
//        displayList.setText(noOfItems+ "     " + itemName );
//
//        ll.addView(displayList);
//

//        SharedPreferences sharedPreferences;
//

//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        Set<String> orderedItemList = sharedPreferences.getStringSet("OrderedItemList", new HashSet<String>());

//        JSONArray jsonArray = (JSONArray) orderedItemList;
//


        final Button orderBtn = (Button) v.findViewById(R.id.orderbutton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // temporaly going back to MenuActivity
              //  Intent orderBtnIntent = new Intent(v.getContext(), OrderSucceedActivity.class);
            //    Intent orderBtnIntent = new Intent(v.getContext(), ChefActivity.class);
            // temporaly going back to MenuActivity
            Intent orderBtnIntent = new Intent(v.getContext(), OrderSucceedActivity.class);

            orderBtnIntent.putExtra("tableNo", tableNumStr);

//                orderBtnIntent.putExtra("table no", 1);

            String tableNum = getActivity().getIntent().getExtras().getString("tableNo");
            orderBtnIntent.putExtra("table no", tableNum);




            startActivity(orderBtnIntent);
            }
        });

        return v;
    }

    /***********************************************************************************************
     * Load the ordered list from parse of that given Table No
     ***********************************************************************************************/

    private void loadOrderedList() {

        final ParseQuery<OrderedListData> orderedlist = ParseQuery.getQuery("OrderedListParse");
        orderedlist.whereEqualTo("TableNo",tableNumStr);
        orderedlist.findInBackground(new FindCallback<OrderedListData>() {
            @Override
            public void done(List<OrderedListData> orderedlist, ParseException e) {
                orderedListAdapter = new OrderedListAdapter(getActivity().getApplicationContext(), orderedlist);
                ordereditemslistview.setAdapter(orderedListAdapter);

            }
        });




    }


//    private void loadMainMenuItems() {
//        final ParseQuery<MainMenuData> mainMenuItems = ParseQuery.getQuery("MenuParse");
//        mainMenuItems.findInBackground(new FindCallback<MainMenuData>() {
//
//            @Override
//            public void done(List<MainMenuData> mainMenuItems, ParseException e) {
//                mainMenuAdapter = new MainMenuAdapter(MenuActivity.this, mainMenuItems);
//                gridview.setAdapter(mainMenuAdapter);
//
//            }
//        });
//    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        TextView noOfitemsTextview = (TextView) v.findViewById(R.id.orderednoOfItems);
//        TextView listOfitemsTextview = (TextView) v.findViewById(R.id.orderedItemName);
//
//        /**************************************************************************
//         * getting itemName and noOfItems from Submenu Activity
//         **************************************************************************/
//        itemName = getActivity().getIntent().getExtras().getString("Item Name");
//        noOfItems = getActivity().getIntent().getExtras().getString("No of Items");
//
//        Log.i("OF", itemName + "   "+ noOfItems);
//
//        noOfitemsTextview.setText(noOfItems);
//        listOfitemsTextview.setText(itemName);
//    }
//
//    public void setText(){
//        TextView tv = (TextView) getActivity().findViewById(R.id.ordertitle);
//        tv.setText(tableNumStr);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

//
//        Bundle arg = getArguments();
//        Log.i("ARGUMENT", arg.toString() );
    }


//    public void setItemName(String itemName) {
//        this.itemName = itemName;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public void setNoOfItems(String noOfItems) {
//        this.noOfItems = noOfItems;
//    }

}

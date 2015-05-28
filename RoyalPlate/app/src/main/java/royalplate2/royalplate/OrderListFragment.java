package royalplate2.royalplate;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashSet;
import java.util.List;

import royalplate2.royalplate.adapter.MainMenuAdapter;
import royalplate2.royalplate.adapter.OrderedListAdapter;
import royalplate2.royalplate.adapter.SubMenuAdapter;
import royalplate2.royalplate.data.MainMenuData;
import royalplate2.royalplate.data.OrderedListData;

/**
 * Created by operamac on 4/29/15.
 */

public class OrderListFragment extends Fragment {

    private View v;
    private TextView tv;
    ImageView refreshImageview;
    ListView ordereditemslistview;
    OrderedListAdapter orderedListAdapter;
    SharedPreferences shared;

    String itemName;
    String noofItem;
    String itemcost;

    private String tableno;
    private String privous = null;
    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";
    public static final String ORDEREDLISTSHARED = "orderedlistSharedPreferences";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_orderlist, container, false);
          shared = PreferenceManager.getDefaultSharedPreferences(getActivity());

        ordereditemslistview = (ListView) v.findViewById(R.id.ordereditems_listview);

//        ordereditemslistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });


        int mode = Activity.MODE_PRIVATE;
        SharedPreferences assignedtablesSharedPreferences = getActivity().getSharedPreferences(ASSIGNEDTABLESHARED, mode);
        tableno = assignedtablesSharedPreferences.getString("tableNo", "");

        tv = (TextView) v.findViewById(R.id.tableNo_textview);
        tv.setText(tableno);



//        RetrieveSharedData();
//        storeDataOnParse(); // Store data values on Parse in OrderListParse

        /*******************************************************************************************
         * OrderedList gets loaded
         ******************************************************************************************/
        loadOrderedList();

        /*******************************************************************************************
         * Set Data values to Parse Class(OrderedListParse) through OrderListData java class
         ******************************************************************************************/


//
//        OrderedListData orderedListData = new OrderedListData();
//            orderedListData.setItemName(itemName);
//            orderedListData.setItemPrice(itemCost);
//            orderedListData.setTableNo(tableNumStr);
//            orderedListData.setNoOfItems(noOfItems);
//            orderedListData.saveInBackground();
//
//        Log.i("OF2", itemName + "  " + noOfItems + " " + itemCost);
//      //  shared.edit().clear().apply();



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

//
        /*******************************************************************************************
         * OrderBtn will confirm the orderlist and then send it Parse for Chef to load
         ******************************************************************************************/

        final Button orderBtn = (Button) v.findViewById(R.id.orderbutton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent orderBtnIntent = new Intent(v.getContext(), AssignedTableActivity.class);

                startActivity(orderBtnIntent);



            }
        });
        /*******************************************************************************************
         * Refresh Button will load again updated orderedlist from Parse
         ******************************************************************************************/

        refreshImageview = (ImageView) v.findViewById(R.id.orderedlist_refreshid);
        refreshImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrieveSharedData();
                storeDataOnParse();
                loadOrderedList();
            }


        });


        return v;
    }

    private void RetrieveSharedData() {


        SharedPreferences orderedlistSharedPreferences = getActivity().getSharedPreferences(ORDEREDLISTSHARED, Activity.MODE_PRIVATE);
       // orderedlistSharedPreferences = getActivity().getSharedPreferences(ORDEREDLISTSHARED, Activity.MODE_PRIVATE);

        //tableno = orderedlistSharedPreferences.getString("TableNo", "");
        noofItem = orderedlistSharedPreferences.getString("No of Items", "");
        itemName = orderedlistSharedPreferences.getString("Item Name", "");
        itemcost = orderedlistSharedPreferences.getString("Item Cost","");

        // clear the orderedlistSharedPreferenes.
       orderedlistSharedPreferences.edit().clear().apply();

    }

    /*******************************************************************************************
     * Set Data values to Parse Class(OrderedListParse) through OrderListData java class
     ******************************************************************************************/
    private void storeDataOnParse() {
        /*******************************************************************************************
         * Set Table No as a title in Ordered item list
         ******************************************************************************************/


        OrderedListData orderedListData = new OrderedListData();
        orderedListData.setTableNo( tableno);
        orderedListData.setNoOfItems(noofItem);
        orderedListData.setItemName(itemName);
        orderedListData.setItemPrice(itemcost);
        orderedListData.saveInBackground();

       // storeEachTable(tableno);
        Log.i("OF2",tableno+ " "+ itemName + "  " + noofItem + " " + itemcost);
      //  orderedlistSharedPreferences.edit().clear().apply();

    }

    private void storeEachTable(String tableno) {



     //   private void loadItems(String str) {
            //  final int itemCost = getIntent().getExtras().getInt("iniPrice");

//            final ParseQuery<ParseObject> items = ParseQuery.getQuery(tableno);
//            items.findInBackground(new FindCallback<ParseObject>() {
//                @Override
//                public void done(List<ParseObject> items, ParseException e) {
//                    menuAdapter = new SubMenuAdapter(SubMenuActivity.this, items,  tableno, SubMenuActivity.this );
//                    listview.setAdapter(menuAdapter);
//                }
//            });
       // }
    }

    /***********************************************************************************************
     * Load the ordered list from parse of that given Table No
     ***********************************************************************************************/

    private void loadOrderedList() {

        final ParseQuery<OrderedListData> orderedlist = ParseQuery.getQuery("OrderedListParse");
        orderedlist.whereEqualTo("TableNo",tableno);
        //orderedlist.whereGreaterThan("NoOfItems", Integer.toString(0));
//        orderedlist.whereGreaterThan("NoOfItems", 0);
//        orderedlist.whereEqualTo("TableNo",tableNumStr);


        orderedlist.findInBackground(new FindCallback<OrderedListData>() {
            @Override
            public void done(List<OrderedListData> orderedlist, ParseException e) {
                orderedListAdapter = new OrderedListAdapter(getActivity().getApplicationContext(), orderedlist, tableno);
                ordereditemslistview.setAdapter(orderedListAdapter);

            }
        });


       // orderedlistSharedPreferences.edit().clear().apply();

    }




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
        //  SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(getActivity());


//
//        Bundle arg = getArguments();
//        Log.i("ARGUMENT", arg.toString() );
    }

}
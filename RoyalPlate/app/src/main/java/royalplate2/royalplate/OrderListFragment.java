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
import com.parse.SaveCallback;

import java.util.HashSet;
import java.util.List;

import royalplate2.royalplate.adapter.MainMenuAdapter;
import royalplate2.royalplate.adapter.OrderedListAdapter;
import royalplate2.royalplate.adapter.SubMenuAdapter;
import royalplate2.royalplate.data.ChefServingTablesData;
import royalplate2.royalplate.data.MainMenuData;
import royalplate2.royalplate.data.OrderedListData;
import royalplate2.royalplate.data.OrderedListLogsData;

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


        int mode = Activity.MODE_PRIVATE;
        SharedPreferences assignedtablesSharedPreferences = getActivity().getSharedPreferences(ASSIGNEDTABLESHARED, mode);
        tableno = assignedtablesSharedPreferences.getString("tableNo", "").substring(0,2);

        tv = (TextView) v.findViewById(R.id.tableNo_textview);
        tv.setText(tableno);



//        RetrieveSharedData();
//        storeDataOnParse(); // Store data values on Parse in OrderListParse

        /*******************************************************************************************
         * OrderedList gets loaded
         ******************************************************************************************/
        loadOrderedList();

        /*******************************************************************************************
         * OrderBtn will confirm the orderlist and then send it Parse for Chef to load
         ******************************************************************************************/

        final Button orderBtn = (Button) v.findViewById(R.id.orderbutton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent orderBtnIntent = new Intent(v.getContext(), AssignedTableActivity.class);
                startActivity(orderBtnIntent);


                ChefServingTablesData chefServingTablesData = new ChefServingTablesData();
                chefServingTablesData.setTable(tableno);
                chefServingTablesData.saveInBackground();

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

        noofItem = orderedlistSharedPreferences.getString("No of Items", "");
        itemName = orderedlistSharedPreferences.getString("Item Name", "");
        itemcost = orderedlistSharedPreferences.getString("Item Cost","");

        // clear the orderedlistSharedPreferenes.
       orderedlistSharedPreferences.edit().clear().apply();

    }

    /*******************************************************************************************
     * Set Data values to Parse Class(OrderedListParse and OrderedListLogsParse)
     * through OrderListData java class
     * If item already exist on database, then overwrite
     ******************************************************************************************/
    private void storeDataOnParse() {



        final OrderedListData orderedListData = new OrderedListData();
        orderedListData.setTableNo( tableno.substring(0,2));
        orderedListData.setNoOfItems(noofItem);
        orderedListData.setItemName(itemName);
        orderedListData.setItemPrice(itemcost);
        orderedListData.saveInBackground();

        final OrderedListLogsData orderedListLogsData = new OrderedListLogsData();
        orderedListLogsData.setTableNo( tableno.substring(0,2));
        orderedListLogsData.setNoOfItems(noofItem);
        orderedListLogsData.setItemName(itemName);
        orderedListLogsData.setItemPrice(itemcost);
        orderedListLogsData.saveInBackground();

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

        orderedlist.findInBackground(new FindCallback<OrderedListData>() {
            @Override
            public void done(List<OrderedListData> orderedlist, ParseException e) {
                orderedListAdapter = new OrderedListAdapter(getActivity().getApplicationContext(), orderedlist, tableno);
                ordereditemslistview.setAdapter(orderedListAdapter);

            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

}
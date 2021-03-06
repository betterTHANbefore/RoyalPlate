package royalplate2.royalplate;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.ChefSideOrderListAdapter;
import royalplate2.royalplate.data.OrderedListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hetu on 4/10/15.
 */

public class ChefActivity extends ActionBarActivity  {

    ListView listview;
    private Button hostessButton;
    private Button waiterButton;
    private Button chefButton;
    private Button managerButton;
    Intent intent;
    Button doneBtn;
    String tableno;
    final ArrayList<String> tableNumsToDestroy = new ArrayList<String>();

    ChefSideOrderListAdapter chefSideOrderListAdapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_activity);
        listview = (ListView) findViewById(R.id.itemlist);

        // this contains OrderListFragment class
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentContainer_chef, new ChefQueueFragment()).commit();

        doneBtn = (Button) findViewById(R.id.doneButton);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
            tableno = sharedPref.getString("chefTableClicked", "");
            String tableToDestroy = tableno;

            tableNumsToDestroy.add(tableToDestroy);
            // table destroy needs to be done in ChefQueueFragment
            ChefQueueFragment fragment = (ChefQueueFragment) getFragmentManager().findFragmentById(R.id.fragmentContainer_chef);
            fragment.updateTableInQueue(tableNumsToDestroy);

            clearItemList();

            // Below code deletes from parse -> we don't want it happen!
                final ParseQuery query =  new ParseQuery("ChefServingTablesParse");
                query.whereEqualTo("TableNo", tableToDestroy);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> tableData, ParseException e) {
                        if(e == null || tableData.size() >0){
                            for (int i=0; i<tableData.size(); i++){

                                ParseObject tableNo = tableData.get(i);
                                try {
                                    tableNo.delete();
                                } catch (ParseException e1) {
                                }
                            }
                        }
                        else{

                        }
                    }
                });

                /**********************************************************************************
                 * Deletes the table entry from the parse after payment.
                 **********************************************************************************/

                final ParseQuery query2 =  new ParseQuery("OrderedListParse");
                query2.whereEqualTo("TableNo", tableno);
                query2.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> tableData, ParseException e) {
                        if(e == null || tableData.size() >0){
                            for (int i=0; i<tableData.size(); i++){

                                ParseObject tableNo = tableData.get(i);
                                try {
                                    tableNo.delete();
                                } catch (ParseException e1) {
                                }
                            }
                        }
                        else{

                        }
                    }
                });

                // Above code deletes from parse -> we don't want it happen!
            }
        });


        ImageView previousBtn = (ImageView) findViewById(R.id.previousImageviewid);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });



        /*******************************************************************************************
         *  H = Hostess, W= Waiter, C= Chef nevigate  M = Manager buttons
         /******************************************************************************************/

        hostessButton = (Button) findViewById(R.id.h_hostessbtn);
        hostessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), HostessActivity.class);
                startActivity(intent);
            }
        });

        waiterButton = (Button) findViewById(R.id.w_waitersbtn);
        waiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(),  AssignedTableActivity.class);
                startActivity(intent);

            }
        });

//        chefButton = (Button) findViewById(R.id.c_chefbtn);
//        chefButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(getApplicationContext(), ChefActivity.class);
//                startActivity(intent);
//            }
//        });

        managerButton = (Button) findViewById(R.id.m_managerbtn);
        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(), ManagerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clearItemList(){
        listview.setAdapter(null);
    }

    public void updateTableInfo(String tableNum) {
        TextView tablenoTextview = (TextView) findViewById(R.id.chef_tablenoid);
        tablenoTextview.setText( tableNum);
        loadOrderedItems(tableNum);
    }

//    private void loadOrderedItems(String tableNum) {
//
//        final ParseQuery<ParseObject> orderedItems = ParseQuery.getQuery(tableNum);
//
//        orderedItems.findInBackground(new FindCallback<ParseObject>() {
//
//            @Override
//            public void done(List<ParseObject> orderedItems, ParseException e) {
//                chefSideOrderListAdapter = new ChefSideOrderListAdapter(ChefActivity.this,  orderedItems);
//                listview.setAdapter(chefSideOrderListAdapter);
//            }
//        });
//    }
    /*******************************************************************************************
     * Loads orderedlist items from the OrderedListParse by Table no
     * that CHEF needs to finish in queue.
     ******************************************************************************************/

    private void loadOrderedItems(String tableNum) {

        final ParseQuery<OrderedListData> orderedItems = ParseQuery.getQuery("OrderedListParse");
        orderedItems.whereEqualTo("TableNo",tableNum);

        orderedItems.findInBackground(new FindCallback<OrderedListData>() {

            @Override
            public void done(List<OrderedListData> orderedItems, ParseException e) {
                chefSideOrderListAdapter = new ChefSideOrderListAdapter(ChefActivity.this,  orderedItems);
                listview.setAdapter(chefSideOrderListAdapter);


            }
        });
    }



}
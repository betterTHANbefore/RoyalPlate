package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

import royalplate2.royalplate.adapter.AccountBillAdapter;
import royalplate2.royalplate.data.GuestBillData;
import royalplate2.royalplate.data.OrderedListData;

/**
 * Created by hetu on 4/20/15.
 */

public class AccountActivity extends Activity {

    public static final String GUESTINFOSHARED = "guestInfoSharedPreferences";
    public static final String SUBTOTALSHARED = "subtotalSharedPreferences";
    SharedPreferences subtotalSharedPreferences;

    String tableno;
    String guestname;
    String noofguest;
    String waitername;
    String date;
    String time;
    float total;

    TextView guestNameTextview;
    TextView noOfGuestTextview;
    TextView tablenoTextview;

    TextView waiternameTextview;
    TextView dateTextview;
    TextView timeTextview;

    TextView subtotalTextview;
    TextView taxTextView;
    TextView grandtotalTextView;

    ImageView previousImageview;

    Button donebutton;
    Button okbutton;
    AccountBillAdapter accountBillAdapter;
    ListView ordereditemslistview;
    GuestBillData guestdata;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        ordereditemslistview = (ListView) findViewById(R.id.bill_listviewid);

        /*******************************************************************************************
         * Retrieve GuestInfo from guestInfoSharedPreferences SharedPreferences.
         ******************************************************************************************/

        int mode = Activity.MODE_PRIVATE;
        final SharedPreferences guestInfoSharedPreferences = getSharedPreferences(GUESTINFOSHARED, mode);

        guestname = guestInfoSharedPreferences.getString("guestName", "");
        noofguest = guestInfoSharedPreferences.getString("noOfGuest", "");
        waitername = guestInfoSharedPreferences.getString("userName", "");
        date = guestInfoSharedPreferences.getString("date", "");
        time = guestInfoSharedPreferences.getString("time", "");


        /*******************************************************************************************
         * Textviews to display Guest info in Billing statement
         ******************************************************************************************/

        guestNameTextview = (TextView) findViewById(R.id.bill_guestnameid);
        noOfGuestTextview = (TextView) findViewById(R.id.bill_noOfguestid);
        tablenoTextview = (TextView) findViewById(R.id.bill_tablenoid);
        waiternameTextview = (TextView) findViewById(R.id.bill_waiternameid);
        dateTextview = (TextView) findViewById(R.id.bill_dateid);
        timeTextview = (TextView) findViewById(R.id.bill_timeid);
        subtotalTextview = (TextView) findViewById(R.id.bill_subotalid);
        taxTextView = (TextView) findViewById(R.id.bill_taxid);
        grandtotalTextView = (TextView) findViewById(R.id.bill_grandtotalid);


        tableno = getIntent().getExtras().getString("tableNo","");

        final String waitername = getIntent().getExtras().getString("waiterName","");
        /*******************************************************************************************
         * Make a query to WaiterTable to display Guest info in the Billing statement
         ******************************************************************************************/

        final ParseQuery query = new ParseQuery("WaiterTable");
        query.whereEqualTo("TableNo", tableno);
        query.whereEqualTo("WaiterName", waitername);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> guestlist, ParseException e) {

                if(e ==null && guestlist.size()>0){

                    for(int i =0; i<guestlist.size(); i++){

                        ParseObject guestinfo = guestlist.get(i);
                        guestNameTextview.setText("Guest: " + guestinfo.getString("GuestName"));
                        noOfGuestTextview.setText("No: "+ guestinfo.getString("NoOfGuest"));
                        tablenoTextview.setText("Table: " + guestinfo.getString("TableNo").charAt(5));
                        waiternameTextview.setText("Waiter: " + guestinfo.getString("WaiterName"));
                        dateTextview.setText("Date: "+ guestinfo.getString("Date"));
                        timeTextview.setText("Time: " + guestinfo.get("Time"));

                    }
                }

            }
        });

        loadOrderedItemsList();

        displayPayment();

        guestdata = new GuestBillData();
        okbutton = (Button) findViewById(R.id.okbtnid);
        okbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final ParseQuery guestquery = new ParseQuery("GuestBillParse");
                guestquery.whereEqualTo("GuestName", guestname);
                guestquery.whereEqualTo("TableNo", tableno);
                guestquery.whereEqualTo("WaiterName", waitername);

                guestquery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> guestlist, ParseException e) {
                        if(e == null){
                            for(int i =0; i<guestlist.size(); i++) {
                                if(guestlist.get(i).equals(guestname)
                                        & guestlist.get(i).equals(tableno)
                                        & guestlist.get(i).equals(waitername)) {





                                }
                            }

                        }

                    }


                });

            }
        });


        /*******************************************************************************************
         *  Done button will delete the table entry from the database on Parse.
         *  Also send back to Waiter Assigned Table Activity.
         *******************************************************************************************/


        donebutton = (Button) findViewById(R.id.finisedbtnid);
        donebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(getApplicationContext(),AssignedTableActivity.class);
                startActivity(intent);

                // Below code deletes from parse -> we don't want it happen!
                String tableNumToDestroy = tableno;
                final ParseQuery query =  new ParseQuery("WaiterTable");
                query.whereEqualTo("TableNo", tableNumToDestroy);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> waiterData, ParseException e) {
                        if(e == null || waiterData.size() >0){
                            for (int i=0; i<waiterData.size(); i++){

                                ParseObject tableNo = waiterData.get(i);
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
            }
        });


        /*******************************************************************************************
         * Go back to AccountActivity.
         ******************************************************************************************/
        previousImageview = (ImageView) findViewById(R.id.previousImageviewid);
        previousImageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousIntent = new Intent(getApplicationContext(), AssignedTableActivity.class);
                startActivity(previousIntent);
            }
        });
    }
    /*******************************************************************************************
     * Display Subtotal, tax, and total in 2 decimal point format.
     *******************************************************************************************/

    private void displayPayment() {

        subtotalSharedPreferences = getSharedPreferences(SUBTOTALSHARED, Activity.MODE_PRIVATE);

        float  subtotal = subtotalSharedPreferences.getFloat("SubTotal",0);
        total = (float)(subtotal + subtotal* (.15));

        subtotalTextview.setText("SubTotal:  " +String.format("%.2f",subtotal));
        taxTextView.setText("Tax: " + "15%");
        grandtotalTextView.setText("Total: "+ String.format("%.2f",total));

    }

    /***********************************************************************************************
     * This function loads the data from the parse, where the class is
     * called "OrderedListParse". It uses AccountBillAdapter. And Listview to
     * display the orderedlist iteams.
     **********************************************************************************************/

    private void loadOrderedItemsList() {
        final ParseQuery<OrderedListData> orderedItems = ParseQuery.getQuery("OrderedListParse");

        orderedItems.whereEqualTo("TableNo", tableno);

        orderedItems.findInBackground(new FindCallback<OrderedListData>() {

            @Override
            public void done(List<OrderedListData> orderedItems, ParseException e) {

                accountBillAdapter = new AccountBillAdapter(AccountActivity.this, orderedItems, AccountActivity.this);
                ordereditemslistview.setAdapter(accountBillAdapter);
            }

        });
    }

    /*******************************************************************************************
     * Contstructor
     *******************************************************************************************/

    public AccountActivity(){}

    /*******************************************************************************************
     * Retrieve Subtotal from AccountBillAdapter class. Used SharedPreferences to store subtotal.
     * to access in display method.
     *******************************************************************************************/
    public void saveSubtotal(float subtotal){

        subtotalSharedPreferences = getSharedPreferences(SUBTOTALSHARED, Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = subtotalSharedPreferences.edit();
        editor.putFloat("SubTotal", subtotal);
        editor.apply();

    }

}

package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import royalplate2.royalplate.data.GuestBillData;

/**
 * Created by hetu on 4/20/15.
 */


public class AccountActivity extends Activity {

    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";
    public static final String LOGINSHARED = "loginSharedPreferences";
    public static final String GUESTINFOSHARED = "guestInfoSharedPreferences";

    String tableno;
    String guestname;
    String noofguest;
    String waitername;
    String date;
    String time;

    View guestName;
    String noOfguest;

    TextView guestNameTextview;
    TextView noOfGuestTextview;
    TextView tablenoTextview;

    TextView waiternameTextview;
    TextView dateTextview;
    TextView timeTextview;

    Button finishedbutton;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);

        /*********************
         * Retrieve GuestInfo
         *********************/
        int mode = Activity.MODE_PRIVATE;
        SharedPreferences guestInfoSharedPreferences = getSharedPreferences(GUESTINFOSHARED, mode);

        guestname = guestInfoSharedPreferences.getString("guestName", "");
        noofguest = guestInfoSharedPreferences.getString("noOfGuest", "");
       // tableno = guestInfoSharedPreferences.getString("tableNo", "");
        waitername = guestInfoSharedPreferences.getString("userName", "");
        date = guestInfoSharedPreferences.getString("date", "");
        time = guestInfoSharedPreferences.getString("time", "");


        /***************************************
         * Display Guest info in Billing statement
         ***************************************/

        guestNameTextview = (TextView) findViewById(R.id.bill_guestnameid);
        noOfGuestTextview = (TextView) findViewById(R.id.bill_noOfguestid);
        tablenoTextview = (TextView) findViewById(R.id.bill_tablenoid);
        waiternameTextview = (TextView) findViewById(R.id.bill_waiternameid);
        dateTextview = (TextView) findViewById(R.id.bill_dateid);
        timeTextview = (TextView) findViewById(R.id.bill_timeid);

//        String tableno = getIntent().getExtras().getString("tableNo","");
        tableno = getIntent().getExtras().getString("tableNo","");
        String waitername = getIntent().getExtras().getString("waiterName","");
//        final ParseQuery query = new ParseQuery("GuestBillParse");
//        query.whereEqualTo("TableNo", tableno);
////        query.whereEqualTo("GuestName", guestname);
////        query.whereEqualTo("WaiterName", waitername);
//
//        query.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> guestlist, ParseException e) {
//
//                if(e ==null && guestlist.size()>0){
//
//                    for(int i =0; i<guestlist.size(); i++){
//
//                        ParseObject guestinfo = guestlist.get(i);
//
//                        Log.i("guest",guestname+" "+noofguest+" "+tableno+" "+waitername+" "+date+" "+time);
//
//                        guestNameTextview.setText("Guest: " + "fasdas");
//
//
//
//
//
//                    }
//                }
//
//
//
//            }
//
//
//        });
//



        final ParseQuery query = new ParseQuery("WaiterTable");
        query.whereEqualTo("TableNo", tableno);
       // query.whereEqualTo("GuestName", guestname);
        query.whereEqualTo("WaiterName", waitername);

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> guestlist, ParseException e) {

                if(e ==null && guestlist.size()>0){

                    for(int i =0; i<guestlist.size(); i++){

                        ParseObject guestinfo = guestlist.get(i);

                     //   Log.i("guest",guestname+" "+noofguest+" "+tableno+" "+waitername+" "+date+" "+time);

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

        finishedbutton = (Button) findViewById(R.id.finisedbtnid);
        finishedbutton.setOnClickListener(new View.OnClickListener() {
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
                // Above code deletes from parse -> we don't want it happen!


            }
        });

    }

        /* Make a query from GuestBillParse class from Parse through GuestBillData */


    //  guestNameTextview.setText("Guest: " + guestname);


    //noOfGuestTextview.setText("No:  "+ noofguest);

    //  tablenoTextview.setText("Table:  " +          tableno.charAt(5));


//        SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
//        username = loginSharedPreferences.getString("userName", "");

    // waiternameTextview.setText("Waiter: " + username.toUpperCase());

    // dateTextview.setText("Date: "+ date);

//        timeTextview.setText("Time: " + time);

//        SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
//        tableno = assignedtablesSharedPreferences.getString("tableNo", "");


    //DELETE GUESTINFOSHARED here




}
package royalplate2.royalplate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.TableAdapter;
import royalplate2.royalplate.adapter.WaiterAdapter;
import royalplate2.royalplate.data.GuestBillData;
import royalplate2.royalplate.data.TableItemData1;
import royalplate2.royalplate.data.TableItemData2;
import royalplate2.royalplate.data.TablesData;
import royalplate2.royalplate.data.WaiterData;
import royalplate2.royalplate.data.WaiterTableData;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import static android.view.View.OnClickListener;

/**
 * Created by hetu on 4/12/15.
 */
public class HostessActivity extends Activity implements OnClickListener{


    GridView tablelistview;
    ListView waiterlistview;

    TableAdapter tableAdapter;
    WaiterAdapter waiterAdapter;
    Button assignedButton;
    SharedPreferences sharedtable;
    SharedPreferences sharedwaiter;

    Map<String, Set<String>> waitertables;

    TextView displayGuestName;
    TextView displayNoofGuest;
    TextView displayTableNo;
    TextView displayWaiterName;
    EditText guestNameedit;
    EditText gutestNoedit;
    String getguestname;
    String getnoOfguest;

    private String tableno;
    private String waitername;
    String guestname;
    String noofguest;

    WaiterTableData waitertable;
    GuestBillData guestBillData;

    public static final String GUESTINFOSHARED = "guestInfoSharedPreferences";
    public static final String LOGINSHARED = "loginSharedPreferences";
    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";
    DateFormat dateFormat;
    DateFormat timeFormat;
    Date date;
    Date time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostess_activity);

        /***********************************************************
         * To Stamp current date and time
         *********************************************************/
        date = new Date();
        time = new Date();

        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        timeFormat = new SimpleDateFormat("HH:mm:ss");

        sharedwaiter = PreferenceManager.getDefaultSharedPreferences(this);
        sharedtable = PreferenceManager.getDefaultSharedPreferences(this);


        loadTables();
        loadWaiters();

        // loaddata();

        /************************************************************
         * Tables GridView
         **********************************************************/
        tablelistview = (GridView) findViewById(R.id.tablelist_left);
        // tablelistview.setEnabled(false);

        /************************************************************
         * Waiters GridView
         **********************************************************/
        waiterlistview = (ListView) findViewById(R.id.waiterslist_right);
        //  waiterlistview.setEnabled(false);

        assignedButton = (Button) findViewById(R.id.assignedBtn);

        /************************************************
         * Disabling guestNoeditbox and AssignedBtn
         ***********************************************/
        //  gutestNoedit.setEnabled(false);
        assignedButton.setEnabled(false);
        assignedButton.setBackgroundColor(Color.DKGRAY);
        assignedButton.setPadding(80,0,80,0);



        // assignedButton.setBackgroundColor(Color.DKGRAY);
        /************************************************************
         * EditText to enter guest name and no of guest
         **********************************************************/
        guestNameedit = (EditText) findViewById(R.id.guestnameEdit);
        // guestname = guestNameedit.getText().toString();
        guestNameedit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length()==0) {
                    assignedButton.setEnabled(false);
                    assignedButton.setBackgroundColor(Color.DKGRAY);
                    assignedButton.setPadding(80, 0, 80, 0);

                    guestNameedit.setFocusable(true);
                    gutestNoedit.setEnabled(false);
                }
                else {
                    assignedButton.setEnabled(true);
                    assignedButton.setBackgroundResource(R.drawable.checkbox_background);
                    assignedButton.setPadding(80,0,80,0);
                    gutestNoedit.setEnabled(true);

                }

            }
        });
        assignedButton.setOnClickListener(this);

        gutestNoedit = (EditText) findViewById(R.id.guestnoEdit);
        //noofguest = gutestNoedit.getText().toString();


        /************************************************************
         *  Assigned Button will bring up the Popup Window with
         *  all guest info.
         **********************************************************/




        /************************************************************
         * ImageView leads to previous activity (SelectActivity)
         **********************************************************/
        ImageView goToPrevious = (ImageView) findViewById(R.id.previousImageview);

        goToPrevious.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent hostessIntent = new Intent(HostessActivity.this,SelectActivity.class);
                startActivity(hostessIntent);
            }
        });

    }

    /************************************************************
     * Load Waiters data from the Parse. Set n WaiterAdapter to view
     **********************************************************/

    private void loadWaiters(){

        final ParseQuery<WaiterData> waiters = ParseQuery.getQuery("WaiterParse");
        waiters.findInBackground(new FindCallback<WaiterData>() {

            @Override
            public void done(List<WaiterData> waiters, ParseException e) {
                waiterAdapter = new WaiterAdapter(getBaseContext(), waiters, HostessActivity.this);

                waiterlistview.setAdapter(waiterAdapter);

            }
        });
    }
    /************************************************************
     * Load Tables data from the Parse. Set a TableAdapter to view
     **********************************************************/
    private void loadTables() {

        final ParseQuery<TablesData> tables = ParseQuery.getQuery("TablesParse");
        tables.findInBackground(new FindCallback<TablesData>() {

            @Override
            public void done(List<TablesData> tables, ParseException e) {
                tableAdapter = new TableAdapter(getBaseContext(), tables, HostessActivity.this);
                tablelistview.setAdapter(tableAdapter);

            }

        });
    }

//        private void loaddata(){
//
//            final ParseQuery<ParseObject> table_hostess = ParseQuery.getQuery("TablesParse");
//            table_hostess.findInBackground(new FindCallback<ParseObject>() {
//
//                @Override
//                public void done(List<ParseObject> table_hostess, ParseException e) {
//                   hostesAdapter = new HostessAdapter(HostessActivity.this, table_hostess);
//                    tablelistview.setAdapter(hostesAdapter);
//
//
//                }
//            });
//            final ParseQuery<ParseObject> waiters = ParseQuery.getQuery("WaiterParse");
//            waiters.findInBackground(new FindCallback<ParseObject>() {
//
//                @Override
//                public void done(List<ParseObject> waiters, ParseException e) {
//                    hostesAdapter = new HostessAdapter(HostessActivity.this, waiters);
//                    waiterlistview.setAdapter(hostesAdapter);
//
//
//                }
//            });
//   }





    /************************************************************
     * Constructor
     **********************************************************/
    public HostessActivity(){

    }

    /************************************************************
     * Retrieve data values from the TableAdapter class
     **********************************************************/
    public void saveTableNumber(String tablelist){

        SharedPreferences.Editor tableeditor = sharedtable.edit();

        tableeditor.putString("TableNo", tablelist);

        tableeditor.apply();
    }

    /************************************************************
     * Retrieve data values from the WaiterAdapter class
     **********************************************************/

    public void saveWaiterName(String waitername){

        SharedPreferences.Editor waitereditor = sharedwaiter.edit();

        waitereditor.putString("WaiterName", waitername);

        waitereditor.apply();

    }

    /************************************************************
     * Assigned Button Listener
     **********************************************************/

    @Override
    public void onClick(View v) {


        displayGuestName = (TextView) findViewById(R.id.guestname_popup);
        displayNoofGuest = (TextView) findViewById(R.id.noofpeople_popup);
        displayTableNo = (TextView) findViewById(R.id.tableno_popup);
        displayWaiterName = (TextView) findViewById(R.id.waitername_popup);


        guestname = guestNameedit.getText().toString();
        noofguest = gutestNoedit.getText().toString();
        waitername = sharedwaiter.getString("WaiterName", null);
        tableno = sharedtable.getString("TableNo", null);


        /**************************************************************
         * Popup Window display new assigned guest information
         **************************************************************/

        View popupView = getLayoutInflater().inflate(R.layout.hostessconfirm_popup, null);
        final PopupWindow popupwindow = new PopupWindow(popupView, 330, 400, true);

        popupwindow.showAtLocation(v, Gravity.CENTER, 0, 0);
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setContentView(popupView);

        /*******************************************************
         * Display Guest Name and no of guest on popup Window
         *****************************************************/
        ((TextView) popupwindow.getContentView().findViewById(R.id.guestname_popup))
                .setText("Name:  " + guestname);
        ((TextView) popupwindow.getContentView().findViewById(R.id.noofpeople_popup))
                .setText("No:  " + noofguest);

//
        /*******************************************************
         * Display tableSet and waiterSet on popup Window
         *****************************************************/
        ((TextView) popupwindow.getContentView().findViewById(R.id.tableno_popup))
                .setText("Table No:  " + tableno);
        ((TextView) popupwindow.getContentView().findViewById(R.id.waitername_popup))
                .setText("Waiter:  " + waitername);


        /************************************************************
         * Confirm Button listener from the PopupWindow
         **********************************************************/
        Button confirmPopupButton = (Button) popupView.findViewById(R.id.confirmBtn_popup);

        confirmPopupButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*****************************************
                 * Store data on WaiterTable class on parse
                 *****************************************/
                waitertable = new WaiterTableData();
                waitertable.setWaiter(waitername);
                waitertable.setTable(tableno);
                waitertable.saveInBackground();

                guestBillData = new GuestBillData();
                guestBillData.setGuestName(guestname);
                guestBillData.setNoOfGuest(noofguest);
                guestBillData.setTableNo(tableno);
                guestBillData.setWaiterName(waitername);
                guestBillData.setDate(dateFormat.format(date));
                guestBillData.setTime(timeFormat.format(time));
                guestBillData.saveInBackground();


                // store Guest infor for billing and manager

//                 dateFormat.format(date);
//                 timeFormat.format(time);
                // storeGuestInfo(tableno);

                int mode = Activity.MODE_PRIVATE;

                SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
                waitername = loginSharedPreferences.getString("userName", "");

                SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
                tableno =  assignedtablesSharedPreferences.getString("tableNo", "");


                SharedPreferences guestInfoSharedPreferences = getSharedPreferences(GUESTINFOSHARED, mode);
                SharedPreferences.Editor editor = guestInfoSharedPreferences.edit();
                editor.putString("userName", waitername);
                editor.putString("guestName", guestname);
                editor.putString("tableNo", tableno);
                editor.putString("noOfGuest", noofguest);
                editor.putString("time", timeFormat.format(time));
                editor.putString("date",dateFormat.format(date));
                editor.apply();

//
//






                popupwindow.dismiss();

                guestNameedit.setText("");
                gutestNoedit.setText(" ");


                sharedwaiter.edit().clear().apply();
                sharedtable.edit().clear().apply();

            }


        });
        /************************************************************
         * Cancle Button listener from the PopupWindow
         **********************************************************/
        Button canclePopupButton = (Button) popupView.findViewById(R.id.cancleBtn_popup);

        canclePopupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(HostessActivity.this, "Guest entry cancled.", Toast.LENGTH_LONG).show();

                guestNameedit.setText("");
                gutestNoedit.setText(" ");

                popupwindow.dismiss();
                sharedwaiter.edit().clear().apply();
                sharedtable.edit().clear().apply();
            }
        });

        /************************************************************
         * set checkbox to unchecked in both the adapters
         ***********************************************************/
        CheckBox tablecheckBox = (CheckBox) findViewById(R.id.tableBtn);
        CheckBox waitercheckBox = (CheckBox) findViewById(R.id.waiterchkbox);
        tablecheckBox.setChecked(false);
        waitercheckBox.setChecked(false);
        tableAdapter.notifyDataSetChanged();
        waiterAdapter.notifyDataSetChanged();

    }

    //    private void storeGuestInfo() {
//
//        int mode = Activity.MODE_PRIVATE;
//
//        SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
//        waitername = loginSharedPreferences.getString("userName", "");
//
//        SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
//        tableno =  assignedtablesSharedPreferences.getString("tableNo", "");
//
//
//        SharedPreferences guestInfoSharedPreferences = getSharedPreferences(GUESTINFOSHARED, mode);
//        SharedPreferences.Editor editor = guestInfoSharedPreferences.edit();
//
//        editor.putString("guestName", guestname);
//        editor.putString("noOfGuest", noofguest);
//        editor.putString("tableNo", tableno);
//
//        editor.putString("userName", waitername);
//        editor.putString("date",dateFormat.format(date));
//        editor.putString("time", timeFormat.format(time));
//
//        editor.apply();
//
//    }
    private void storeGuestInfo(String tableno) {


//    if(tableno.equals("Table3")){
//
//    }
//    if(tableno.equals("Table4")){
//
//    }

//    int mode = Activity.MODE_PRIVATE;
//
//    SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
//    waitername = loginSharedPreferences.getString("userName", "");
//
//    SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
//    tableno =  assignedtablesSharedPreferences.getString("tableNo", "");
//
//
//    SharedPreferences guestInfoSharedPreferences = getSharedPreferences(GUESTINFOSHARED, mode);
//    SharedPreferences.Editor editor = guestInfoSharedPreferences.edit();
//
//    editor.putString("guestName", guestname);
//    editor.putString("noOfGuest", noofguest);
//    editor.putString("tableNo", tableno);
//
//    editor.putString("userName", waitername);
//    editor.putString("date",dateFormat.format(date));
//    editor.putString("time", timeFormat.format(time));
//
//    editor.apply();
//
//

    }

}
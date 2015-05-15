package royalplate2.royalplate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.SyncStateContract;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.TableAdapter;
import royalplate2.royalplate.adapter.WaiterAdapter;
import royalplate2.royalplate.data.TablesData;
import royalplate2.royalplate.data.WaiterData;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
    SharedPreferences.Editor waitereditor;
    SharedPreferences.Editor tableeditor;
    Map<String, Set<String>> waitertables;
    ParseObject waitertable;
    TextView displayGuestName;
    TextView displayNoofGuest;
    TextView displayTableNo;
    TextView displayWaiterName;
    EditText guestNameedit;
    EditText gutestNoedit;
    String getguestname;
    String getnoOfguest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostess_activity);
        // setContentView(R.layout.testing_waiter);

        loadTables();
        loadWaiters();

       // loaddata();

        /************************************************************
         * Tables GridView
         **********************************************************/
        tablelistview = (GridView) findViewById(R.id.tablelist_left);

        /************************************************************
         * Waiters GridView
         **********************************************************/
        waiterlistview = (ListView) findViewById(R.id.waiterslist_right);


        /************************************************************
         * EditText to enter guest name and no of guest
         **********************************************************/
        guestNameedit = (EditText) findViewById(R.id.guestnameEdit);
        gutestNoedit = (EditText) findViewById(R.id.guestnoEdit);

        /************************************************************
         *  Assigned Button will bring up the Popup Window with
         *  all guest info.
         **********************************************************/
        assignedButton = (Button) findViewById(R.id.assignedBtn);
        assignedButton.setOnClickListener(this);

    }

    /************************************************************
     * Load Waiters data from the Parse. Set n WaiterAdapter to view
     **********************************************************/

    private void loadWaiters(){
        //final  ParseQuery<WaiterData> waiters = ParseQuery.getQuery("User");
        final ParseQuery<WaiterData> waiters = ParseQuery.getQuery("WaiterParse");
        waiters.findInBackground(new FindCallback<WaiterData>() {

            @Override
            public void done(List<WaiterData> waiters, ParseException e) {
//                waiterAdapter = new WaiterAdapter(HostessActivity.this, waiters,getBaseContext());
                waiterAdapter = new WaiterAdapter(getBaseContext(), waiters,HostessActivity.this);

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
               //tableAdapter = new TableAdapter(HostessActivity.this, tables);

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
    public void saveTableNumber(HashSet<String> tablelist){

//       sharedtable = PreferenceManager.getDefaultSharedPreferences(this);
        sharedtable = PreferenceManager.getDefaultSharedPreferences(this);


//        SharedPreferences.Editor tableeditor = sharedtable.edit();
        tableeditor = sharedtable.edit();
        tableeditor.putStringSet("TableNo", tablelist);

        tableeditor.apply();
    }

    /************************************************************
     * Retrieve data values from the WaiterAdapter class
     **********************************************************/

    public void saveWaiterName(HashSet<String> waiternameset){

        sharedwaiter = PreferenceManager.getDefaultSharedPreferences(this);

//        SharedPreferences.Editor waitereditor = sharedwaiter.edit();
        waitereditor = sharedwaiter.edit();
        waitereditor.putStringSet("WaiterName", waiternameset);
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


        getguestname = guestNameedit.getText().toString();
        getnoOfguest = gutestNoedit.getText().toString();

        Log.i("Tag", "name and no  " + getguestname + " "+ getnoOfguest);


        waitertable = new ParseObject("WaiterTable");
        /**************************************************************
         * Initialize all the values. Unchecked all the checkboxes
         **************************************************************/


        Log.i("Tag", "HA:  " + sharedtable.getStringSet("TableNo", new HashSet<String>()));

        Log.i("Tag", "HA:  " + sharedwaiter.getStringSet("WaiterName", new HashSet<String>()));

        final Set<String> tableSet = sharedtable.getStringSet("TableNo", new HashSet<String>());
        final Set<String> waiterSet = sharedwaiter.getStringSet("WaiterName", new HashSet<String>());


        /**************************************************************
         * Popup Window display new assigned guest information
         **************************************************************/

        View popupView = getLayoutInflater().inflate(R.layout.hostessconfirm_popup,null);
        final PopupWindow popupwindow = new PopupWindow(popupView, 330, 400, true);

        popupwindow.showAtLocation(v, Gravity.CENTER, 0,0);
      //  popupwindow.setTouchable(true);
        popupwindow.setFocusable(true);
        popupwindow.setOutsideTouchable(true);
        popupwindow.setContentView(popupView);

        ((TextView)popupwindow.getContentView().findViewById(R.id.guestname_popup)).setText("Name:  "+getguestname);
        ((TextView)popupwindow.getContentView().findViewById(R.id.noofpeople_popup)).setText("No:  "+getnoOfguest);

//            popupwindow.setTouchInterceptor(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//
//
//                    if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//
//                        popupwindow.dismiss();
//
//                        return true;
//
//                    }
//
//                    return false;
//                }
//            });

        for(String table : tableSet) {
            Log.i("Tag", "TSet " + table);

            ((TextView)popupwindow.getContentView().findViewById(R.id.tableno_popup)).setText("Table No:  "+table);

        }
        for(String waiter : waiterSet) {
            Log.i("Tag", "WSet " + waiter);
            ((TextView)popupwindow.getContentView().findViewById(R.id.waitername_popup)).setText("Waiter:  "+ waiter);

        }


        /************************************************************
         * Confirm Button listener from the PopupWindow
         **********************************************************/
        Button confirmPopupButton = (Button) popupView.findViewById(R.id.confirmBtn_popup);

        confirmPopupButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Tag", "after confirm");
//
                for(String waiter : waiterSet){
                    for(String table : tableSet) {

                        waitertable.put("WaiterName", waiter);
                        waitertable.put("TableNo", table);

                     }
                }
                waitertable.saveInBackground();

//                String getguestname = displayGuestName.getText().toString();
//                String getnoofguest = displayNoofGuest.getText().toString();
//                String getwaitername = displayWaiterName.getText().toString();
//                String gettableno = displayTableNo.getText().toString();
//
//                waitertable.put("WaiterName", getwaitername);
//                waitertable.put("TableNo", gettableno);
//                waitertable.saveInBackground();

                guestNameedit.setText("");
                gutestNoedit.setText(" ");

                popupwindow.dismiss();

            }

        });


//
//        int waitersetSize = sharedwaiter.getStringSet("WaiterName", new HashSet<String>()).size();
//        int tablesetSize = sharedtable.getStringSet("TableNo", new HashSet<String>()).size();



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

}

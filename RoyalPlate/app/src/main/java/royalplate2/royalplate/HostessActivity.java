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
import royalplate2.royalplate.data.TablesData;
import royalplate2.royalplate.data.WaiterData;
import royalplate2.royalplate.data.WaiterTableData;

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
    //ParseObject waitertable;
    TextView displayGuestName;
    TextView displayNoofGuest;
    TextView displayTableNo;
    TextView displayWaiterName;
    EditText guestNameedit;
    EditText gutestNoedit;
    String getguestname;
    String getnoOfguest;

    String tableno;
    String waitername;
    WaiterTableData waitertable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hostess_activity);

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
      //  assignedButton.setOnClickListener(this);


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

    public void saveWaiterName(String waiternameset){

     SharedPreferences.Editor waitereditor = sharedwaiter.edit();

        waitereditor.putString("WaiterName", waiternameset);

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
        final String waitrename_shared = sharedwaiter.getString("WaiterName", null);
        final String tableno_shared = sharedtable.getString("TableNo", null);


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
                     .setText("Name:  " + getguestname);
             ((TextView) popupwindow.getContentView().findViewById(R.id.noofpeople_popup))
                     .setText("No:  " + getnoOfguest);

//
             /*******************************************************
              * Display tableSet and waiterSet on popup Window
              *****************************************************/
             ((TextView) popupwindow.getContentView().findViewById(R.id.tableno_popup))
                     .setText("Table No:  " + tableno_shared);
             ((TextView) popupwindow.getContentView().findViewById(R.id.waitername_popup))
                     .setText("Waiter:  " + waitrename_shared);


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
                 waitertable.setWaiter(waitrename_shared);
                 waitertable.setTable(tableno_shared);
                 waitertable.saveInBackground();

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


}

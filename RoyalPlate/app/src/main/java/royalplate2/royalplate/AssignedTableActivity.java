package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import royalplate2.royalplate.adapter.WaiterTableAdapter;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by operamac on 5/1/15.
 */
public class AssignedTableActivity extends Activity {

    String username;
    TextView usernameTextView;
    ImageButton refreshbutton;
    Button signoutbutton;
    GridView assignedtableGridview;
    WaiterTableAdapter waiterTableAdapter;
    Intent intent;
    public static final String LOGINSHARED = "loginSharedPreferences";

    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assigned_tables_activity);


        /***************************************************************
         * Waiter UserName appear into usernameTextview after login to account
         ***************************************************************/
      //  username = "cs";


        int mode = Activity.MODE_PRIVATE;
        SharedPreferences loginSharedPreferences = getSharedPreferences(LOGINSHARED, mode);
        username = loginSharedPreferences.getString("userName", "");


       // final SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(this);

//        username = shared.getString("userName", "");
     //   username = getIntent().getExtras().getString("userName");
        usernameTextView = (TextView) findViewById(R.id.waiternametextviewid);
        usernameTextView.setText(username);

        /***************************************************************
         * Load assignd tables
         ***************************************************************/
        loadtables();

        /************************************************************************
         * Each table listener to go MainMenu. Popup window opens. Waiter either
         * Order or Pay.
         ************************************************************************/
        assignedtableGridview = (GridView) findViewById(R.id.waitertable_gridview);
        assignedtableGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Button assignedtableBtn = (Button) parent.getChildAt(position).findViewById(R.id.mainmenu);

            String tableno = assignedtableBtn.getText().toString();

                int mode = Activity.MODE_PRIVATE;
                SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);


                //   SharedPreferences shared = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = assignedtablesSharedPreferences.edit();
                editor.putString("tableNo", tableno);
                editor.apply();

                View popupView = getLayoutInflater().inflate(R.layout.order_pay_popup, null);

                final PopupWindow popupwindow = new PopupWindow(popupView, 330, 300, true);
                    popupwindow.showAtLocation(view, Gravity.CENTER,0,0);
                    popupwindow.setFocusable(true);
                    popupwindow.setOutsideTouchable(true);
                    popupwindow.setContentView(popupView);


                    Button orderButton = (Button) popupView.findViewById(R.id.orderbuttonid);

                    orderButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                           intent = new Intent(AssignedTableActivity.this, MenuActivity.class);

                            // tablegridIntent.putExtra("tableNo", tableno);

                            startActivity(intent);

                        }
                    });

                    Button payButton = (Button) popupView.findViewById(R.id.paybuttonid);

                    payButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent = new Intent(AssignedTableActivity.this, AccountActivity.class);

                            // tablegridIntent.putExtra("tableNo", tableno);

                            startActivity(intent);

                        }
                    });




            }
        });

        /*********************************************************************
         * ImageView leads to previous activity (SelectActivity)
         *******************************************************************/
        ImageView goToPrevious = (ImageView) findViewById(R.id.previousImageview);

        goToPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AssignedTableActivity.this, SelectActivity.class);
                startActivity(intent);
            }
        });

        /*********************************************************************
         * Refresh (ImageButton) will refresh the current activity
         *******************************************************************/

        refreshbutton = (ImageButton) findViewById(R.id.refreshBtn);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 refreshActivity();

            }
        });

        /*********************************************************************
         * Sign Out button will not display waiter's name in the serving list in
         * Hostess Activity.(means waiter is done for the day or on break)
         *******************************************************************/
        signoutbutton = (Button) findViewById(R.id.signoutBtn);
        signoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AssignedTableActivity.this, "You are signed out!",Toast.LENGTH_LONG).show();

                final ParseQuery query =  new ParseQuery("WaiterParse");
                query.whereEqualTo("WaiterName", username);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> waiterData, ParseException e) {
                        if(e == null || waiterData.size() >0){
                            for (int i=0; i<waiterData.size(); i++){

                                  ParseObject waitername = waiterData.get(i);
                                        try {
                                            waitername.delete();
                                        } catch (ParseException e1) {
                                            Log.e("TAG", e1.getMessage(), e1);
                                        }
                            }

                        }
                        else{
                            Log.d("Tag", "Error: "+ e.getMessage());
                        }

                    }
                });

            }
        });
    }

    /*********************************************************************
     * Refresh Button will relaod all the tables from the Parse
     *******************************************************************/
    private void refreshActivity() {
        loadtables();
    }


    /*********************************************************************
     *
     * USE ParseRelation where WaiterParse relates to WaiterTables
     * Find waitername from the WaiterParse, relate waitername in
     * WaiterTable class and laod all the tables.
     *
     *
     *              1:n (Relation)(WaiterParse->WaiterTable
     *
     *
     *  Query ParseObject "WaiterTable", load all tables for that user only
     *******************************************************************/
    private void loadtables() {

        final ParseQuery<WaiterTableData> waitertables = ParseQuery.getQuery("WaiterTable");

        waitertables.whereEqualTo("WaiterName", username);
        waitertables.findInBackground(new FindCallback<WaiterTableData>() {

            @Override
            public void done(List<WaiterTableData> waitertables, ParseException e) {

                waiterTableAdapter = new WaiterTableAdapter(AssignedTableActivity.this, waitertables, AssignedTableActivity.this);
                assignedtableGridview.setAdapter(waiterTableAdapter);

            }

        });

    }

    /***********************************************************
     * Constructor
     * ********************************************************
     */
    public AssignedTableActivity() {

    }

    /**
     * *********************************************************
     * Retrieve data values from the TableAdapter class
     * ********************************************************
     */
//    public void saveTableNumber(String tablelist){
//
//
////       sharedtable = PreferenceManager.getDefaultSharedPreferences(this);
//        sharedtable = PreferenceManager.getDefaultSharedPreferences(this);
//
//
//        SharedPreferences.Editor tableeditor = sharedtable.edit();
//     //   tableeditor = sharedtable.edit();
//        tableeditor.putString("TableNo", tablelist);
//
//        tableeditor.apply();
//    }



}
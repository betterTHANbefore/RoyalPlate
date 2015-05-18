package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import royalplate2.royalplate.adapter.WaiterAdapter;
import royalplate2.royalplate.adapter.WaiterTableAdapter;
import royalplate2.royalplate.data.WaiterData;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by operamac on 5/1/15.
 */
public class AssignedTableActivity extends Activity {
    // temporaly, 3 table buttons statically.
    //  private Button[] tableButtons = new Button[3];
//    private Button tb;

    //    private ListView lv;
    String username;
    TextView usernameTextView;
    ImageButton refreshbutton;
    Button signoutbutton;
    GridView assignedtableGridview;
    WaiterTableAdapter waiterTableAdapter;
    Intent intent;
    SharedPreferences sharedtable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.assigned_table_activity);
        setContentView(R.layout.assigned_tables_activity);


        /***************************************************************
         * Waiter UserName appear into usernameTextview after login to account
         ***************************************************************/
        username = getIntent().getExtras().getString("userName");
        usernameTextView = (TextView) findViewById(R.id.waitername_textview);
        usernameTextView.setText(username);

        Log.i("Tag", "username  " + username);

        /***************************************************************
         * Load assignd tables
         ***************************************************************/
        loadtables();

        /************************************************************************
         * Each table listener to go MainMenu. Passing tableno through Intent
         ************************************************************************/
        assignedtableGridview = (GridView) findViewById(R.id.waitertable_gridview);
        assignedtableGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent tablegridIntent = new Intent(AssignedTableActivity.this, MenuActivity.class);

                Button assignedtableBtn = (Button) parent.getChildAt(position).findViewById(R.id.mainmenu);

                String tableno = assignedtableBtn.getText().toString();
                tablegridIntent.putExtra("tableNo", tableno);
                startActivity(tablegridIntent);
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

                Log.i("tag", "signout clicked");

                Toast.makeText(AssignedTableActivity.this, "You are signed out!",Toast.LENGTH_LONG).show();


                final ParseQuery query =  new ParseQuery("WaiterParse");
                query.whereEqualTo("WaiterName", username);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> waiterData, ParseException e) {
                        if(e == null || waiterData.size() >0){
                            for (int i=0; i<waiterData.size(); i++){
                         Log.i("TAG", "Deleted  " + username + "   "+ waiterData.get(i));

                                    ParseObject waitername = waiterData.get(i);
                    //waitername.remove(username);
                                try {
                                    waitername.delete();
                                } catch (ParseException e1) {
                                    Log.e("TAG", e1.getMessage(), e1);
                                }
                                //waitername.deleteInBackground();
                                }

                        }
                        else{
                            Log.d("Tag", "Error: "+ e.getMessage());
                        }

                    }
                });



            }
        });







//        String tableNumStr = getText().toString().
        // FIX IT this may need to be grid or list rather than buttons
//        tableButtons[0] = (Button) findViewById(R.id.rock);
//        tableButtons[1] = (Button) findViewById(R.id.and);
//        tableButtons[2] = (Button) findViewById(R.id.roll);
//
//        tableButtons[0].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                intent.putExtra("tableNo", "1");
//                intent.putExtra("table no", "Table 1");
//                intent.putExtra("iniPrice" , 0);
//                intent.putExtra("iniNoOfItem", 0);
//                startActivity(intent);
//
//            }
//        });
//
//        tableButtons[1].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                intent.putExtra("tableNo", "2");
////                Log.i("HELLO","AHHHHHHHHHHHHHHH");
//                startActivity(intent);
//
//                intent.putExtra("table no", "Table 2");
//                startActivity(intent);
//            }
//        });
//
//        tableButtons[2].setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
//                intent.putExtra("tableNo", "3");
////                Log.i("HELLO","AHHHHHHHHHHHHHHH");
//                startActivity(intent);
//
//                intent.putExtra("table no", "Table 3");
//                startActivity(intent);
//            }
//        });


    }

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

       // waitertables.whereExists("WaiterName");
        waitertables.whereEqualTo("WaiterName", username);
     //  waitertables.include("WaiterParse");
        waitertables.findInBackground(new FindCallback<WaiterTableData>() {

            @Override
            public void done(List<WaiterTableData> waitertables, ParseException e) {


                waiterTableAdapter = new WaiterTableAdapter(AssignedTableActivity.this, waitertables, AssignedTableActivity.this);
                assignedtableGridview.setAdapter(waiterTableAdapter);

            }

        });


    }


    /**
     * *********************************************************
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
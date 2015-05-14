package royalplate2.royalplate;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import royalplate2.royalplate.adapter.WaiterAdapter;


public class WaiterActivity extends ActionBarActivity {
    WaiterAdapter waiterAdapter;
  ListView waiterlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_activity);
     //   loadWaitersses();







//
//        tableSpinner = (Spinner)findViewById(R.id.table_spinner);
//        ArrayAdapter<String> tableAdapter = new ArrayAdapter<String>(WaiterActivity.this,android.R.layout.simple_spinner_item,tables);
//
//        tableAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//        tableSpinner.setAdapter(tableAdapter);
//        // tableSpinner.setOnItemClickListener(this);
//
//
//        /*******************************************************
//         * Go to Menu button listener switchs to Menu Items UI
//         ******************************************************/
//        menuButton = (Button)findViewById(R.id.menuBtn);
//        menuButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent menuUI = new Intent(getApplicationContext(),Menu_Items_Activity.class );
////                startActivity(menuUI);
//            }
//        });
//


    }

//    private void loadWaitersses() {
//
//
//
//        // Check Waiter Adapter passing variable have been changed
//
//        final ParseQuery<WaiterData> waiter_hostess = ParseQuery.getQuery("User");
//        waiter_hostess.findInBackground(new FindCallback<WaiterData>() {
//
//            @Override
//            public void done(List<WaiterData> waiter_hostess, ParseException e) {
//                waiterAdapter = new WaiterAdapter(WaiterActivity.this, waiter_hostess);
//                waiterlistview.setAdapter(waiterAdapter);
//
//
//            }
//        });
//
//
//    }

//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_royalplate, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

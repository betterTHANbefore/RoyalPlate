package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by operamac on 5/1/15.
 */
public class AssignedTableActivity extends Activity {


    // temporaly, 3 table buttons statically.
    private Button[] tableButtons = new Button[3];
//    private Button tb;

//    private ListView lv;

    TextView usernameTextView;
    GridView assignedTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.assigned_table_activity);
        setContentView(R.layout.assigned_tables_activity);

        String username = getIntent().getExtras().getString("userName");


        usernameTextView = (TextView) findViewById(R.id.waitername_textview);

        usernameTextView.setText(username);

        /***********************************************************
         * match username with the parse object and load the tables
         **********************************************************/












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
}

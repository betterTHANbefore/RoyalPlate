package royalplate2.royalplate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by operamac on 5/1/15.
 */
public class OrderSucceedActivity extends Activity {

    private TextView tv;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_succeed_activity);

        tv = (TextView) findViewById(R.id.table_num_view);
        tv.setText("Table " + getIntent().getExtras().getString("tableNo"));

        lv = (ListView) findViewById(R.id.menulist_left);

//        lv.setAdapter(new ArrayAdapter<>());
    }
}

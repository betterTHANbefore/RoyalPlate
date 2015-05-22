package royalplate2.royalplate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by hetu on 4/20/15.
 */


public class AccountActivity extends Activity{

    public static final String ASSIGNEDTABLESHARED = "assignedtablesSharedPreferences";
    String tableno;

    TextView tablenoTextview;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_activity);


        int mode = Activity.MODE_PRIVATE;
        SharedPreferences assignedtablesSharedPreferences = getSharedPreferences(ASSIGNEDTABLESHARED, mode);
        tableno = assignedtablesSharedPreferences.getString("tableNo", "");

        tablenoTextview = (TextView) findViewById(R.id.tablenoid);
        tablenoTextview.setText(tableno);


    }

}

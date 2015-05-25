package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import royalplate2.royalplate.adapter.ManagerAdapter;
import royalplate2.royalplate.adapter.TableAdapter;
import royalplate2.royalplate.data.GuestBillData;
import royalplate2.royalplate.data.TablesData;

/**
 * Created by hetu on 4/12/15.
 */
public class ManagerActivity extends Activity{

    ImageView previousimageview;
    Button reservationlogButton;
    ListView reservationlistview;

    ManagerAdapter managerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity);

        reservationlistview = (ListView) findViewById(R.id.reservationloglistviewid);

        previousimageview = (ImageView) findViewById(R.id.previousImageviewid);
        previousimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent previousIntent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(previousIntent);
            }
        });

        reservationlogButton = (Button) findViewById(R.id.reservationlogbuttonid);
        reservationlogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadReservationLogs();
            }
        });

    }

    private void loadReservationLogs() {

        final ParseQuery<GuestBillData> guestdata = ParseQuery.getQuery("GuestBillParse");
        guestdata.findInBackground(new FindCallback<GuestBillData>() {
            @Override
            public void done(List<GuestBillData> guestBillDatas, ParseException e) {
                managerAdapter = new ManagerAdapter(getApplicationContext(), guestBillDatas);
                reservationlistview.setAdapter(managerAdapter);


            }
        });

    }

}

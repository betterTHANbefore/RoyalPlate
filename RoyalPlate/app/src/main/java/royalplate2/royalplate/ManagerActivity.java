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
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import royalplate2.royalplate.adapter.GuestLogsAdapter;
import royalplate2.royalplate.adapter.ManagerAdapter;
import royalplate2.royalplate.adapter.TableAdapter;
import royalplate2.royalplate.data.GuestBillData;
import royalplate2.royalplate.data.GuestLogsData;
import royalplate2.royalplate.data.TablesData;

/**
 * Created by hetu on 4/12/15.
 */
public class ManagerActivity extends Activity{

    ImageView previousimageview;
    Button reservationlogButton;
    Button guestlogsButton;
    ListView logslistview;

    ManagerAdapter managerAdapter;
    GuestLogsAdapter guestLogsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_activity);

        logslistview = (ListView) findViewById(R.id.reservationloglistviewid);

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

        guestlogsButton = (Button) findViewById(R.id.guestlogsbuttonid);
        guestlogsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadGuestLogs();
            }
        });

    }

    private void loadGuestLogs() {

        final ParseQuery<GuestLogsData> guestlogs = ParseQuery.getQuery("GuestLogsParse");
        guestlogs.findInBackground(new FindCallback<GuestLogsData>() {
            @Override
            public void done(List<GuestLogsData> guestLogsDatas, ParseException e) {
                guestLogsAdapter = new GuestLogsAdapter(getApplicationContext(), guestLogsDatas);
               logslistview.setAdapter(guestLogsAdapter);
            }
        });

    }

    private void loadReservationLogs() {

        final ParseQuery<GuestBillData> guestdata = ParseQuery.getQuery("GuestBillParse");
        guestdata.findInBackground(new FindCallback<GuestBillData>() {
            @Override
            public void done(List<GuestBillData> guestBillDatas, ParseException e) {
                managerAdapter = new ManagerAdapter(getApplicationContext(), guestBillDatas);
                logslistview.setAdapter(managerAdapter);


            }
        });

    }

}

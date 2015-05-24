package royalplate2.royalplate;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.ListView;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.ChefSideOrderListAdapter;
import java.util.List;

/**
 * Created by hetu on 4/10/15.
 */

public class ChefActivity extends ActionBarActivity  {

    ListView listview;
    Button doneBtn;

    ChefSideOrderListAdapter chefSideOrderListAdapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_activity);
        listview = (ListView) findViewById(R.id.itemlist);

        // this contains OrderListFragment class
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentContainer_chef, new ChefQueueFragment()).commit();


        doneBtn = (Button) findViewById(R.id.doneButton);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                final String tableNumToDestroy = sharedPref.getString("chefTableClicked", "");

                final ParseQuery query =  new ParseQuery("WaiterTable");
                query.whereEqualTo("TableNo", tableNumToDestroy);
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> waiterData, ParseException e) {
                        if(e == null || waiterData.size() >0){
                            for (int i=0; i<waiterData.size(); i++){

                                ParseObject tableNo = waiterData.get(i);
                                try {
                                    tableNo.delete();
                                } catch (ParseException e1) {
                                }
                            }
                        }
                        else{

                        }
                    }
                });
            }
        });

        ImageView previousBtn = (ImageView) findViewById(R.id.previousImageviewid);
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });

    }

    public void updateTableInfo(String tableNum) {
        loadOrderedItems(tableNum);
    }

    private void loadOrderedItems(String tableNum) {
        final ParseQuery<ParseObject> orderedItems = ParseQuery.getQuery(tableNum);
        orderedItems.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> orderedItems, ParseException e) {
                chefSideOrderListAdapter = new ChefSideOrderListAdapter(ChefActivity.this,  orderedItems);
                listview.setAdapter(chefSideOrderListAdapter);
            }
        });
    }
}
package royalplate2.royalplate;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import com.parse.FindCallback;
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

    ChefSideOrderListAdapter chefSideOrderListAdapter;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chef_activity);
        listview = (ListView) findViewById(R.id.itemlist);

        // this contains OrderListFragment class
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentContainer_chef, new TableListFragment()).commit();

    }

    public void updateTableInfo(String tableId) {
        loadOrderedItems(tableId);
    }

    private void loadOrderedItems(String str) {
        final ParseQuery<ParseObject> orderedItems = ParseQuery.getQuery(str);
        orderedItems.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> orderedItems, ParseException e) {
                chefSideOrderListAdapter = new ChefSideOrderListAdapter(ChefActivity.this,  orderedItems);
                listview.setAdapter(chefSideOrderListAdapter);
            }
        });
    }
}

package royalplate2.royalplate;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import royalplate2.royalplate.adapter.TableListAdapter;

/**
 * Created by sh on 5/17/15.
 */
public class ChefQueueFragment extends Fragment {

    View view;
    ListView listView;
    TableListAdapter tableListAdapter;

    ImageView refreshBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chef_table_queue, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);

        loadTables();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tableNumText = (TextView) parent.getChildAt(position).findViewById(R.id.tableNum);
                String tableNumStr = tableNumText.getText().toString();
                ((ChefActivity) getActivity()).updateTableInfo(tableNumStr);
//                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
////                SharedPreferences.Editor editor = sharedPreferences.edit();
////                editor.putString("chefTableClicked", tableNumStr);
//

                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("chefTableClicked", tableNumStr);
                editor.commit();
            }
        });

        /*******************************************************************************************
         * Refresh Button will reload all the tables
         *******************************************************************************************/
        refreshBtn = (ImageView) view.findViewById(R.id.refreshBtn);

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTables();
            }
        });
        return view;
    }
    /*******************************************************************************************
     *TABLES COMES IN QUEUE TO CHEF TO FINISH THE ORDER.
     *******************************************************************************************/

    public void updateTableInQueue(ArrayList<String> tableNumsToDestroy){
        loadTables(tableNumsToDestroy);
    }

    // overloading with string argument
    private void loadTables(final ArrayList<String> exceptThese){
        final ParseQuery<ParseObject> tables = ParseQuery.getQuery("WaiterTable");

        for (int i=0; i<exceptThese.size(); i++)
            Log.i(String.valueOf(i), exceptThese.get(i).toString());

        tables.whereNotContainedIn("TableNo", exceptThese);
        tables.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> tables, ParseException e) {
                tableListAdapter = new TableListAdapter(getActivity(), tables);
                listView.setAdapter(tableListAdapter);
            }
        });
    }
    /*******************************************************************************************
     * Load All Assigned tables from the WaiterTable class from Parse.
     *******************************************************************************************/

    private void loadTables() {
        final ParseQuery<ParseObject> tables = ParseQuery.getQuery("WaiterTable");
        tables.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> tables, ParseException e) {
                tableListAdapter = new TableListAdapter(getActivity(), tables);
                listView.setAdapter(tableListAdapter);
            }
        });
    }
}
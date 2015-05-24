package royalplate2.royalplate;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;
import royalplate2.royalplate.adapter.TableListAdapter;

/**
 * Created by sh on 5/17/15.
 */
public class ChefQueueFragment extends Fragment {

    View view;
    ListView listView;
    TableListAdapter tableListAdapter;
    Button refreshBtn;

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
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("chefTableClicked", tableNumStr);


                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("chefTableClicked", tableNumStr);
                editor.commit();
            }
        });

        refreshBtn = (Button) view.findViewById(R.id.refreshBtn);
        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTables();
            }
        });
        return view;
    }

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
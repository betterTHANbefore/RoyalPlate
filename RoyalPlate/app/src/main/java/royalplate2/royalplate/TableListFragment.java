package royalplate2.royalplate;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class TableListFragment extends Fragment {

    View view;
    ListView listView;
    TableListAdapter tableListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_chef_table_queue, container, false);
        listView = (ListView) view.findViewById(R.id.list_view);
        loadTables();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tableNumText  = (TextView) parent.getChildAt(position).findViewById(R.id.tableNum);
                String tableNumStr = tableNumText.getText().toString();
                /* TODO Table1 needs to be replaced later */
                ((ChefActivity)getActivity()).updateTableInfo("Table1");
            }
        });
        return view;
    }

    private void loadTables() {
        final ParseQuery<ParseObject> tables = ParseQuery.getQuery("WaiterTable");
//        final ParseQuery<ParseObject> tables = ParseQuery.getQuery("TablesParse");
//        Log.i("TableListFragment", "Working");
        tables.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> tables, ParseException e) {
                tableListAdapter = new TableListAdapter(getActivity(), tables);
                listView.setAdapter(tableListAdapter);
            }
        });
    }
}

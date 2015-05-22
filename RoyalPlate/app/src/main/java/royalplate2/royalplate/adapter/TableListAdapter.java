package royalplate2.royalplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by sh on 5/17/15.
 */

public class TableListAdapter extends ArrayAdapter<ParseObject> {
    Context context;
    List<ParseObject> tableList;
//    TextView textView;

    public TableListAdapter(Context context, List<ParseObject> objects) {
        super(context, R.layout.listview_simple_list, objects);
//        Log.i("Constructor", objects.toString());
        this.context = context;
        this.tableList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            Log.i("getView", inflater.toString());
            view = inflater.inflate(R.layout.listview_simple_list, parent, false);
        } else {

        }

        // R.id.mainmenu is really "table number", this confusion causes due to reuse of existing listview_table_button.xml
        TextView textView = (TextView) view.findViewById((R.id.tableNum));
//        Log.i("Table", "Table");
//        textView.setText(((ParseObject) (tableList.get(position))).getString("TableNo"));
//        textView.setText(((TablesData) (tableList.get(position))).getTable());
        textView.setText(((WaiterTableData) (tableList.get(position))).getTable());
//        Log.i("Table", "Table2");
        return view;
    }
}
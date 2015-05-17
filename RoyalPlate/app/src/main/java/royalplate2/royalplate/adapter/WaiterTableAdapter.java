package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.MenuData;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by hetu on 4/23/15.
 */

public class WaiterTableAdapter extends ArrayAdapter<WaiterTableData> {

    Context context;
    List<WaiterTableData> waitertables;

    //Context is the SubMenuActivity
    //objects is the list of items
    public WaiterTableAdapter(Context context, List<WaiterTableData> waitertables) {
        super(context, R.layout.listview_table_button, waitertables);
        this.context = context;
        this.waitertables = waitertables;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_table_button, parent, false);

        }
        Button tablebtn = (Button) view.findViewById(R.id.mainmenu);

        tablebtn.setText(((WaiterTableData) (waitertables.get(position))).getTable());

        return view;
    }
}

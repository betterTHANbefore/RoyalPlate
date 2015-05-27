package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

import royalplate2.royalplate.AssignedTableActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.WaiterTableData;

/**
 * Created by hetu on 4/23/15.
 */

public class WaiterTableAdapter extends ArrayAdapter<WaiterTableData> {

    Context context;
    Button assignedtableBtn;
    String guestname;

    List<WaiterTableData> waitertables;
    AssignedTableActivity assignedTableActivity;


    public WaiterTableAdapter(Context context, List<WaiterTableData> waitertables, AssignedTableActivity assignedTableActivity) {
        super(context, R.layout.listview_table_button, waitertables);
        this.context = context;
        this.waitertables = waitertables;
        this.assignedTableActivity = assignedTableActivity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_table_button, parent, false);

        }
        final Button assignedtableBtn = (Button) view.findViewById(R.id.mainmenu);

        String tableno = waitertables.get(position).getTable();

        final ParseQuery queryguestname = new ParseQuery("WaiterTable");
        queryguestname.whereEqualTo("TableNo", tableno);

        queryguestname.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if(e == null){
                   for(int i=0; i<list.size(); i++){
                       ParseObject ob = list.get(i);
                       String guestname = ob.getString("GuestName");
                       assignedtableBtn.setText((waitertables.get(position)).getTable() + "  ("+ guestname+ ") ");


                   }
                }

            }

        });

//        assignedtableBtn.setText((waitertables.get(position)).getTable());

        return view;
    }
}

package royalplate2.royalplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import royalplate2.royalplate.HostessActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.TablesData;

import java.util.List;


/**
 * Created by hetu on 5/2/15.
 */
public class TableAdapter extends ArrayAdapter<TablesData> {

    List<TablesData> tableslist;
    Context context;
    HostessActivity hostessActivity;
    String tablenolist = null;


//    public TableAdapter(Context context, List<TablesData> objects) {
//        super(context, R.layout.listview_tablescheckbox, objects);
//        this.context = context;
//        this.tableslist = objects;
//    }

    public TableAdapter(Context context, List<TablesData> objects, HostessActivity hostessActivity) {
        super(context, R.layout.listview_tablescheckbox, objects);
        this.context = context;
        this.tableslist = objects;
        this.hostessActivity = hostessActivity;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_tablescheckbox, parent, false);

       final CheckBox tablecheckbox = (CheckBox) view.findViewById((R.id.tableBtn));

        tablecheckbox.setText(tableslist.get(position).getTable());

        tablecheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final boolean isChecked = tablecheckbox.isChecked();

                // do somthing here
                if (isChecked) {

                    String tableno = tablecheckbox.getText().toString();

                    tablenolist= tableno;
                    hostessActivity.saveTableNumber(tablecheckbox.getText().toString());

                }
            }

        });

        return view;
    }


}

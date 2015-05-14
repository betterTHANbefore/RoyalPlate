package royalplate2.royalplate.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import royalplate2.royalplate.HostessActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.TablesData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by hetu on 5/2/15.
 */
public class TableAdapter extends ArrayAdapter<TablesData>{

    List<TablesData> tableslist;
    Context context;
    HostessActivity hostessActivity;
    Set<String> tablenolist;


//    public TableAdapter(Context context, List<TablesData> objects) {
//        super(context, R.layout.listview_tables, objects);
//        this.context = context;
//        this.tableslist = objects;
//    }

    public TableAdapter(Context context, List<TablesData> objects, HostessActivity hostessActivity) {
        super(context, R.layout.listview_tables, objects);
        this.context = context;
        this.tableslist = objects;
        this.hostessActivity = hostessActivity;
    }



    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent){


       //final ArrayList<String> tablenolist = new ArrayList<String>();

         tablenolist = new HashSet<String>();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_tables, parent, false);

//        final CheckBox tableButton = (CheckBox) parent.getChildAt(position).findViewById(R.id.tableBtn);
       final CheckBox tableButton = (CheckBox) view.findViewById((R.id.tableBtn));
        tableButton.setText(tableslist.get(position).getTable().toString());

        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("Tag", "clicked");

                final boolean isChecked = tableButton.isChecked();
                // do somthing here
                if(isChecked) {

                    String tableno = tableButton.getText().toString();
                    tablenolist.add(tableno);

                  //  Log.i("Tag", "TableAdapter: table no  " + tableno);


                    Log.i("Tag", " set  " + tablenolist);


                }
                hostessActivity.saveTableNumber((HashSet<String>) tablenolist);


            }
        });

      //  hostessActivity.saveTableNumber((HashSet<String>) tablenolist);

      return view;
    }


}

package royalplate2.royalplate.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import royalplate2.royalplate.HostessActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.TablesData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by hetu on 5/2/15.
 */
public class TableAdapter extends ArrayAdapter<TablesData> {

    List<TablesData> tableslist;
    Context context;
    HostessActivity hostessActivity;
    Set<String> tablenolist;
  //  String tableno = null;

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



         tablenolist = new HashSet<String>();


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_tables, parent, false);

//        final CheckBox tableButton = (CheckBox) parent.getChildAt(position).findViewById(R.id.tableBtn);
       final CheckBox tablecheckbox = (CheckBox) view.findViewById((R.id.tableBtn));
//        final Button assigntablebtn = (Button) view.findViewById(R.id.assigntableBtn);
//        assigntablebtn.setOnClickListener(this);

        tablecheckbox.setText(tableslist.get(position).getTable());

      //  final boolean isChecked = tablecheckbox.isChecked();
        //String tableno = tableButton.getText().toString();
//
//        assigntablebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////
//                tablenolist.add(tableNostring);
//                hostessActivity.saveTableNumber((HashSet<String>) tablenolist);
//                tablecheckbox.setChecked(false);
//
//
//            }
//        });





        tablecheckbox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final boolean isChecked = tablecheckbox.isChecked();

                // do somthing here
                if (isChecked) {
                    String tableno = tablecheckbox.getText().toString();

                    tablenolist.add(tableno);

                 //   Log.i("Tag", " set  " + tablenolist);

                }

                hostessActivity.saveTableNumber((HashSet<String>) tablenolist);
            }

        });

        return view;
    }


}

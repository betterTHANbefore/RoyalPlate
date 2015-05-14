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
    String tableno = null;

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

        tablecheckbox.setText(tableslist.get(position).getTable().toString());

        final boolean isChecked = tablecheckbox.isChecked();
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





//        tableButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
////                Log.i("Tag", "clicked");
//
//                final boolean isChecked = tableButton.isChecked();
//                String tableno = tableButton.getText().toString();
//                int index;
//
//                // do somthing here
//                if (isChecked) {
//
//                    tablenolist.add(tableno);
//                    //      index = tableButton.getId();
//
//                    //  Log.i("Tag", "TableAdapter: table no  " + tableno);
//
//                    tableButton.toggle();
//                    Log.i("Tag", " set  " + tablenolist);
//
//
//                }
//
//
//            }
//        });
//        hostessActivity.saveTableNumber((HashSet<String>) tablenolist);

        tablecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

               //  String tableno = null;
                 // do somthing here
               if (isChecked) {

                  tableno = tablecheckbox.getText().toString();

                    tablenolist.add(tableno);
//                    //      index = tableButton.getId();
//
//                    //  Log.i("Tag", "TableAdapter: table no  " + tableno);
//
//                   // tableButton.toggle();
                    Log.i("Tag", " set  " + tablenolist);
//
                }

             }

            });

        hostessActivity.saveTableNumber((HashSet<String>) tablenolist);

        return view;
    }

//
//    @Override
//    public void onClick(View v) {
//        Log.i("Tag", "Assign Table Got Clicked ");
//        tablenolist.add(tableNostring);
//        hostessActivity.saveTableNumber((HashSet<String>) tablenolist);
//       // tablecheckbox.setChecked(false);
//
//
//    }
}

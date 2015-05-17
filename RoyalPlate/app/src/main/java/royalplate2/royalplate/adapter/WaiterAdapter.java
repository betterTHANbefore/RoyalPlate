package royalplate2.royalplate.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import royalplate2.royalplate.HostessActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.WaiterData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hetu on 5/2/15.
 */
public class WaiterAdapter extends ArrayAdapter<WaiterData> {
    List<WaiterData> waiterslist;
    ListView waiterListview;
    Context context;
    HostessActivity hostessActivity;
   // Set<String> waiternameSet;
    String waiternameSet;
    String waitername;
    

    public WaiterAdapter(Context context, List<WaiterData> objects,HostessActivity hostessActivity) {
        super(context, R.layout.listview_waiter, objects);
        this.context = context;
        this.waiterslist = objects;
        this.hostessActivity = hostessActivity;
    }

//    public WatierAdapter(Context context, List<WaiterData> objects) {
//         super(context, R.layout.listview_waiter, objects);
    //    this.context = context;
//    this.waiterslist = objects;
//    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){


      //  final ArrayList<String> waitername = new ArrayList<String>();

     // waiternameSet = new HashSet<String>();

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.listview_waiter, parent, false);
            waiterListview = (ListView) view.findViewById(R.id.waiterslist);

            final CheckBox waitercheckbox = (CheckBox) view.findViewById((R.id.waiterchkbox));
           waitercheckbox.setText(waiterslist.get(position).getWaiter());
//        waitercheckbox.setText(waiterslist.get(position).getUser());


        waitercheckbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final boolean isChecked = waitercheckbox.isChecked();
                    if(isChecked) {

                      String waiter = waitercheckbox.getText().toString();
                     //   waiternameSet.add(waitername);
                        waitername = waiter;


                    }
                    hostessActivity.saveWaiterName(waitername);

                    //    hostessActivity.saveWaiterName((HashSet<String>) waiternameSet);

                }
            });

        return view;
    }

}


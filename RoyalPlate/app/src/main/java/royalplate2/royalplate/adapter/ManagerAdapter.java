package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.GuestBillData;

/**
 * Created by hetu on 4/23/15.
 */
public class ManagerAdapter extends ArrayAdapter<GuestBillData> {

    Context context;
    List<GuestBillData> guestdata;

    public ManagerAdapter(Context context, List<GuestBillData> objects) {
        super(context, R.layout.listview_manager, objects);
        this.context = context;
        this.guestdata = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_manager, parent, false);

        TextView dateTextView;
        TextView timeTextView;
        TextView waiterTextView;
        TextView tableTextView;
        TextView paymentTextView;

        dateTextView = (TextView) view.findViewById(R.id.manager_dateid);
        dateTextView.setText(guestdata.get(position).getDate());

        timeTextView = (TextView) view.findViewById(R.id.manager_timeid);
        timeTextView.setText(guestdata.get(position).getTime());

        waiterTextView= (TextView) view.findViewById(R.id.manager_waiterid);
        waiterTextView.setText(guestdata.get(position).getWaiterName());

        tableTextView = (TextView) view.findViewById(R.id.manager_tablenoid);
        tableTextView.setText(guestdata.get(position).getTableNo());

        paymentTextView = (TextView) view.findViewById(R.id.manager_totalid);
        paymentTextView.setText(guestdata.get(position).getPayment());



        return view;
    }
}

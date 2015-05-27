package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.GuestLogsData;

/**
 * Created by hetu on 4/23/15.
 */
public class GuestLogsAdapter extends ArrayAdapter<GuestLogsData> {

    Context context;
    List<GuestLogsData> guestlogsdata;

    public GuestLogsAdapter(Context context, List<GuestLogsData> objects) {
        super(context, R.layout.listview_guestlogs, objects);
        this.context = context;
        this.guestlogsdata = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_guestlogs, parent, false);

        TextView dateTextView;
        TextView timeTextView;
        TextView waiterTextView;
        TextView tableTextView;
        TextView guestnameTextView;
        TextView noofguestTextView;
        TextView paymentTextView;

        dateTextView = (TextView) view.findViewById(R.id.guest_dateid);
        dateTextView.setText(guestlogsdata.get(position).getDate());

        timeTextView = (TextView) view.findViewById(R.id.guest_timeid);
        timeTextView.setText(guestlogsdata.get(position).getTime());

        waiterTextView= (TextView) view.findViewById(R.id.guest_waiterid);
        waiterTextView.setText(guestlogsdata.get(position).getWaiterName());

        tableTextView = (TextView) view.findViewById(R.id.guest_tablenoid);
        tableTextView.setText(guestlogsdata.get(position).getTableNo());

        guestnameTextView = (TextView) view.findViewById(R.id.guest_guestnameid);
        guestnameTextView.setText(guestlogsdata.get(position).getGuestName());

        noofguestTextView = (TextView) view.findViewById(R.id.guest_noguestid);
        noofguestTextView.setText(guestlogsdata.get(position).getNoGuest());

        paymentTextView = (TextView) view.findViewById(R.id.guest_totalid);
        paymentTextView.setText(guestlogsdata.get(position).getPayment());



        return view;
    }
}

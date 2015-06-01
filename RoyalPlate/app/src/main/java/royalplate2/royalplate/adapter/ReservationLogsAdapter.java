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
import royalplate2.royalplate.data.ReservationLogsData;

/**
 * Created by hetu on 4/23/15.
 */
public class ReservationLogsAdapter extends ArrayAdapter<ReservationLogsData> {

    Context context;
    List<ReservationLogsData> reservationlogsdata;

    public ReservationLogsAdapter(Context context, List<ReservationLogsData> objects) {
        super(context, R.layout.listview_reservationlogs, objects);
        this.context = context;
        this.reservationlogsdata = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_reservationlogs, parent, false);

        TextView dateTextView;
        TextView timeTextView;
        TextView waiterTextView;
        TextView tableTextView;
        TextView guestnameTextView;
        TextView noofguestTextView;

//        dateTextView = (TextView) view.findViewById(R.id.guest_dateid);
//        dateTextView.setText(reservationlogsdata.get(position).getDate());

        timeTextView = (TextView) view.findViewById(R.id.guest_timeid);
       timeTextView.setText(reservationlogsdata.get(position).getTime());

        waiterTextView= (TextView) view.findViewById(R.id.guest_waiterid);
        waiterTextView.setText(reservationlogsdata.get(position).getWaiterName().toUpperCase());

        tableTextView = (TextView) view.findViewById(R.id.guest_tablenoid);
        tableTextView.setText( reservationlogsdata.get(position).getTableNo());

        guestnameTextView = (TextView) view.findViewById(R.id.guest_guestnameid);
        guestnameTextView.setText(reservationlogsdata.get(position).getGuestName());

        noofguestTextView = (TextView) view.findViewById(R.id.guest_noguestid);
        noofguestTextView.setText("(" + reservationlogsdata.get(position).getNoGuest()+ ")");


        return view;
    }
}

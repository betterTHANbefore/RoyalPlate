package royalplate2.royalplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import com.parse.ParseObject;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.HostessData;

import java.util.List;

/**
 * Created by hetu on 4/29/15.
 */

public class HostessAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    Context context1;
    Context context2;
    List<ParseObject> hostessData;

    public HostessAdapter(Context context, List<ParseObject> objects){
//        super(context, R.layout.listview_tables,objects);
        super(context, 0,objects);

        this.context = context;
        this.hostessData = objects;

    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent){

        View view = convertView;
        if(view == null) {


            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



            // 0 --> table no
            if(getItemViewType(position)== 0) {
               view = inflater.inflate(R.layout.listview_tables,null, false);


                final Button tableButton = (Button) view.findViewById((R.id.tableBtn));

                tableButton.setText(((HostessData) (hostessData.get(position))).getTable());
                final CheckBox  waitercheckbox = (CheckBox) view.findViewById(R.id.waiterchkbox);

                waitercheckbox.setText(((HostessData) (hostessData.get(position))).getWaiter());



            }

            // 1 --> waiter name on listview_waiter
            else{
                view = inflater.inflate(R.layout.listview_waiter, null, false);

                final CheckBox  waitercheckbox = (CheckBox) view.findViewById(R.id.waiterchkbox);

                waitercheckbox.setText(((HostessData) (hostessData.get(position))).getWaiter());


            }

        }
//
//        tableButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                try {
//
//                    LayoutInflater inflater = (LayoutInflater) context
//                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//                    View view = inflater.inflate(R.layout.listview_waiter, parent, false);
//
//
//                    CheckBox waitercheckbox = (CheckBox) view.findViewById(R.id.waiterchkbox);
//
//                    waitercheckbox.setText(((HostessData) (hostessData.get(position))).getWaiter());
//
//                    popup = new PopupWindow(view, 300, 370, true);
//                    popup.showAtLocation(view, Gravity.CENTER, 0, 0);
//                    popup.setBackgroundDrawable(new BitmapDrawable());
//                    popup.setOutsideTouchable(true);
//                    popup.setTouchable(true);
//                    popup.setFocusable(true);
//                    //the pop-up will be dismissed if touch event occurs anywhere outside its window
//                    popup.setTouchInterceptor(new View.OnTouchListener() {
//                        public boolean onTouch(View v, MotionEvent event) {
//                            if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                                popup.dismiss();
//                                return true;
//                            }
//                            return false;
//                        }
//                    });
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//
//        });
        return view;
    }


}


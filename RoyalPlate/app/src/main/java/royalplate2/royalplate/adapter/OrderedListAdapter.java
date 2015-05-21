package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.OrderedListData;

/**
 * Created by hetu on 4/23/15.
 */
public class OrderedListAdapter extends ArrayAdapter<OrderedListData> {

    Context context;
    List<OrderedListData> orderedItemsList;
    TextView noofItemTextview;
    TextView itemnameTextview;
    TextView itempriceTextview;
    Button cacleitembutton;
    // Context is the SubMenuActivity
    // objects is the list of items

    public OrderedListAdapter(Context context, List<OrderedListData> ordereditemsList) {
        super(context, R.layout.listview_ordereditems, ordereditemsList);
        this.context = context;
        this.orderedItemsList = ordereditemsList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_ordereditems, parent, false);

        noofItemTextview = (TextView) view.findViewById(R.id.orded_noofitemsid);
        noofItemTextview.setText(orderedItemsList.get(position).getNoOfItems());

        itemnameTextview = (TextView) view.findViewById(R.id.orded_itemNameid);
        itemnameTextview.setText(orderedItemsList.get(position).getItemName());

        itempriceTextview = (TextView) view.findViewById(R.id.orded_itemPriceid);
        itempriceTextview.setText(orderedItemsList.get(position).getItemPrice());

        cacleitembutton = (Button) view.findViewById(R.id.orded_cancleBtnid);
        //cacleitembutton.setText(view.findViewById());

        return view;
    }
}

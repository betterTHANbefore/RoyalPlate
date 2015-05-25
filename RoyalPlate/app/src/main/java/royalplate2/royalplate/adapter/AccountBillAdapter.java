package royalplate2.royalplate.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

import java.text.ParseException;
import java.util.List;

import royalplate2.royalplate.AccountActivity;
import royalplate2.royalplate.R;
import royalplate2.royalplate.data.OrderedListData;

/**
 * Created by hetu on 4/23/15.
 */

public class AccountBillAdapter extends ArrayAdapter<OrderedListData> {

    Context context;
    List<OrderedListData> orderedItems;
    AccountActivity accountActivity;
    float subtotal = 0;

    public AccountBillAdapter(Context context, List<OrderedListData> objects, AccountActivity accountActivity) {
        super(context, R.layout.listview_account, objects);
        this.context = context;
        this.orderedItems = objects;
        this.accountActivity = accountActivity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_account, parent, false);
        }



        final TextView noofitemsTextView = (TextView) view.findViewById((R.id.noofitemsid));
        noofitemsTextView.setText(orderedItems.get(position).getNoOfItems().toString());

        TextView itemnamesTextView = (TextView) view.findViewById((R.id.itemnameid));
        itemnamesTextView.setText(orderedItems.get(position).getItemName());

        TextView itempriceTextView = (TextView) view.findViewById((R.id.itempriceid));
        itempriceTextView.setText(orderedItems.get(position).getItemPrice());

        subtotal += Float.parseFloat(orderedItems.get(position).getItemPrice());

        accountActivity.saveSubtotal(subtotal);


        return view;
    }
}



package royalplate2.royalplate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.OrderedListData;
import royalplate2.royalplate.data.TableItemData;

/**
 * Created by sh on 5/17/15.
 */
public class ChefSideOrderListAdapter extends ArrayAdapter<OrderedListData> {

    Context context;
    List<OrderedListData> orderedList;

    public ChefSideOrderListAdapter(Context context, List<OrderedListData> objects) {
        super( context, R.layout.listview_chef_item, objects);
        this.context = context;
        this.orderedList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_chef_item, parent, false);
        }
        TextView itemnametextView = (TextView) view.findViewById((R.id.itemNameid)); // itemName
        itemnametextView.setText(orderedList.get(position).getItemName());

        TextView noofitemTextview = (TextView) view.findViewById(R.id.noOfItemsid);
        noofitemTextview.setText(orderedList.get(position).getNoOfItems());

        return view;
    }
}



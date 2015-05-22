
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
import royalplate2.royalplate.data.TableItemData;

/**
 * Created by sh on 5/17/15.
 */
public class ChefSideOrderListAdapter extends ArrayAdapter<ParseObject> {

    Context context;
    List<ParseObject> orderedList;

    public ChefSideOrderListAdapter(Context context, List<ParseObject> objects) {
        super( context, R.layout.listview_item, objects);
        this.context = context;
        this.orderedList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.listview_item, parent, false);
        }
        TextView textView = (TextView) view.findViewById((R.id.itemNameid)); // itemName
        textView.setText(((TableItemData) (orderedList.get(position))).getName());

        return view;
    }
}



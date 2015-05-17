package royalplate2.royalplate.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import royalplate2.royalplate.R;
import royalplate2.royalplate.data.MainMenuData;

import java.util.List;

/**
 * Created by hetu on 4/23/15.
 */
public class MainMenuAdapter extends ArrayAdapter<MainMenuData> {

    Context context;
    List<MainMenuData> mainMenuItems;

// Context is the SubMenuActivity
    // objects is the list of items
    public MainMenuAdapter(Context context, List<MainMenuData> objects) {
        super(context, R.layout.listview_table_button, objects);
        this.context = context;
        this.mainMenuItems = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_table_button, parent, false);

        Button menuButton = (Button) view.findViewById((R.id.mainmenu));
        menuButton.setText(mainMenuItems.get(position).getMenuName());

       // TextView textView = (TextView) view.findViewById((R.id.itemName));

       // textView.setText(((mainMenuItems.get(position))).getName());

        return view;
    }
}

package royalplate2.royalplate.adapter;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.parse.ParseObject;
import royalplate2.royalplate.OrderedItem;
import royalplate2.royalplate.R;
import royalplate2.royalplate.SubMenuActivity;
import royalplate2.royalplate.data.SubMenuData;

import java.util.List;


/**
 * Created by hetu on 4/23/15.
 */
public class SubMenuAdapter extends ArrayAdapter<ParseObject>  {

    Context context;
    List<ParseObject> menuItems;
    SubMenuActivity subMenuActivity;

    double itemcost_db;
    String itemcost_st;
    String tableNo;
    //TextView noOfItemsTextview;
//  EditText noOfItemsEditText;
    List<OrderedItem> orderedList;
    TextView itemIdTextView;
    TextView itemNameTextView;
    TextView itemPriceTextView;

    TextView itemCostTextView;
    EditText noOfItemEditText;


    //Map<String, Ordered> noOfItems = new HashMap<String, Ordered>();

    // Context is the SubMenuActivity
    // objects is the list of items
    public SubMenuAdapter(Context context, List<ParseObject> objects, String tableNo, SubMenuActivity subMenuActivity) {
//public SubMenuAdapter(Context context, List<ParseObject> objects, double itemcost, String tableNo) {
//public SubMenuAdapter(Context context, List<ParseObject> objects, double itemcost, String tableNo) {

         super(context, R.layout.listview_item, objects);
        this.context = context;
        this.menuItems = objects;
        this.tableNo = tableNo;
        this.subMenuActivity = subMenuActivity;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final String getItemPrice;
        String getItemID;
       // final TextView itemNameTextView;

        View view = convertView;
        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, parent, false);

        }
        // view id
        itemIdTextView = (TextView) view.findViewById(R.id.itemId);

            getItemID = Integer.toString(((SubMenuData) (menuItems.get(position))).getID());
            itemIdTextView.setText(getItemID);

        // view ItemName

        itemNameTextView = (TextView) view.findViewById((R.id.itemName));

        itemNameTextView.setText(((SubMenuData) (menuItems.get(position))).getName());

        itemPriceTextView = (TextView) view.findViewById(R.id.itemPrice);
        getItemPrice = Double.toString(((SubMenuData) (menuItems.get(position))).getPrice());
        itemPriceTextView.setText(getItemPrice);


        /******
         * select no of items from the number picker
         */
        NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker);

        np.setMinValue(0);
        np.setMaxValue(20);
        np.setWrapSelectorWheel(false);

        final View finalView = view;
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            final EditText noOfItemsEditText = (EditText) finalView.findViewById(R.id.no_of_items);
            noOfItemsEditText.setText(String.valueOf(newVal));


            int noofitems = Integer.parseInt(noOfItemsEditText.getText().toString());
            double price = Double.parseDouble(getItemPrice); // give 2 decimal places

            //set the item price = (price * no of items)

          //  final TextView itempriceTextview = (TextView) finalView.findViewById(R.id.cost);
            double eachItemcost = price * noofitems;
            final String cost = String.format("%.2f", eachItemcost);
            itemCostTextView = (TextView) finalView.findViewById(R.id.itemcosttextview);

            itemCostTextView.setText(cost);


            noOfItemsEditText.addTextChangedListener(new TextWatcher() {
                private String lastText;

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                //
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) { }

                @Override
                public void afterTextChanged(Editable s) {

                    if (!s.toString().equals(null)){


                        String itemName = itemNameTextView.getText().toString();
                        String noOfItem = s.toString();
                        String itemcost =  itemCostTextView.getText().toString();

                        Log.i("tag" , "SubAdapter   " + noOfItem  + "    " + itemName+ "  " + itemcost);

                        subMenuActivity.saveOrderedList( noOfItem, itemName, itemcost);


                    }

                }
            });

            }

        });


        return view;
    }

}
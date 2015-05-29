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
import android.widget.Toast;

import com.parse.ParseObject;
import royalplate2.royalplate.OrderedItem;
import royalplate2.royalplate.R;
import royalplate2.royalplate.SubMenuActivity;
import royalplate2.royalplate.data.OrderedListData;
import royalplate2.royalplate.data.SubMenuData;

import java.util.List;


/**
 * Created by hetu on 4/23/15.
 */
public class SubMenuAdapter extends ArrayAdapter<ParseObject>  {

    Context context;
    List<ParseObject> menuItems;
    SubMenuActivity subMenuActivity;

    String tableno;


    /**********************************************************************************************
     *
     * @param context
     * @param objects
     * @param tableNo
     * @param subMenuActivity
     */
    // Context is the SubMenuActivity.  objects is the list of items
    /**********************************************************************************************/
    public SubMenuAdapter(Context context, List<ParseObject> objects, String tableNo, SubMenuActivity subMenuActivity) {

        super(context, R.layout.listview_item, objects);
        this.context = context;
        this.menuItems = objects;
        this.tableno = tableNo;
        this.subMenuActivity = subMenuActivity;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//
        final String getItemPrice;
        String getItemID;
        final TextView itemNameTextView;

        View view = convertView;

        if(convertView == null ) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_item, parent, false);

        }
        /***********************************************
         * Item ID appears in TextView
         **********************************************/
       final TextView itemIdTextView = (TextView) view.findViewById(R.id.itemId);

            getItemID = Integer.toString(((SubMenuData) (menuItems.get(position))).getID());
            itemIdTextView.setText(getItemID);


        /***********************************************
         * Item Name appears in TextView
         **********************************************/
            itemNameTextView = (TextView) view.findViewById((R.id.itemNameid));

            itemNameTextView.setText(((SubMenuData) (menuItems.get(position))).getName());

        /***********************************************
         * Item initial price appears in TextView
         **********************************************/
       final TextView initialPriceTextView= (TextView) view.findViewById(R.id.itemPriceid);
            getItemPrice = Double.toString(((SubMenuData) (menuItems.get(position))).getPrice());
            initialPriceTextView.setText(getItemPrice);


        /************************************************
         * select no of items from the number picker
         ************************************************/
       NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPickerid);

            np.setMinValue(0);
            np.setMaxValue(20);
            np.setWrapSelectorWheel(false);

            final View finalView = view;

            np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                final EditText noOfItemsEditText = (EditText) finalView.findViewById(R.id.noOfitem_edittextid);
                noOfItemsEditText.setText(String.valueOf(newVal));

                noOfItemsEditText.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    //
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) { }

                    @Override
                    public void afterTextChanged(Editable s) {


                        /*******************************************************************************
                         * get the no of items from the edittext and convert from string to int
                         *******************************************************************************/
                        int noofitems = Integer.parseInt(noOfItemsEditText.getText().toString());
                        /*******************************************************************************
                         * get each item initial price from the inititialTextview. Convet to double.
                         *******************************************************************************/
                        double price = Double.parseDouble(initialPriceTextView.getText().toString());


                        /***********************************************************************************
                         * To view the how much each item ordered cost
                         * set the item price = (price * no of items)
                         **********************************************************************************/
                        final TextView itemCostTextView = (TextView) finalView.findViewById(R.id.itemcost_textviewid);

                        final double eachItemcost = price * noofitems;

                        /***********************************************************************************
                         * Format the cost into 2 decimal place. Display the cost in itemCostTextview.
                         ************************************************************************************/
                        itemCostTextView.setText(String.format("%.2f", eachItemcost));


                        String  eachitemprice =  itemCostTextView.getText().toString();
                        String noofitems_st = noOfItemsEditText.getText().toString();

//                        try {
//
//
//                        if (noofitems>0){
                            if (!s.toString().equals(null) || !s.toString().equals("0")) {

                                String itemName = itemNameTextView.getText().toString();
                                //                           String noofItem = s.toString();
//
//
                                subMenuActivity.saveOrderedList(tableno, noofitems_st, itemName, eachitemprice);


                                Log.i("tag", "SAdap " + tableno+  "  " + noofitems_st + "  " + " " + itemName + " "+ eachitemprice);
                            }
//                        }
//                        catch (Exception e){
//                            Toast.makeText(context, "Item not selected!", Toast.LENGTH_SHORT);
//                        }
                    }
                });
            }

        });

        return view;
    }

}
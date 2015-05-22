package royalplate2.royalplate.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

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
    Button deleteItembutton;
    String itemName;
    String tableno;
    String noOfItem;
    // Context is the SubMenuActivity
    // objects is the list of items

    public OrderedListAdapter(Context context, List<OrderedListData> ordereditemsList, String tableno) {
        super(context, R.layout.listview_ordereditems, ordereditemsList);
        this.context = context;
        this.orderedItemsList = ordereditemsList;
        this.tableno = tableno;
       // this.noOfItem = noOfItem;
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

        deleteItembutton = (Button) view.findViewById(R.id.orded_deleteBtnid);
     //   Log.i("Remove", "Cancle not clicked");


        deleteItembutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //remove item
                Log.i("Remove", "Cancle clicked");


                Integer index = (Integer) view.getTag();
                orderedItemsList.remove(position);
               // orderedItemsList.set(position, orderedItemsList.remove(index.intValue()));
              //  orderedItemsList.remove(index.intValue());
                notifyDataSetChanged();

//                final ParseQuery query = new ParseQuery("OrderedListParse");
//
//                query.whereEqualTo("TableNo", tableno);
//                query.whereEqualTo("ItemName",itemName );
                //query.whereEqualTo("NoOfItem", noofitem);

//                query.findInBackground(new FindCallback<ParseObject>() {
//                    @Override
//                    public void done(List<ParseObject> orderedlist, ParseException e) {
//                        if(e == null || orderedlist.size() >0){
//
//// iterate over all messages and delete them
//                            for(ParseObject ol : orderedlist)
//                            {
//                                ol.deleteInBackground();
//                            }
//                        }
//                        else {
//                                    Log.e("TAG", e.getMessage(), e);
//                        }
//
//
//
//                            //for (int i=0; i<orderedlist.size(); i++) {
//
////                                ParseObject itemName = orderedlist.get(i);
////                                //ParseObject tableNo = orderedlist.get(i);
//////                                try {
////                                    itemName.deleteInBackground();
////                                    //tableNo.delete();
////
//////                                } catch (ParseException e1) {
//////                                    Log.e("TAG", e1.getMessage(), e1);
//////                                }
//
//                          // }
//                        }
//
//                    //}
//
//                });



            }
        });

        return view;
    }
}

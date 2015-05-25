package royalplate2.royalplate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by operamac on 4/29/15.
 */

// Not being used. Double check, and decide to delete it
public class WaiterSelectorFragment extends Fragment {

    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_orderlist, container, false);
        Log.i("FRAGMET", "WAITER LIST");

        final Button orderBtn = (Button) v.findViewById(R.id.orderbutton);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            // temporaly going back to MenuActivity
            Intent orderBtnIntent = new Intent(v.getContext(), OrderSucceedActivity.class);
//                orderBtnIntent.putExtra("table no", 1);
            String tableNum = getActivity().getIntent().getExtras().getString("table no");
            orderBtnIntent.putExtra("table no", tableNum);
            startActivity(orderBtnIntent);
            }
        });
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.i("onCreate", "WURKING");
    }
}

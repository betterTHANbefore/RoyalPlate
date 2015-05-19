package royalplate2.royalplate;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import royalplate2.royalplate.adapter.MainMenuAdapter;
import royalplate2.royalplate.data.MainMenuData;

/**
 * Created by operamac on 5/1/15.
 */
public class OrderSucceedActivity extends Activity {

    private TextView tv;
    private ListView lv;

    ArrayAdapter<String> adapter;

    Set<String> orderedItemList = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_succeed_activity);

        tv = (TextView) findViewById(R.id.table_num_view);
        tv.setText("Table " + getIntent().getExtras().getString("tableNo"));

        lv = (ListView) findViewById(R.id.menulist_left);

//        SharedPreferences sharedPreferences;
//        SharedPreferences sharedPreferences = getSharedPreferences("OrderedItemSet", MODE_PRIVATE);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        orderedItemList = sharedPreferences.getStringSet("OrderedItemSet", new HashSet<String>());
//        ArrayList<String> listBack = new ArrayList<String>(orderedItemList);

//        for (String s: orderedItemList)
        Log.i("JSON in OrderLaLa", orderedItemList.toString());

//        ObjectMapper mapper = new ObjectMapper();
//        JSONObject jo;
//        jo = mapper.realValue(orderedItemList, new JSONObject());


//
//        ParseObject object = new ParseObject("OrderedList");
//        object.put("Hello", orderedItemList.toString());
//        Iterator iterator = orderedItemList.iterator();
//        while (iterator != null) {
//            object.add();
//        }
//        JSONArray jsonArray = (JSONArray) orderedItemList;

//        ParseObject object = new ParseObject("OrderedList");
//        object.put("hello", 2);
//        object.addAll("hell", (Collection<?>) jsonArray);
////        adapter = new ArrayAdapter<String>(this, R.id.menulist_left)

    }
//
//    private void loadItems() {
//        final ParseQuery<MainMenuData> mainMenuItems = ParseQuery.getQuery("MenuParse");
//        mainMenuItems.findInBackground(new FindCallback<MainMenuData>() {
//
//            @Override
//            public void done(List<MainMenuData> mainMenuItems, ParseException e) {
//                mainMenuAdapter = new MainMenuAdapter(MenuActivity.this, mainMenuItems);
//                gridview.setAdapter(mainMenuAdapter);
//
//            }
//        });
//    }
}

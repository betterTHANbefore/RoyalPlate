package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import royalplate2.royalplate.adapter.MenuAdapter;

import java.util.List;

/**
 * Created by operamac on 4/28/15.
 */
public class SendOrder extends Activity {


    MenuAdapter menuAdapter;
    ListView listview;
//    ArrayList<ParseObject> tableOrderedList[] = new ArrayList<ParseObject>;

//    @Override
//    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//
////        ParseObject orderedList[] = new ParseObject[];
////        ArrayList<ParseObject> tableOrderedList[];
//
//        Log.i("HELLOOOOO", "SUEEEEEEE");
//        Intent prevIntent = getIntent();
//        int tableNum = prevIntent.getExtras().getInt("table no");
////        ParseObject orderedList = new ParseObject("Table1");
////        tableOrderedList[tableNum].
//        object.put("title", "hello");
//        object.put("passed value", Integer.toString(tableNum));
//        tables.put("parent", object);
//        tables.saveInBackground();
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_order);
        ParseObject object = new ParseObject("1");
        ParseObject tables = new ParseObject("Tables");

        Log.i("HELLOOOOO", "SUEEEEEEE");
        Intent prevIntent = getIntent();
        int tableNum = prevIntent.getExtras().getInt("table no");
//        ParseObject orderedList = new ParseObject("Table1");
//        tableOrderedList[tableNum].
        object.put("ItemName", "hello");
        object.put("ItemName", Integer.toString(tableNum));
        tables.put("parent", object);
        tables.saveInBackground();


        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tables");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, com.parse.ParseException e) {
                menuAdapter = new MenuAdapter(SendOrder.this, parseObjects);
                listview.setAdapter(menuAdapter);
            }
        });

        Log.i("HELLOOOOO", "SUEEEEEEE");
    }
}

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
// Is this class being used? If not, delete it.
public class SendOrder extends Activity {

    MenuAdapter menuAdapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_order);
        ParseObject object = new ParseObject("1");
        ParseObject tables = new ParseObject("Tables");

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

    }
}

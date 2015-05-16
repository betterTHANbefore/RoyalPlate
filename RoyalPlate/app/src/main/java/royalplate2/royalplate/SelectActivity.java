package royalplate2.royalplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SelectActivity extends ActionBarActivity {

    private Button hostessButton;
    private Button waiterButton;
    private Button chefButton;
    private Button managerButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_royalplate);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
////        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
//

        hostessButton = (Button) findViewById(R.id.hostess_button);
        waiterButton = (Button) findViewById(R.id.waiter_button);
        chefButton = (Button) findViewById(R.id.chef_button);
        managerButton = (Button) findViewById(R.id.manager_button);

        ImageView logoImage = (ImageView) findViewById(R.id.royalplate_title);

        logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent welcomeApp = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(welcomeApp);
            }
        });

        hostessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent hostessIntent = new Intent(SelectActivity.this, HostessActivity.class);
                startActivity(hostessIntent);
            }
        });

        waiterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AssignedTableActivity bypass
              //  Intent waiterIntent = new Intent(getApplicationContext(), AssignedTableActivity.class);
                // Below Intent initialization for normal flow
                Intent  waiterIntent = new Intent(getApplicationContext(), SignupOrLoginActivity.class);
//                Below line is for login bypass
//                Intent  waiterIntent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(waiterIntent);
            }
        });

        chefButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chefIntent = new Intent(getApplicationContext(), ChefActivity.class);
                startActivity(chefIntent);
            }
        });

        managerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent managerIntent = new Intent(getApplicationContext(), ManagerActivity.class);
                startActivity(managerIntent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menuItem) {
//        // Inflate the menu items for use in the action bar
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_royalplate, menuItem);



//        for(int i = 0; i<menuItem.size(); i++)
//        {
//            MenuItem item = menuItem.getItem(i);
//
//            switch (item.getItemId()) {
//
//                case R.id.hostesspage:
//
//                   Intent hostpage = new Intent(getApplicationContext(),HostessActivity.class);
//                   startActivity(hostpage);
//
//                    Log.i("Tag", " host");
//                    return true;
//
//
//                case R.id.waiterpage:
//
//                   Intent waiterpage = new Intent(getApplicationContext(),WelcomeActivity.class);
//                    this.startActivity(waiterpage);
//                    return true;
//
//                case R.id.chefpage:
//
//                    Intent chefpage = new Intent(getApplicationContext(),ChefActivity.class);
//                    this.startActivity(chefpage);
//                    return true;
//
//
//                case R.id.managerpage:
//
//                    Intent managerpage = new Intent(getApplicationContext(),ManagerActivity.class);
//                    this.startActivity(managerpage);
//                    return true;
//
//                default:
//
//                    return super.onOptionsItemSelected(item);
//
//
//            }

//        }
//
//        return super.onCreateOptionsMenu(menuItem);
//    }

//public boolean onOptionsItemSeletected(MenuItem item) {
//
//        Intent menu;
//        switch (item.getItemId()) {
//
//            case R.id.hostesspage:
//
//                menu = new Intent(getApplicationContext(),HostessActivity.class);
//                this.startActivity(menu);
//
//                Log.i("Tag", " host");
//                return true;
//
//
//            case R.id.waiterpage:
//
//                menu = new Intent(getApplicationContext(),WelcomeActivity.class);
//                this.startActivity(menu);
//                return true;
//
//            case R.id.chefpage:
//
//                menu = new Intent(getApplicationContext(),ChefActivity.class);
//                this.startActivity(menu);
//                return true;
//
//
//            case R.id.managerpage:
//
//                menu = new Intent(getApplicationContext(),ManagerActivity.class);
//                this.startActivity(menu);
//                return true;
//
//            default:
//
//                return super.onOptionsItemSelected(item);
//
//
//        }
//
//
//    }

}
package royalplate2.royalplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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

}
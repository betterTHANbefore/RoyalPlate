package royalplate2.royalplate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by operamac on 4/14/15.
 */
public class SignupOrLoginActivity extends Activity {

    private Button signupButton;
    private Button loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_or_login_activity);

        // Sign Up
        signupButton = (Button) findViewById(R.id.SignUpButton);
        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOrLoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Login
        loginButton = (Button) findViewById(R.id.LoginButton);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupOrLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

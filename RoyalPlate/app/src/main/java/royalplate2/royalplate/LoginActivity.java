package royalplate2.royalplate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

import royalplate2.royalplate.data.WaiterData;

/**
 * Created by operamac on 4/14/15.
 */
public class LoginActivity extends Activity {

    private EditText usernameView;
    private EditText passwordView;
    private Button resetBtn;
    WaiterData waitertables;

    @Override
    public void onCreate(Bundle savedInstanceState ) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        // Set up the login form.
        usernameView = (EditText) findViewById(R.id.userName);
        passwordView = (EditText) findViewById(R.id.passWord);
        resetBtn = (Button) findViewById(R.id.reset_button);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameView.setText("");
                passwordView.setText("");
            }
        });
        // Set up the submit button click handler
        findViewById(R.id.action_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Validate the log in data
                boolean validationError = false;
                StringBuilder validationErrorMessage =
                        new StringBuilder(getResources().getString(R.string.error_intro));
                if (isEmpty(usernameView)) {
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_username));
                }
                if (isEmpty(passwordView)) {
                    if (validationError) {
                        validationErrorMessage.append(getResources().getString(R.string.error_join));
                    }
                    validationError = true;
                    validationErrorMessage.append(getResources().getString(R.string.error_blank_password));
                }
                validationErrorMessage.append(getResources().getString(R.string.error_end));

                // If there is a validation error, display the error
                if (validationError) {
                    Toast.makeText(LoginActivity.this, validationErrorMessage.toString(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }

                // Set up a progress dialog
                final ProgressDialog dlg = new ProgressDialog(LoginActivity.this);
                dlg.setTitle("Please wait.");
                dlg.setMessage("Logging in.  Please wait.");
                dlg.show();
                // Call the Parse login method
                ParseUser.logInInBackground(usernameView.getText().toString(), passwordView.getText()
                        .toString(), new LogInCallback() {

                    @Override
                    public void done(ParseUser user, ParseException e) {
                        dlg.dismiss();
                        if (e != null) {
                            // Show the error message
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            // Start an intent for the dispatch activity
                            // Below is for without table assignment
//                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);

                           final String username = usernameView.getText().toString();
                            /***************************
                             * store username on parse
                             *****************************/


                       // ParseQuery<ParseObject> query = ParseQuery.getQuery("WaiterParse");
                            ParseQuery<ParseUser> query = ParseUser.getQuery();
                            query.whereEqualTo("WaiterName", username);
                        query.findInBackground(new FindCallback<ParseUser>() {

                            @Override
                            public void done(List<ParseUser> parseObjects, ParseException e) {
                                if(e != null) {

                                    // username does not exist
                                    // so add
                                    waitertables = new WaiterData();

                                    waitertables.setWaiter(username);
                                    waitertables.saveInBackground();
                                    Log.i("tag", "Does not exist");


                                }else{
                                    Toast.makeText(LoginActivity.this, username + " already exist!", Toast.LENGTH_LONG).show();
                                }

                            }
                        });


                            Intent intent = new Intent(LoginActivity.this, AssignedTableActivity.class);
                            intent.putExtra("userName", username);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }
}

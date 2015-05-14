package royalplate2.royalplate;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;


/**
 * Created by hetu on 4/9/15.
 */
public class WelcomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(final Bundle savedInstancesState){

        super.onCreate((savedInstancesState));
        setContentView(R.layout.welcome_activity);

//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_royalplate);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);


//        anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.welcome_animation);
//
//        anim.setAnimationListener(this);

        ImageView logoImage = (ImageView)findViewById(R.id.royalplate_logo);
        logoImage.setBackgroundResource(R.drawable.animated_background);
        AnimationDrawable frameAnimation = (AnimationDrawable) logoImage.getBackground();
        frameAnimation.start();

        logoImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent welcomeApp = new Intent(WelcomeActivity.this,SelectActivity.class);
                startActivity(welcomeApp);
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu items for use in the action bar
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_royalplate, menu);
////        menu.add(" ")
////            .setIcon(R.drawable.ic_royalplate).setTitle("RoyalPlate");
//        return super.onCreateOptionsMenu(menu);
//    }


}




package it.pjlabs.growroom.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import it.pjlabs.growroom.GrowRoomApplication;
import it.pjlabs.growroom.R;
import it.pjlabs.growroom.data.entities.User;
import it.pjlabs.growroom.data.rest.GrowRoomApiService;
import it.pjlabs.growroom.fragments.MainContentFragment;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @Inject GrowRoomApiService growRoomApiService;
    @Inject SharedPreferences sharedPreferences;

    private Fragment mFragmentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContent = MainContentFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content_frag, mFragmentContent).commit();

        Call<User> userCall = growRoomApiService.getUser(1l);

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
      super.onBackPressed();
    }


    @Override
    protected void inject() {
         getMyApplication().getGrowRoomApiComponent().inject(this);
    }

}

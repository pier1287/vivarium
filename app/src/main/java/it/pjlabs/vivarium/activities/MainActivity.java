package it.pjlabs.vivarium.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import it.pjlabs.vivarium.R;
import it.pjlabs.vivarium.data.entities.User;
import it.pjlabs.vivarium.data.rest.VivariumApiService;
import it.pjlabs.vivarium.fragments.MainContentFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fab) FloatingActionButton mFab;

    @Inject VivariumApiService vivariumApiService;
    @Inject SharedPreferences sharedPreferences;

    private Fragment mFragmentContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContent = MainContentFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content_frag, mFragmentContent).commit();

        mFab.setOnClickListener(
                v -> Snackbar.make(v,"Replace with your own action", Snackbar.LENGTH_SHORT).setAction("Action",null).show());


        Observable<User> user = vivariumApiService.getUser(1l);

        user.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(u -> Log.i("User", u.toString()));

        }

    @Override
    public void onBackPressed() {
      super.onBackPressed();
    }


    @Override
    protected void inject() {
         getMyApplication().getVivariumApiComponent().inject(this);
    }

}

package it.pjlabs.vivarium.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.github.lzyzsd.circleprogress.CircleProgress;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import it.pjlabs.vivarium.R;
import it.pjlabs.vivarium.data.entities.Measurement;
import it.pjlabs.vivarium.data.rest.VivariumApiService;
import it.pjlabs.vivarium.fragments.LineChartFragment;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fab) FloatingActionButton mFab;

    @Inject VivariumApiService vivariumApiService;
    @Inject SharedPreferences sharedPreferences;

    private LineChartFragment mFragmentContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentContent = LineChartFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.main_content_frag, mFragmentContent).commit();

        mFab.setOnClickListener(
                v -> Snackbar.make(v,"Replace with your own action", Snackbar.LENGTH_SHORT).setAction("Action",null).show());

        final Scheduler scheduler = Schedulers.from(Executors.newSingleThreadExecutor());

        Observable.interval(5, TimeUnit.SECONDS)
                .flatMap(n ->
                        vivariumApiService.getLatestTemperature()
                                .retry(3)
                                .subscribeOn(scheduler))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Measurement>() {
                    @Override
                    public void call(Measurement measurement) {
                        mFragmentContent.addMeasurement(measurement);
                        Log.i("API_SERVICE",measurement.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("API_SERVICE","error rest api call");
                    }
                });

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

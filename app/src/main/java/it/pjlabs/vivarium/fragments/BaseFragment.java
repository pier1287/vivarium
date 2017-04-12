package it.pjlabs.vivarium.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import it.pjlabs.vivarium.R;

/**
 * Created by Pj on 19/11/16.
 */

public abstract class BaseFragment extends Fragment{

    protected View mInflatedView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInflatedView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this,mInflatedView);

        return  mInflatedView;
    }

    public Context getApplicationContext(){
        return this.getActivity().getApplicationContext();
    }

    protected abstract int getLayoutId();
}

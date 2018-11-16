package com.swapi.atry.tryswapi;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapi.atry.tryswapi.repository.SWItemRepo;
import com.swapi.atry.tryswapi.repository.SWItemRepoImpl;
import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.dto.SWPlanet;
import com.swapi.atry.tryswapi.repository.local.DBConstant;
import com.swapi.atry.tryswapi.repository.local.SWDB;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepo;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepoImpl;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepo;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepoImpl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A fragment representing a single Switem detail screen.
 * This fragment is either contained in a {@link SwitemListActivity}
 * in two-pane mode (on tablets) or a {@link SwitemDetailActivity}
 * on handsets.
 */
public class SwitemDetailFragment extends Fragment {

    private static String TAG = SwitemDetailFragment.class.getSimpleName();

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private SWItem mItem;

    Disposable disposable;

    SWPlanetViewModel swPlanetViewModel;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SwitemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getParcelable(ARG_ITEM);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.switem_detail, container, false);
        String homeWorld = mItem.getHomeWorld();
        if(mItem != null && homeWorld != null) {

            //Try to find the number for homeworld / planet API
            String re1=".*?";	// Non-greedy match on filler
            String re2="(\\d+)";	// Integer Number 1

            Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
            Matcher m = p.matcher(homeWorld);
            if (m.find())
            {
                String planetId = m.group(1);
                SWRemoteRepo swRemoteRepo = new SWRemoteRepoImpl();
                SWDB swdb = Room.databaseBuilder(this.getContext().getApplicationContext(),
                        SWDB.class, DBConstant.DB_NAME).build();
                SWLocalRepo swLocalRepo = new SWLocalRepoImpl(swdb.swItemDao(), swdb.swPlanetDao());
                SWItemRepo swItemRepo = new SWItemRepoImpl(swLocalRepo, swRemoteRepo);
                disposable = swItemRepo.getSWPlanetObservable(planetId)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<SWPlanet>() {
                            @Override
                            public void onNext(SWPlanet swPlanet) {
                                if (swPlanet != null) {
                                    Log.d(TAG, "Found SW Planet : " + swPlanet.getName());
                                    if (mItem != null && swPlanet.getName() != null) {
                                        ((TextView) rootView.findViewById(R.id.switem_detail)).setText(swPlanet.getName());
                                    }
                                } else
                                    Log.d(TAG, "STAR TREK !  maybe");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

                swPlanetViewModel = ViewModelProviders.of(this).get(SWPlanetViewModel.class);
                swPlanetViewModel.getSWPlanetLiveData(planetId).observe(SwitemDetailFragment.this, new Observer<SWPlanet>() {
                    @Override
                    public void onChanged(@Nullable SWPlanet swPlanet) {

                        if (swPlanet != null && swPlanet.getName() != null) {
                            Log.d(TAG, "Found Local SW Planet : " + swPlanet.getName());
                            ((TextView) rootView.findViewById(R.id.switem_detail)).setText(swPlanet.getName());
                        }
                    }
                });

            }


        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        if(disposable != null) disposable.dispose();
        super.onDestroy();
    }
}

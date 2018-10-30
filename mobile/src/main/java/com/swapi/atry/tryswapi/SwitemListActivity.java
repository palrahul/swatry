package com.swapi.atry.tryswapi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.swapi.atry.tryswapi.dummy.DummyContent;
import com.swapi.atry.tryswapi.repository.SWItemRepo;
import com.swapi.atry.tryswapi.repository.SWItemRepoImpl;
import com.swapi.atry.tryswapi.repository.dto.SWItem;
import com.swapi.atry.tryswapi.repository.local.DBConstant;
import com.swapi.atry.tryswapi.repository.local.SWDB;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepo;
import com.swapi.atry.tryswapi.repository.local.SWLocalRepoImpl;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepo;
import com.swapi.atry.tryswapi.repository.remote.SWRemoteRepoImpl;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * An activity representing a list of Switems. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SwitemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SwitemListActivity extends AppCompatActivity {

    private static String TAG = SwitemListActivity.class.getSimpleName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private Disposable disposable;
    private SWItemViewModel swItemViewModel;
    private SimpleItemRecyclerViewAdapter simpleItemRecyclerViewAdapter;

    @Override
    protected void onDestroy() {
        if(disposable != null) disposable.dispose();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switem_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.switem_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.switem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        /// test
        SWRemoteRepo swRemoteRepo = new SWRemoteRepoImpl();
        SWDB swdb = Room.databaseBuilder(getApplicationContext(),
                    SWDB.class, DBConstant.DB_NAME).build();
        SWLocalRepo swLocalRepo = new SWLocalRepoImpl(swdb.swDao());
        SWItemRepo swItemRepo = new SWItemRepoImpl(swLocalRepo, swRemoteRepo);
        disposable = swItemRepo.getSWItemObservable("Obi")
                        .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableObserver<SWItem>() {
                    @Override
                    public void onNext(SWItem swItem) {
                        if(swItem != null)
                            Log.d(TAG, "Found SW item : " + swItem.getName());
                        else
                            Log.d(TAG, "NOPE");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });



        ///end test

        swItemViewModel = ViewModelProviders.of(this).get(SWItemViewModel.class);
        swItemViewModel.getSWItemLiveData("R2-D2").observe(this, new Observer<SWItem>() {
            @Override
            public void onChanged(@Nullable SWItem swItem) {
                simpleItemRecyclerViewAdapter.mValues.clear();
                simpleItemRecyclerViewAdapter.mValues.add(swItem);
                

            }
        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        simpleItemRecyclerViewAdapter = new SimpleItemRecyclerViewAdapter(this, mTwoPane);
        recyclerView.setAdapter(simpleItemRecyclerViewAdapter);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final SwitemListActivity mParentActivity;
        private final List<SWItem> mValues = new ArrayList<>();
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(SwitemDetailFragment.ARG_ITEM_ID, item.id);
                    SwitemDetailFragment fragment = new SwitemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.switem_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, SwitemDetailActivity.class);
                    intent.putExtra(SwitemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(SwitemListActivity parent,
                                      boolean twoPane) {
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.switem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mIdView.setText(mValues.get(position).getName());
            holder.mContentView.setText(mValues.get(position).getHomeWorld());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}

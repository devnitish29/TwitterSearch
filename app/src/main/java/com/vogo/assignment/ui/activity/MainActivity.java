package com.vogo.assignment.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.vogo.assignment.R;
import com.vogo.assignment.ui.adapter.TweetListAdapter;
import com.vogo.assignment.viewModel.TweetViewModel;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private RecyclerView recyclerView;
    private SearchView searchView;
    TweetViewModel itemViewModel;
    TweetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.edSearch);
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
        searchView.setIconified(false);


        //getting our ItemViewModel
        itemViewModel = ViewModelProviders.of(this).get(TweetViewModel.class);
        //creating the Adapter
        adapter = new TweetListAdapter();
        recyclerView.setAdapter(adapter);
    }





    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {


        itemViewModel.setSearchQuery(newText.trim());
        itemViewModel.itemPagedList.observe(this, results -> {
            assert results != null;
            adapter.submitList(results);
        });

        return false;
    }
}

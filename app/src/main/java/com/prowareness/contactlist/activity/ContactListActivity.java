package com.prowareness.contactlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.prowareness.contactlist.adapter.ContactAdapter;
import com.prowareness.contactlist.adapter.DividerDecorater;
import com.prowareness.contactlist.base.App;
import com.prowareness.contactlist.interfaces.PresenterInterface;
import com.prowareness.contactlist.model.ContactList;
import com.prowareness.contactlist.persenter.ContactActivityPersenter;
import com.prowareness.contactlist.service.RxService;

import java.util.HashMap;

import javax.inject.Inject;

public class ContactListActivity extends AppCompatActivity implements PresenterInterface {

    private RecyclerView mRecyclerView;
    private ProgressBar progressBar;
    ContactActivityPersenter presenter;

    @Inject
    RxService rxService;
    @Inject
    HashMap<String, String> map;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        App.getComponent().inject(this);

        init();

        presenter = new ContactActivityPersenter(rxService, this, map);
        presenter.getContactList();

    }

    private void init() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerDecorater decorater = new DividerDecorater(this);
        mRecyclerView.addItemDecoration(decorater);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {

    }

    @Override
    public void getContactList(ContactList contactList) {
        ContactAdapter adapter = new ContactAdapter(this, contactList.getResult());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}

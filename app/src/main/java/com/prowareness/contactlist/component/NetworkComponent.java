package com.prowareness.contactlist.component;

import com.prowareness.contactlist.ContactListActivity;
import com.prowareness.contactlist.adapter.ContactAdapter;
import com.prowareness.contactlist.module.NetworkModule;
import com.prowareness.contactlist.persenter.ContactActivityPersenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by vaibhavjain on 21/3/17.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    void inject(ContactListActivity activity);
    void inject(ContactAdapter adapter);
    void inject(ContactActivityPersenter persenter);
}

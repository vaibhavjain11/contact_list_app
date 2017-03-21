package com.prowareness.contactlist.persenter;

import android.accounts.NetworkErrorException;
import android.content.SharedPreferences;

import com.prowareness.contactlist.base.App;
import com.prowareness.contactlist.interfaces.PresenterInterface;
import com.prowareness.contactlist.model.ContactList;
import com.prowareness.contactlist.service.RxService;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Set;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public class ContactActivityPersenter{

    @Inject
    SharedPreferences sharedPreferences;
    private final RxService service;
    private final PresenterInterface view;
    private final HashMap hashMap;
    private CompositeSubscription subscriptions;

    public ContactActivityPersenter(RxService service, PresenterInterface view, HashMap map){
        this.service = service;
        this.view = view;
        this.hashMap = map;
        this.subscriptions = new CompositeSubscription();
        App.getComponent().inject(this);
    }

    public void getContactList(){
        view.showWait();

        Subscription subscription = service.getContactList(hashMap, new RxService.GetContactListCallback() {

            @Override
            public void onSuccess(ContactList contactList) {
                view.removeWait();

                Set<String> set = sharedPreferences.getStringSet("deletedList",null);

                if(set!=null){
                    ListIterator<ContactList.Contact> iterator = contactList.getResult().listIterator();
                    while(iterator.hasNext()){
                        ContactList.Contact contact= iterator.next();
                        if(set.contains(contact.getUid())){
                            iterator.remove();
                        }
                    }
                }
                view.getContactList(contactList);

            }

            @Override
            public void onError(NetworkErrorException e) {
                view.removeWait();
                view.onFailure(e.getMessage());
            }
        });

        subscriptions.add(subscription);
    }

    public void onStop(){
        subscriptions.unsubscribe();
    }

}

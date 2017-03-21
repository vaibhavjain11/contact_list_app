package com.prowareness.contactlist.service;

import android.accounts.NetworkErrorException;

import com.prowareness.contactlist.model.ContactList;

import java.util.HashMap;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public class RxService {

    private final RestService contactListService;

    public RxService(RestService service) {
        contactListService = service;
    }

    public Subscription getContactList(final HashMap hashMap, final GetContactListCallback callback) {

        return contactListService.getContactList(hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends ContactList>>() {
                    @Override
                    public Observable<? extends ContactList> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })

                .subscribe(new Subscriber<ContactList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkErrorException());
                    }

                    @Override
                    public void onNext(ContactList contactList) {
                        callback.onSuccess(contactList);
                    }
                });
    }

    public interface GetContactListCallback {
        void onSuccess(ContactList contactList);

        void onError(NetworkErrorException e);
    }
}

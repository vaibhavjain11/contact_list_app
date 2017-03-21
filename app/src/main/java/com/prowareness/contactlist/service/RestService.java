package com.prowareness.contactlist.service;

import com.prowareness.contactlist.model.ContactList;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public interface RestService {

    @POST("contactlist.php")
    @FormUrlEncoded
    Observable<ContactList> getContactList(@FieldMap HashMap<String,String> params);
}

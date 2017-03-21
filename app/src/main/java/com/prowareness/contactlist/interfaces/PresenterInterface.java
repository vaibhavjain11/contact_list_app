package com.prowareness.contactlist.interfaces;

import com.prowareness.contactlist.model.ContactList;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public interface PresenterInterface {

    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getContactList(ContactList contactList);
}

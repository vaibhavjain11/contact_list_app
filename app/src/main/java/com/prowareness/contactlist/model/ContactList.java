package com.prowareness.contactlist.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public class ContactList {

    @SerializedName("message")
    @Expose
    private Object message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("result")
    @Expose
    private List<Contact> result = null;

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Contact> getResult() {
        return result;
    }

    public void setResult(List<Contact> result) {
        this.result = result;
    }

    public class Contact {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("uid")
        @Expose
        private String uid;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

    }

}

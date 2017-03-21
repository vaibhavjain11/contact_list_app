package com.prowareness.contactlist.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prowareness.contactlist.base.App;
import com.prowareness.contactlist.R;
import com.prowareness.contactlist.model.ContactList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

/**
 * Created by vaibhavjain on 21/3/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    @Inject
    SharedPreferences sharedPreferences;

    private final Context mContext;
    private List<ContactList.Contact> mList;

    public ContactAdapter(Context context, List<ContactList.Contact> list) {
        this.mContext = context;
        this.mList = list;
        App.getComponent().inject(this);
    }


    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_contact, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, final int position) {

        holder.mNameTextView.setText(mList.get(position).getName());
        holder.mDeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Set<String> set = sharedPreferences.getStringSet("deletedList", null);
                if (set == null) {
                    set = new HashSet<String>();
                }
                ArrayList<String> list = new ArrayList<String>(set);
                list.add(mList.get(position).getUid());
                editor.putStringSet("deletedList", new HashSet<String>(list));
                editor.commit();
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView mNameTextView;
        public TextView mDeleteContact;

        public ContactViewHolder(View itemView) {
            super(itemView);

            mNameTextView = (TextView) itemView.findViewById(R.id.name);
            mDeleteContact = (TextView) itemView.findViewById(R.id.deleteContact);
        }
    }
}

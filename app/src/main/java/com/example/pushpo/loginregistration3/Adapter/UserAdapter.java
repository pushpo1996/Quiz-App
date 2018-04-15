package com.example.pushpo.loginregistration3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pushpo.loginregistration3.Activity.UpdateActivity;
import com.example.pushpo.loginregistration3.Database.Database;
import com.example.pushpo.loginregistration3.R;
import com.example.pushpo.loginregistration3.model.User;

import java.util.List;

/**
 * Created by pushpo on 3/21/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> listUsers;
    private Database db;
    private Context context;
    private Intent intent;

    public UserAdapter(List<User> listUsers,Context context) {
        this.listUsers = listUsers;
        this.context=context;
        db=new Database(this.context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.textViewName.setText(listUsers.get(position).getName());
        holder.textViewEmail.setText(listUsers.get(position).getEmail());
        holder.textViewPassword.setText(listUsers.get(position).getPassword());
        holder.delete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        delete(position);
                    }
                }
        );

        holder.update.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update(position);

                    }
                }
        );
    }

    @Override
    public int getItemCount() {
        Log.v(UserAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }

    public void delete(int position){
        String email=listUsers.get(position).getEmail();
        boolean isDelete=db.delete(email);
        this.listUsers.remove(position);
        if(isDelete){
            notifyDataSetChanged();
            notifyItemRemoved(position);
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(int position){
        intent=new Intent(context, UpdateActivity.class);
        intent.putExtra("name",listUsers.get(position).getName());
        intent.putExtra("email",listUsers.get(position).getEmail());
        intent.putExtra("password",listUsers.get(position).getPassword());
        context.startActivity(intent);

    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewName;
        public AppCompatTextView textViewEmail;
        public AppCompatTextView textViewPassword;
        public AppCompatButton update,delete;

        public UserViewHolder(View view) {
            super(view);
            textViewName = (AppCompatTextView) view.findViewById(R.id.textViewName);
            textViewEmail = (AppCompatTextView) view.findViewById(R.id.textViewEmail);
            textViewPassword = (AppCompatTextView) view.findViewById(R.id.textViewPassword);
            update=(AppCompatButton)view.findViewById(R.id.updateButton);
            delete=(AppCompatButton)view.findViewById(R.id.deleteButton);
        }
    }

}

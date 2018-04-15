package com.example.pushpo.loginregistration3.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pushpo.loginregistration3.Database.Database;
import com.example.pushpo.loginregistration3.R;
import com.example.pushpo.loginregistration3.model.User;

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = UpdateActivity.this;

    private EditText name,email,password;
    private Button update,showUpdate;
    private Database db;
    private User user;
    private String getName,getEmail,getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initViews();
        initListner();
        initObject();
    }

    public void initViews(){
        name=(EditText)findViewById(R.id.name);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        update=(Button)findViewById(R.id.update);
        showUpdate=(Button)findViewById(R.id.showUpdate);

    }

    public void initListner(){
        update.setOnClickListener(this);
        showUpdate.setOnClickListener(this);
    }

    public void initObject(){
        db=new Database(this);
        user=new User();
        getDataFromIntent();
        setData();
    }

    public void getDataFromIntent(){
        getName=getIntent().getStringExtra("name");
        getEmail=getIntent().getStringExtra("email");
        getPassword=getIntent().getStringExtra("password");
    }

    public void setData(){
        name.setText(getName);
        email.setText(getEmail);
        password.setText(getPassword);
    }

    public void update(){
        user.setName(name.getText().toString());
        user.setEmail(email.getText().toString());
        user.setPassword(password.getText().toString());
       boolean isUpdate=db.updateUser(user);
       if(isUpdate){
           Toast.makeText(activity, "Update successfully", Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(activity, "update failed", Toast.LENGTH_SHORT).show();
       }
    }
    //This implemented method is to listen the click on view

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update:
                update();
                break;
            case R.id.showUpdate:
                showUpdate();
                break;
        }
    }

    public void showUpdate(){
        Cursor cursor=db.getData(getEmail);
        if(cursor !=null){
            name.setText(user.getName());
            email.setText(user.getEmail());
            password.setText(user.getPassword());
        }
    }
}

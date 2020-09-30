package com.keeyoshi.internetbasedservice.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keeyoshi.internetbasedservice.Admin.AdminCategoryActivity;
import com.keeyoshi.internetbasedservice.Model.Users;
import com.keeyoshi.internetbasedservice.R;

public class LoginActivity extends AppCompatActivity {

    private EditText InputPhone, InputPassword;
    private Button BtnStaffLogin;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        InputPhone=(EditText)findViewById(R.id.staff_login_phone);
        InputPassword=(EditText)findViewById(R.id.staff_login_password);
        BtnStaffLogin=(Button)findViewById(R.id.staff_login_btn);
        loadingBar = new ProgressDialog(this);

        BtnStaffLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginStaff();
            }
        });
    }

    private void loginStaff() {
        String phone = InputPhone.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone))
        {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();


            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final String phone,final String password) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("Staffs").child(phone).exists()) {
                    Users staffsData = dataSnapshot.child("Staffs").child(phone).getValue(Users.class);
                    if (staffsData.getPhone().equals(phone)) {
                        if (staffsData.getPassword().equals(password)) {
                            Toast.makeText(LoginActivity.this, "Welcome Sraff, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(LoginActivity.this, StaffPageActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }

                    else{
                        loadingBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Account with this phone number not register", Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
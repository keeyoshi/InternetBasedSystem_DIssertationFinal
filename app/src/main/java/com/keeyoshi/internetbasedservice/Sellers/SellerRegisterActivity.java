package com.keeyoshi.internetbasedservice.Sellers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keeyoshi.internetbasedservice.Activity.MainActivity;
import com.keeyoshi.internetbasedservice.R;

import java.util.HashMap;

public class SellerRegisterActivity extends AppCompatActivity {

    private Button sellerLoginBegin, registerButton;
    private EditText nameInput, phoneInput, emailInput, passwordInput, addressInput;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_register);

        sellerLoginBegin=findViewById(R.id.staff_already_have_account);
        nameInput=findViewById(R.id.staff_name);
        phoneInput=findViewById(R.id.staff_phone);
        emailInput=findViewById(R.id.staff_email);
        passwordInput=findViewById(R.id.staff_password);
        addressInput=findViewById(R.id.staff_address);
        registerButton=findViewById(R.id.staff_register_btn);

        loadingBar = new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();

        sellerLoginBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SellerRegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                registerSeller();
            }
        });

    }

    private void registerSeller() {

        final String name=nameInput.getText().toString();
        final String phone=phoneInput.getText().toString();
        final String email=emailInput.getText().toString();
        final String password=passwordInput.getText().toString();
        final String address=addressInput.getText().toString();

        if(!name.equals("") && !phone.equals("") && !email.equals("") &&
                !password.equals("") && !address.equals("") ) {
            loadingBar.setTitle("Creating Staff Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            validateStaff(name, phone, email, password, address);
        }
    }

    private void validateStaff(final String name, final String phone, final String email,final String password,final String address) {
        final DatabaseReference RootRef;
        RootRef= FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Staffs").child(phone).exists())) {
                    HashMap<String, Object> staffDataMap = new HashMap<>();
                    staffDataMap.put("name", name);
                    staffDataMap.put("phone", phone);
                    staffDataMap.put("email", email);
                    staffDataMap.put("password", password);
                    staffDataMap.put("address", address);

                    RootRef.child("Staffs").child(phone).updateChildren(staffDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(SellerRegisterActivity.this, "Congratulations, your account has been created.", Toast.LENGTH_SHORT).show();
                                        loadingBar.dismiss();

                                        Intent intent = new Intent(SellerRegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        loadingBar.dismiss();
                                        Toast.makeText(SellerRegisterActivity.this, "Network Error: Please try again after some time...", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                
                else{
                    Toast.makeText(SellerRegisterActivity.this, "This " + phone + " already exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(SellerRegisterActivity.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(SellerRegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
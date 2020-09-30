package com.keeyoshi.internetbasedservice.Sellers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.keeyoshi.internetbasedservice.Activity.MainActivity;
import com.keeyoshi.internetbasedservice.Admin.AdminCategoryActivity;
import com.keeyoshi.internetbasedservice.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class StaffPageActivity extends AppCompatActivity {

    CircleImageView orderlist, staffDetails, stockOrder, staffList;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_page);

        orderlist=findViewById(R.id.staff_order);
        staffDetails=findViewById(R.id.staff_details);
        stockOrder=findViewById(R.id.staff_stock);
        staffList=findViewById(R.id.staff_evalutation);
        logout=findViewById(R.id.staff_logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(StaffPageActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        orderlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        staffList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        staffDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        stockOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StaffPageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
package com.keeyoshi.internetbasedservice.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.keeyoshi.internetbasedservice.Admin.AdminAddNewProductActivity;
import com.keeyoshi.internetbasedservice.Admin.AdminCategoryActivity;
import com.keeyoshi.internetbasedservice.Model.Products;
import com.keeyoshi.internetbasedservice.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    List<Products> productsList;

    private int[] mImages=new int[]{
            R.drawable.deal1,R.drawable.deal2,R.drawable.deal3,R.drawable.deal4
    };
    private String [] mImageTitle=new String[]{
            "Deal1","Deal2","Deal3","Deal3"
    };

    private ImageView Tshirt, Sport, Shoes, FemaleDress,Sweathers, Hats;
    private TextView txtTshirt, txtSport, txtShoes, txtFemaleDress,txtSweathers, txtHats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Tshirt=findViewById(R.id.t_shirts_C);
        Sport=findViewById(R.id.sports_t_shirts_C);
        Shoes=findViewById(R.id.shoes_C );
        FemaleDress=findViewById(R.id.female_dresses_C);
        Sweathers=findViewById(R.id.sweathers_C);
        Hats=findViewById(R.id.hats_C);

        txtTshirt=findViewById(R.id.txt_shirt);
        txtSport=findViewById(R.id.txt_sportShirt);
        txtShoes=findViewById(R.id.txt_shoes );
        txtFemaleDress=findViewById(R.id.txt_female);
        txtSweathers=findViewById(R.id.txt_sweathers);
        txtHats=findViewById(R.id.txt_hat);

        CarouselView carouselView;
        carouselView=findViewById(R.id.caral);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(CategoryActivity.this, "Deals Already Finish, Please wait Next time for new Offers", Toast.LENGTH_SHORT).show();
            }
        });

        Tshirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });

        Sport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "Sports tShirts");
                startActivity(intent);
            }
        });

        FemaleDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "Female Dresses");
                startActivity(intent);
            }
        });

        Sweathers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "Sweathers");
                startActivity(intent);
            }
        });

        Shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "Shoes");
                startActivity(intent);
            }
        });

        Hats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(CategoryActivity.this, CategoryListActivity.class);
                intent.putExtra("category", "Hats Caps");
                startActivity(intent);
            }
        });
    }
}
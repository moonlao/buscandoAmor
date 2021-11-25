package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.moonlao.buscandoamor.R;

import org.w3c.dom.Text;

import java.nio.FloatBuffer;

import model.Pet;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack,imgGenreView,imgPetScreen;
    private Button btnAdopt;
    private Pet currentPet;
    String pet;
    private TextView tvPetNameScreen,tvBreedScreen,tvPetAge,tvPetColor,tvDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        btnBack=findViewById(R.id.btnBackPet);
        btnAdopt=findViewById(R.id.btnAdopt);
        btnBack.setOnClickListener(this);
        btnAdopt.setOnClickListener(this);
        tvPetNameScreen= findViewById(R.id.tvPetNameScreen);
        tvBreedScreen = findViewById(R.id.tvBreedScreen);
        tvPetAge = findViewById(R.id.tvPetAge);
        tvPetColor= findViewById(R.id.tvPetColor);
        tvDescription = findViewById(R.id.tvDescription);
        imgGenreView = findViewById(R.id.imgGenreView);
        imgPetScreen = findViewById(R.id.imgPetScreen);
        Intent intent = getIntent();
        pet = intent.getStringExtra("pet");
        Log.e("TAG", pet );
        Gson gson = new Gson();
        currentPet = gson.fromJson(pet,Pet.class);
        
        Loadpet();



    }

    private void Loadpet() {

        tvPetAge.setText(currentPet.getAge());
        tvBreedScreen.setText(currentPet.getBreed());
        tvDescription.setText(currentPet.getDescription());
        tvPetColor.setText(currentPet.getColor());
        tvPetNameScreen.setText(currentPet.getName());
        Glide.with(this).load(currentPet.getImg()).into(imgPetScreen);



        switch (currentPet.getSex()){

            case "Male":

                imgGenreView.setImageResource(R.drawable.ic_male);
                imgGenreView.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));

                break;

            case "Female":

                imgGenreView.setImageResource(R.drawable.ic_female);
                imgGenreView.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
                break;
        }

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

             case R.id.btnBackPet:

                 finish();

                 break;

             case R.id.btnAdopt:
                 Intent intent = new Intent(this,FormActivity.class);
                 intent.putExtra("pet",pet);
                 startActivity(intent);
                 break;
         }
    }
}
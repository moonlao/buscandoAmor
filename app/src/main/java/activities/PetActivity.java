package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.moonlao.buscandoamor.R;

public class PetActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private Button btnAdopt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        btnBack=findViewById(R.id.btnBackPet);
        btnAdopt=findViewById(R.id.btnAdopt);
        btnBack.setOnClickListener(this);
        btnAdopt.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

             case R.id.btnBackPet:

                 finish();

                 break;

             case R.id.btnAdopt:
                 Intent intent = new Intent(this,FormActivity.class);
                 startActivity(intent);
                 break;
         }
    }
}
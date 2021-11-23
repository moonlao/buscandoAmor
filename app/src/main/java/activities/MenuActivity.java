package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.moonlao.buscandoamor.R;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView home,adoptRequest,logOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        home = findViewById(R.id.imgHome);
        adoptRequest=findViewById(R.id.imgAdoptions);
        logOut=findViewById(R.id.imgLogout);
        home.setOnClickListener(this);
        adoptRequest.setOnClickListener(this);
        logOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
         switch (v.getId()){

             case R.id.imgHome:

                 finish();
                 break;

             case R.id.imgAdoptions:

                  intent = new Intent(this,AdoptionRequestActivity.class);
                 startActivity(intent);
                 break;

             case R.id.imgLogout:


                 FirebaseAuth.getInstance().signOut();
                  intent= new Intent(this,LoginActivity.class);
                  startActivity(intent);

                 break;
         }
    }
}
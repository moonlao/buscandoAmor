package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.moonlao.buscandoamor.R;

import java.util.Date;
import java.util.UUID;

import model.AdoptionRequest;
import model.Pet;
import model.User;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private EditText etName,etEmail,etAddress,etReason;
    private Button btnSendForm;
    Pet currentPet;
    String pet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        btnBack=findViewById(R.id.btnBackForm);
        etName=findViewById(R.id.etNameForm);
        etEmail=findViewById(R.id.etEmailForm);
        etReason = findViewById(R.id.etAdoptionReason);
        etAddress = findViewById(R.id.etAddressForm);
        btnSendForm = findViewById(R.id.btnSendForm);
        btnBack.setOnClickListener(this);
        btnSendForm.setOnClickListener(this);

        Intent intent = getIntent();
        pet = intent.getStringExtra("pet");
        Gson gson = new Gson();
        currentPet = gson.fromJson(pet,Pet.class);
        LoadUserData();

    }

    private void LoadUserData() {

        FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                etEmail.setText(user.getEmail());
                etName.setText(user.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

             case R.id.btnBackForm:

                 finish();
                 break;

             case R.id.btnSendForm:


                SendForm();
                 break;
         }
    }

    private void SendForm() {

        if(etEmail.getText().toString().isEmpty()
                ||etName.getText().toString().isEmpty()||etReason.getText().toString().isEmpty()
                ||etAddress.getText().toString().isEmpty()){

            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
        }
        else{

            Gson gson = new Gson();
            Pet tempPet = gson.fromJson(pet,Pet.class);
            Date date;
            date = new Date();
            String requestId =UUID.randomUUID().toString();
            AdoptionRequest adoptionRequest = new AdoptionRequest(requestId,tempPet.getId(),etName.getText().toString(),etEmail.getText().toString(),etAddress.getText().toString(),etReason.getText().toString(),"esperando",date);

            FirebaseDatabase.getInstance().getReference("AdoptionRequest").child(FirebaseAuth.getInstance().getUid()).child(requestId).setValue(adoptionRequest).addOnCompleteListener(task->{

               if(task.isSuccessful()){

                   Toast.makeText(this, "petición de adopción enviada correctamente, espere a la respuesta de la fundación", Toast.LENGTH_SHORT).show();
                   Intent intent = new Intent(this,HomeActivity.class);
                   startActivity(intent);
                   finish();
               }
               else{

                   Toast.makeText(this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
               }
            });
        }
    }
}
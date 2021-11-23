package activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.moonlao.buscandoamor.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName,etEmail,etPassword,etPasswordRepeat;
    private Button btnRegister;
    private ImageView backRegister;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=findViewById(R.id.etNameRegister);
        etEmail=findViewById(R.id.etEmailRegister);
        etPassword=findViewById(R.id.etPasswordRegister);
        etPasswordRepeat= findViewById(R.id.etPasswordRegister2);
        btnRegister=findViewById(R.id.btnRegister);
        backRegister= findViewById(R.id.btnBackRegister);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();

        btnRegister.setOnClickListener(v->{
            if(etName.getText().toString().isEmpty()||etEmail.getText().toString().isEmpty()
                    ||etPassword.getText().toString().isEmpty()||etPasswordRepeat.getText().toString().isEmpty()){

                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{

                if(etPassword.getText().toString().contentEquals(etPasswordRepeat.getText().toString())){

                    Register();


                }
                else{
                    Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
            }

        });
        backRegister.setOnClickListener(v->{

            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void Register() {

        auth.createUserWithEmailAndPassword(etEmail.getText().toString(),etPassword.getText().toString()).addOnCompleteListener(task->{

           if(task.isSuccessful()){

               //se registra
            

           }
        });
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
        finish();

    }


}
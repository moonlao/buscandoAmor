package activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moonlao.buscandoamor.R;

public class LoginActivity extends AppCompatActivity  {

    private EditText etEmail,etPassword;
    private ImageButton btnLogin;
    private TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail= findViewById(R.id.etEmailLogin);
        etPassword= findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister=findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v->{

            if(etEmail.getText().toString().isEmpty()||etPassword.getText().toString().isEmpty()){


                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{

                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                finish();

            }

        });

        tvRegister.setOnClickListener(v->{

            Intent intent = new Intent(this,RegisterActivity.class);
            startActivity(intent);
            finish();

        });
    }
}
package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.moonlao.buscandoamor.R;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView btnBack;
    private EditText etName,etEmail,etAddress,etReason;
    private Button btnSendForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        btnBack=findViewById(R.id.btnBackForm);
        etName=findViewById(R.id.etNameForm);
        etEmail=findViewById(R.id.etEmailForm);
        etReason = findViewById(R.id.etAdoptionReason);
        btnSendForm = findViewById(R.id.btnSendForm);
        btnBack.setOnClickListener(this);
        btnSendForm.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){

             case R.id.btnBackForm:

                 finish();
                 break;

             case R.id.btnSendForm:

                 // TODO: 22/11/2021  enviar formulario
    
                 break;
         }
    }
}
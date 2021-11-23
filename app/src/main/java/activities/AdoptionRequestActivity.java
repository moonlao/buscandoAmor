package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.moonlao.buscandoamor.R;

public class AdoptionRequestActivity extends AppCompatActivity {

    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_request);

        btnBack = findViewById(R.id.btnBackAdoptions);

        btnBack.setOnClickListener(v -> finish());
    }
}
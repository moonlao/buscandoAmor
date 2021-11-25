package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moonlao.buscandoamor.R;

import java.util.ArrayList;

import adapters.AdoptionsAdapter;
import model.AdoptionRequest;

public class AdoptionRequestActivity extends AppCompatActivity {

    private ImageView btnBack;
    RecyclerView rvAdoptionRequest;
    AdoptionsAdapter adoptionsAdapter;
    ArrayList<AdoptionRequest> adoptionRequestArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_request);
        btnBack = findViewById(R.id.btnBackAdoptions);
        adoptionRequestArrayList = new ArrayList<>();
        rvAdoptionRequest = findViewById(R.id.rvAdoptionRequest);
        adoptionsAdapter = new AdoptionsAdapter();
        rvAdoptionRequest = findViewById(R.id.rvAdoptionRequest);
        rvAdoptionRequest.setAdapter(adoptionsAdapter);
        rvAdoptionRequest.setLayoutManager(new LinearLayoutManager(this));
        
        LoadAdoptions();
        btnBack.setOnClickListener(v -> finish());
    }

    private void LoadAdoptions() {

        FirebaseDatabase.getInstance().getReference("AdoptionRequest").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Log.e("TAG", String.valueOf(snapshot.exists()) );
                        adoptionsAdapter.clear();
                        for (DataSnapshot child:snapshot.getChildren()
                             ) {
                            AdoptionRequest tempRequest = child.getValue(AdoptionRequest.class);
                            adoptionsAdapter.addNewAdoption(tempRequest);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

    }
}
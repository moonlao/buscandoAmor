package activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moonlao.buscandoamor.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import adapters.PetsAdapter;
import model.Pet;
import model.User;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private ImageButton btnDog, btnCat;
    private RecyclerView rvHome;
    private TextView tvDog, tvCat;
    private ImageView imgMenu;
    private PetsAdapter adapter;
    private FirebaseDatabase db;
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvName = findViewById(R.id.tvNameHome);
        btnCat = findViewById(R.id.btnCat);
        btnDog = findViewById(R.id.btnDog);
        rvHome = findViewById(R.id.rvHome);
        tvDog = findViewById(R.id.tvDog);
        tvCat = findViewById(R.id.tvCat);
        imgMenu = findViewById(R.id.imgMenu);
        btnDog.setOnClickListener(this);
        btnCat.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        db = FirebaseDatabase.getInstance();
        dbRef=FirebaseDatabase.getInstance().getReference();
        adapter = new PetsAdapter();
        rvHome.setAdapter(adapter);
        rvHome.setLayoutManager(new LinearLayoutManager(this));
        LoadName();

        SetDogs();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.btnCat:

                SetCats();

                break;

            case R.id.btnDog:

                SetDogs();
                break;

            case R.id.imgMenu:

                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
                break;

        }
    }



    private void LoadName() {

        String uid = FirebaseAuth.getInstance().getUid();

        FirebaseDatabase.getInstance().getReference("Users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User tempUser = snapshot.getValue(User.class);

                tvName.setText(tempUser.getName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void SetDogs() {

        db.getReference().child("Pets").orderByChild("type").equalTo("dog").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                adapter.clear();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Pet tempPet = child.getValue(Pet.class);
                    adapter.addNewPet(tempPet);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6727AF")));
        btnDog.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        tvDog.setTextColor(Color.parseColor("#6727AF"));

        btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        btnCat.setImageTintList(ColorStateList.valueOf(Color.parseColor("#818181")));
        tvCat.setTextColor(Color.parseColor("#818181"));

    }

    private void SetCats() {

        db.getReference().child("Pets").orderByChild("type").equalTo("cat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                adapter.clear();

                for (DataSnapshot child : snapshot.getChildren()) {
                    Pet tempPet = child.getValue(Pet.class);
                    adapter.addNewPet(tempPet);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6727AF")));
        btnCat.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        tvCat.setTextColor(Color.parseColor("#6727AF"));

        btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        btnDog.setImageTintList(ColorStateList.valueOf(Color.parseColor("#818181")));
        tvDog.setTextColor(Color.parseColor("#818181"));
    }
}
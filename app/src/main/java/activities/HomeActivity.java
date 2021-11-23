package activities;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.moonlao.buscandoamor.R;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvName;
    private ImageButton btnDog,btnCat;
    private RecyclerView rvHome;
    private TextView tvDog,tvCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tvName=findViewById(R.id.tvNameHome);
        btnCat=findViewById(R.id.btnCat);
        btnDog=findViewById(R.id.btnDog);
        rvHome=findViewById(R.id.rvHome);
        tvDog=findViewById(R.id.tvDog);
        tvCat=findViewById(R.id.tvCat);
        btnDog.setOnClickListener(this);
        btnCat.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.btnCat:

                SetCats();

                break;

            case R.id.btnDog:

                SetDogs();
                break;

        }
    }

    private void SetDogs(){

        btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6727AF")));
        btnDog.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        tvDog.setTextColor(Color.parseColor("#6727AF"));

        btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        btnCat.setImageTintList(ColorStateList.valueOf(Color.parseColor("#818181")));
        tvCat.setTextColor(Color.parseColor("#818181"));

    }

    private void SetCats(){

        btnCat.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#6727AF")));
        btnCat.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        tvCat.setTextColor(Color.parseColor("#6727AF"));

        btnDog.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ffffff")));
        btnDog.setImageTintList(ColorStateList.valueOf(Color.parseColor("#818181")));
        tvDog.setTextColor(Color.parseColor("#818181"));
    }
}
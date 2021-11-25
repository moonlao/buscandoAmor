package adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moonlao.buscandoamor.R;

import java.util.ArrayList;

import model.AdoptionRequest;
import model.Pet;

public class AdoptionsAdapter extends RecyclerView.Adapter<AdoptionsAdapter.AdoptionViewHolder> {

    ArrayList<AdoptionRequest> adoptionList;
    private ViewGroup group;


    public AdoptionsAdapter(){

        adoptionList = new ArrayList<>();


    }


    public void addNewAdoption(AdoptionRequest newAdoption){
        adoptionList.add(newAdoption);
        notifyDataSetChanged();
    }
    public void clear(){

        adoptionList.clear();
    }


    @NonNull
    @Override
    public AdoptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        group=parent;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pet_adoption_card,parent,false);


        return new AdoptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdoptionViewHolder holder, int position) {

        //carga los datos del perro con el id;

        // TODO: 25/11/2021  id de firebase debe ser el mismo que el del objeto mascota 
        FirebaseDatabase.getInstance().getReference("Pets").child("asdasdasda").addValueEventListener(

                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Pet pet = snapshot.getValue(Pet.class);

                        //verifica que el animal existe ( por si las moscas)
                        Log.e("TAG", pet.getAge() );
                        if(pet!=null){

                            holder.petName.setText(pet.getName());
                            holder.petBreed.setText(pet.getBreed());
                            holder.tvAdoptionStatus.setText(adoptionList.get(holder.getAdapterPosition()).getStatus());

                            switch (pet.getSex()){

                                case "Male":

                                    holder.imgGenre.setImageResource(R.drawable.ic_male);
                                    break;

                                case "Female":
                                    holder.imgGenre.setImageResource(R.drawable.ic_female);
                                    break;
                            }
                            Glide.with(group.getContext()).load(pet.getImg()).into(holder.petImg);
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );






    }

    @Override
    public int getItemCount() {
        return adoptionList.size();
    }



    public class AdoptionViewHolder extends RecyclerView.ViewHolder{


        TextView petName,petBreed,tvAdoptionStatus;
        ImageView imgGenre,petImg;
        public AdoptionViewHolder(@NonNull View itemView) {
            super(itemView);


            petName= itemView.findViewById(R.id.tvPetName);
            petBreed = itemView.findViewById(R.id.tvBreed);
            imgGenre = itemView.findViewById(R.id.imgGenre);
            petImg = itemView.findViewById(R.id.imgPet);
            tvAdoptionStatus = itemView.findViewById(R.id.tvAdoptionStatus);

        }
    }
}

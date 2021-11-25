package adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.moonlao.buscandoamor.R;

import java.util.ArrayList;

import activities.PetActivity;
import model.Pet;

public class PetsAdapter extends RecyclerView.Adapter<PetsAdapter.PetViewHolder> {

    ArrayList<Pet> petsList;
    private ViewGroup group;


    public PetsAdapter(){

        petsList= new ArrayList<>();


    }


    public void addNewPet(Pet newPet){
        petsList.add(newPet);
        notifyDataSetChanged();
    }
    public void clear(){

        petsList.clear();
    }


    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        group=parent;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
       View view = layoutInflater.inflate(R.layout.pet_card,parent,false);


        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {

        holder.petName.setText(petsList.get(position).getName());
        holder.petAge.setText(petsList.get(position).getAge());
        holder.petBreed.setText(petsList.get(position).getBreed());
        holder.petCharacteristic.setText(petsList.get(position).getCharacteristic());


        switch (petsList.get(position).getSex()){

            case "Male":

                holder.imgGenre.setImageResource(R.drawable.ic_male);
                break;

            case "Female":
                holder.imgGenre.setImageResource(R.drawable.ic_female);
                break;
        }
        Glide.with(group.getContext()).load(petsList.get(position).getImg()).into(holder.petImg);

        holder.root.setOnClickListener(v->{

            Intent intent = new Intent(group.getContext(), PetActivity.class);
            Gson gson = new Gson();
            String pet = gson.toJson(petsList.get(position));
            intent.putExtra("pet",pet);
            group.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return petsList.size();
    }



    public class PetViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout root;
        TextView petName,petBreed,petAge,petCharacteristic;
        ImageView imgGenre,petImg;
        public PetViewHolder(@NonNull View itemView) {
            super(itemView);

            root = itemView.findViewById(R.id.petCardRoot);
            petName= itemView.findViewById(R.id.tvPetName);
            petBreed = itemView.findViewById(R.id.tvBreed);
            petAge = itemView.findViewById(R.id.tvAge);
            petCharacteristic = itemView.findViewById(R.id.a);
            imgGenre = itemView.findViewById(R.id.imgGenre);
            petImg = itemView.findViewById(R.id.imgPet);
        }
    }
}

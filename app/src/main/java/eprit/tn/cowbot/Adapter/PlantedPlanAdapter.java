package eprit.tn.cowbot.Adapter;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Entity.PlantedPlant;
import eprit.tn.cowbot.R;


public class PlantedPlanAdapter extends RecyclerView.Adapter<PlantedPlanAdapter.plantedPlantRecycleViewHolder> {

    private List<PlantedPlant> plantedPlantRecycleViewList;



    public class plantedPlantRecycleViewHolder extends RecyclerView.ViewHolder {
        TextView libelle,position,dateplantation,datefinal,progresstext,age;
        ProgressBar circularProgressBar;




        public plantedPlantRecycleViewHolder(View row) {
            super(row);
            libelle=(TextView)row.findViewById(R.id.libelle);
            age=(TextView)row.findViewById(R.id.age);
            position=(TextView)row.findViewById(R.id.position);
            dateplantation=(TextView)row.findViewById(R.id.dateplantation);
            datefinal=(TextView)row.findViewById(R.id.datefinal);
            progresstext=(TextView)row.findViewById(R.id.progresstext);
            circularProgressBar= (ProgressBar)row.findViewById(R.id.circularProgressBar);
        }
    }

    public PlantedPlanAdapter(List<PlantedPlant> plantedPlantRecycleViewList) {
        this.plantedPlantRecycleViewList = plantedPlantRecycleViewList;
    }

    @Override
    public plantedPlantRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemprogress, parent, false);
        return new plantedPlantRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(plantedPlantRecycleViewHolder holder, final int position) {
        final PlantedPlant plantedPlant = plantedPlantRecycleViewList.get(position);
        holder.libelle.setText(plantedPlant.getPlant().getLibelle());
        holder.age.setText(plantedPlant.getPlant().getAge()+"");
        holder.position.setText(plantedPlant.getPosition());
        holder.datefinal.setText(plantedPlant.getDate_final());
        holder.dateplantation.setText(plantedPlant.getDate_plantation());
holder.progresstext.setText("1%");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.circularProgressBar.setProgress(1, true);
        }else  holder.circularProgressBar.setProgress(1);


    }

    @Override
    public int getItemCount() {
        return plantedPlantRecycleViewList.size();
    }
}



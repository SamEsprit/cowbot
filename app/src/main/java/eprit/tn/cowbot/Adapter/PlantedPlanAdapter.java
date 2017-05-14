package eprit.tn.cowbot.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;

import java.util.List;

import eprit.tn.cowbot.Entity.Planted.PlantedInput;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.URLS;


public class PlantedPlanAdapter extends RecyclerView.Adapter<PlantedPlanAdapter.plantedPlantRecycleViewHolder> {

    private List<PlantedInput> plantedPlantRecycleViewList;
    Context context;


    public class plantedPlantRecycleViewHolder extends RecyclerView.ViewHolder {
        TextView libelle, position, dateplantation, datefinal, progresstext, age;
        ImageView PlantImg;
        ProgressBar circularProgressBar;

        public plantedPlantRecycleViewHolder(View row) {
            super(row);
            libelle = (TextView) row.findViewById(R.id.libelle);
            PlantImg = (ImageView) row.findViewById(R.id.PlantImg);
            age = (TextView) row.findViewById(R.id.age);
            position = (TextView) row.findViewById(R.id.position);
            dateplantation = (TextView) row.findViewById(R.id.dateplantation);
            datefinal = (TextView) row.findViewById(R.id.datefinal);
            progresstext = (TextView) row.findViewById(R.id.progresstext);
            circularProgressBar = (ProgressBar) row.findViewById(R.id.circularProgressBar);


            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MaterialDialog.Builder(context)
                            .content(plantedPlantRecycleViewList.get(getPosition()).getDescription())
                            .title("Cowbot")
                            .contentGravity(GravityEnum.CENTER)
                            .titleGravity(GravityEnum.CENTER)
                            .neutralText("OK")
                            .onNeutral(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }
            });
        }
    }

    public PlantedPlanAdapter(List<PlantedInput> plantedPlantRecycleViewList, Context context) {
        this.context = context;
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
        final PlantedInput plantedPlant = plantedPlantRecycleViewList.get(position);
        holder.libelle.setText(plantedPlant.getLibelle());
        holder.age.setText(plantedPlant.getAge() + "");
        holder.position.setText(plantedPlant.getPivotPlanted().getX() + "---" + plantedPlant.getPivotPlanted().getY());
        holder.datefinal.setText(String.valueOf(plantedPlant.getDayPassed()));
        holder.dateplantation.setText(plantedPlant.getPivotPlanted().getUpdated_at().substring(0, 10));
        holder.progresstext.setText(String.valueOf(plantedPlant.getPouratage().intValue()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.circularProgressBar.setProgress(plantedPlant.getPouratage().intValue(), true);
        } else holder.circularProgressBar.setProgress(plantedPlant.getPouratage().intValue());
        Glide.with(context).load(URLS.Image + plantedPlant.getImage()).fitCenter().into(holder.PlantImg);

    }

    @Override
    public int getItemCount() {
        return plantedPlantRecycleViewList.size();
    }
}



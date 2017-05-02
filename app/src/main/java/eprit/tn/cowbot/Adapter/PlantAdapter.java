package eprit.tn.cowbot.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Entity.Plant.Plant;
import eprit.tn.cowbot.R;


public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantRecycleViewHolder> {

    private List<Plant> PlantRecycleViewList;
    public class PlantRecycleViewHolder extends RecyclerView.ViewHolder {
        TextView libelle;
        ImageView PlantImg;



        public PlantRecycleViewHolder(View row) {
            super(row);
            libelle=(TextView)row.findViewById(R.id.libelle);
            PlantImg=(ImageView)row.findViewById(R.id.PlantImg);
        }
    }

    public PlantAdapter(List<Plant> PlantRecycleViewList) {
        this.PlantRecycleViewList = PlantRecycleViewList;
    }

    @Override
    public PlantRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plant, parent, false);
        return new PlantRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlantRecycleViewHolder holder, final int position) {
        final Plant Plant = PlantRecycleViewList.get(position);
        holder.libelle.setText(Plant.getLibelle());
    }

    @Override
    public int getItemCount() {
        return PlantRecycleViewList.size();
    }
}



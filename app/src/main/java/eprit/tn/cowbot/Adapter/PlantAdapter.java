package eprit.tn.cowbot.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import eprit.tn.cowbot.Entity.Seeds.SeedsInput;
import eprit.tn.cowbot.R;
import eprit.tn.cowbot.Utils.URLS;


public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.PlantRecycleViewHolder> {

    private List<SeedsInput> PlantRecycleViewList;
    Context context;
    public class PlantRecycleViewHolder extends RecyclerView.ViewHolder {
        TextView libelle;
        ImageView PlantImg;



        public PlantRecycleViewHolder(View row) {
            super(row);
            libelle=(TextView)row.findViewById(R.id.libelle);
            PlantImg=(ImageView)row.findViewById(R.id.PlantImg);

        }
    }

    public PlantAdapter(List<SeedsInput> PlantRecycleViewList ,Context context) {
        this.PlantRecycleViewList = PlantRecycleViewList;
        this.context=context;
    }

    @Override
    public PlantRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plant, parent, false);
        return new PlantRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlantRecycleViewHolder holder, final int position) {
        final SeedsInput Plant = PlantRecycleViewList.get(position);
        holder.libelle.setText(Plant.getLibelle());
        Glide.with(context).load(URLS.Image+Plant.getImage()).into(holder.PlantImg);
    }

    @Override
    public int getItemCount() {
        return PlantRecycleViewList.size();
    }


}



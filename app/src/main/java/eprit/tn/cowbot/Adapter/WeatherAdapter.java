package eprit.tn.cowbot.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import eprit.tn.cowbot.Entity.Weather;
import eprit.tn.cowbot.R;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ItemRecycleViewHolder>  {

    private List<Weather> weatherList;
    Context context ;


    public class ItemRecycleViewHolder extends RecyclerView.ViewHolder  {
        public ImageView ResId;
        public TextView titre,value;
        public ImageView backgrounds;

        public ItemRecycleViewHolder(View view) {
            super(view);
            titre = (TextView) view.findViewById(R.id.titre);
            value = (TextView) view.findViewById(R.id.value);
            ResId=(ImageView)view.findViewById(R.id.res);
            backgrounds=(ImageView)view.findViewById(R.id.background);
        }

    }


    public WeatherAdapter(List<Weather> ItemRecycleViewList,Context context) {
        this.weatherList = ItemRecycleViewList;
        this.context=context;
    }

    @Override
    public ItemRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_weather, parent, false);

        return new ItemRecycleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemRecycleViewHolder holder, int position) {

        /*switch (position)

        {
            case 0:
                Ion.with(holder.backgrounds).fitXY().load("http://bestanimations.com/Nature/Water/rain/beautiful-rain-animated-gif-9.gif");
                break;
            case 3:

                Ion.with(holder.backgrounds).fitXY().load("http://bestanimations.com/Nature/Water/rain/rain-nature-animated-gif-4.gif");

                break;
        }*/
        holder.backgrounds.setVisibility(View.GONE);
        Weather weather = weatherList.get(position);
        holder.titre.setText(weather.getTitre());
        holder.value.setText(weather.getValue());
        holder.ResId.setImageDrawable(context.getDrawable(weather.getResId()));
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}

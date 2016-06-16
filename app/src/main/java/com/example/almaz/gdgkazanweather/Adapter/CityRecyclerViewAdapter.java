package com.example.almaz.gdgkazanweather.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.almaz.gdgkazanweather.ForecastActivity;
import com.example.almaz.gdgkazanweather.R;

import java.util.List;

/**
 * Created by almaz on 15.06.2016.
 */
public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityRecyclerViewAdapter.ViewHolder> {

    private List<String> records;
    public Context mContext;

    public CityRecyclerViewAdapter(Context context, List<String> records){
        this.records = records;
        mContext=context;
    }

    @Override
    public CityRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcv_city_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityRecyclerViewAdapter.ViewHolder holder, final int position) {
        final TextView mTextView = holder.mCityTextView;
        mTextView.setText(records.get(position));
        holder.mCityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ForecastActivity.class);
                i.putExtra(ForecastActivity.EXTRA_CITY_NAME, records.get(position));
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mCityTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mContext=itemView.getContext();
            mCityTextView = (TextView) itemView.findViewById(R.id.rcv_city_item);

        }
    }
}

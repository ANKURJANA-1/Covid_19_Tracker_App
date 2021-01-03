package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<CountryModel> countryModels;
    private Context context;

    public MyAdapter(ArrayList<CountryModel> countryModels, Context context) {
        this.countryModels = countryModels;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.setData(countryModels.get(position));
    }

    @Override
    public int getItemCount() {
        return countryModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageflag;
        private TextView countryname;
        private CardView invisiblePart;
        private TextView casesCount;
        private TextView casesRecorvered;
        private TextView casesCritical;
        private TextView casesActive;
        private TextView casesTotalCases;
        private TextView casesTotalDeaths;
        private TextView casesTodayDeaths;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageflag=itemView.findViewById(R.id.Flagimage);
            countryname=itemView.findViewById(R.id.countryName);

            invisiblePart = itemView.findViewById(R.id.invisible_part);
            casesCount = itemView. findViewById(R.id.cases_count);
            casesRecorvered = itemView.findViewById(R.id.cases_recorvered);
            casesCritical = itemView.findViewById(R.id.cases_critical);
            casesActive = itemView. findViewById(R.id.cases_active);
            casesTotalCases = itemView.findViewById(R.id.cases_total_cases);
            casesTotalDeaths = itemView.findViewById(R.id.cases_total_deaths);
            casesTodayDeaths = itemView.findViewById(R.id.cases_today_deaths);

        }
        public void setData(CountryModel countryModel)
        {
            Glide.with(context).load(countryModel.getFlagImageUrl()).into(imageflag);
            countryname.setText(countryModel.getCountry());


            casesCount.setText(countryModel.getCases());
            casesRecorvered.setText(countryModel.getRecovered());
            casesCritical.setText(countryModel.getCritical());
            casesActive.setText(countryModel.getActive());
            casesTotalCases.setText(countryModel.getTodayCases());
            casesTotalDeaths.setText(countryModel.getTotalDeaths());
            casesTodayDeaths.setText(countryModel.getTotalDeaths());

            countryname.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(countryModel.getCollaspe()==true)
                    {
                        invisiblePart.setVisibility(View.VISIBLE);
                        countryModel.setCollaspe(false);
                    }
                }
            });

        }
    }
}

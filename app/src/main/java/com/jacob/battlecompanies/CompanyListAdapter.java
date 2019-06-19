package com.jacob.battlecompanies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CompanyListAdapter  extends ArrayAdapter {

    private final Activity context;

    private final List<Company> comps;

    public CompanyListAdapter(Activity context, List<Company> comps){

        super(context,R.layout.companies_list , comps);
        this.context=context;
        this.comps = comps;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.companies_list, null,true);

        Company comp = comps.get(position);

        TextView id = rowView.findViewById(R.id.id);
        id.setText(String.valueOf(comp.getCompId()));

        TextView nom = rowView.findViewById(R.id.compNom);
        nom.setText(comp.getCompNom());

        TextView army = rowView.findViewById(R.id.army);
        army.setText(comp.getTipus());

        TextView compCost = rowView.findViewById(R.id.compCost);
        compCost.setText(String.valueOf(comp.getCompCost()));

        TextView date = rowView.findViewById(R.id.date);
        date.setText(comp.getDate());

        String logo;
        if (comp.getLogo()!= null){
            logo = comp.getLogo();
        }else{
            logo = "logo";
        }

        ImageView logoV = rowView.findViewById(R.id.logo);
        logoV.setImageResource(context.getResources().getIdentifier(logo,"drawable",context.getPackageName()));

        return rowView;
    }
}

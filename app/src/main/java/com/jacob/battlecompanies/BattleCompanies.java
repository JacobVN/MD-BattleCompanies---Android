package com.jacob.battlecompanies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class BattleCompanies extends AppCompatActivity {

    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_companies);
        lv = (ListView) findViewById(R.id.list);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String cid = ((TextView) view.findViewById(R.id.id)).getText().toString();
                Intent in=new Intent(getApplicationContext(),CompanyActivity.class);
                in.putExtra("COMPID", cid);
                startActivity(in);
            }
        });
        Button btNou=(Button) findViewById(R.id.nouBtn);
        btNou.setOnClickListener(
                // Cridam l'activity d'edició indicant que es un insert (clau primària en blanc per exemple)
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getApplicationContext(),CompanyActivity.class);
                        in.putExtra("COMPID", "0");
                        startActivity(in);
                    }
                }
        );
        mostraComps(); // Carrega la llista
    }

    @Override
    public void onResume() {
        super.onResume();
        mostraComps();
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    public void mostraComps() {
        DataSourceComps bd;
        bd = new DataSourceComps(this);
        bd.open();
        List<Company> llistaComps = bd.getAllComps();

        bd.close();
        CompanyListAdapter whatever = new CompanyListAdapter(this, llistaComps);
        lv.setAdapter(whatever);
    }
}

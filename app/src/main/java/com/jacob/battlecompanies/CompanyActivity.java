package com.jacob.battlecompanies;

import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CompanyActivity extends AppCompatActivity {
    DataSourceComps bd = new DataSourceComps(this);

    private ListAdapter adapter;
    public ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        lv = (ListView) findViewById(R.id.listCrew);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String charid = ((TextView) view.findViewById(R.id.id)).getText().toString();
                Intent in=new Intent(getApplicationContext(),CharacterActivity.class);
                in.putExtra("CHARID", charid);
                startActivity(in);
            }
        });
        Bundle bundle = getIntent().getExtras();
        final String compId = bundle.getString("COMPID");
        Button recruit=(Button) findViewById(R.id.recruit);
        Button saveComp = (Button) findViewById(R.id.saveComp);
        Button deleteComp = (Button) findViewById(R.id.deleteComp);
        recruit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in=new Intent(getApplicationContext(),CharacterActivity.class);
                        in.putExtra("CHARID", "0");
                        in.putExtra("COMPID", compId);
                        startActivity(in);
                    }
                }
        );
        {
            TextView idc = (TextView) findViewById(R.id.idComp);
            if (!compId.equals("0")){
                idc.setText(compId);
                bd.open();
                Company comp = bd.getCompany(Integer.parseInt(compId));
                emplenaDades(comp);
                bd.close();
            }else{
                idc.setText(compId);
                deleteComp.setVisibility(View.INVISIBLE);
                recruit.setVisibility(View.INVISIBLE);
                (findViewById(R.id.compNom)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.army)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.editCompNom)).setVisibility(View.VISIBLE);
                (findViewById(R.id.spinnerArmy)).setVisibility(View.VISIBLE);
                spinnerArmy();
            }
        }
        saveComp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = ((TextView)findViewById(R.id.idComp)).getText().toString();
                if (!id.equals("0")){
                    updateComp();
                } else {
                    insertComp();
                }
            }
        });
        deleteComp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = ((TextView)findViewById(R.id.idComp)).getText().toString();
                if (!id.equals("0")){
                    deleteComp();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        String id = ((TextView)findViewById(R.id.idComp)).getText().toString();
        if (!id.equals("0")){
            bd.open();
            Company comp = bd.getCompany(Integer.parseInt(id));
            bd.close();
            emplenaDades(comp);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void emplenaDades(Company comp){
        ((TextView) findViewById(R.id.compNom)).setText(comp.getCompNom());
        ((TextView) findViewById(R.id.idComp)).setText(Long.toString(comp.getCompId()));
        ((TextView) findViewById(R.id.infl)).setText(Long.toString(comp.getInfl()));
        ((TextView) findViewById(R.id.army)).setText(comp.getTipus());
        ((TextView) findViewById(R.id.desc)).setText(comp.getCompDesc());
        //((TextView) findViewById(R.id.date)).setText(comp.getDate());
        String logo;
        if (comp.getLogo()!= null){
            logo = comp.getLogo();
        }else{
            logo = "logo";
        }
        setLogo(logo);

        long[] costs = getMembres(comp.getCompId());

        ((TextView) findViewById(R.id.compCost)).setText(Long.toString(costs[0]));
        ((TextView) findViewById(R.id.costAct)).setText(Long.toString(costs[1]));
    }
    private long[] getMembres(long id){
            long[] costs = new long[2];
            costs[0] = 0;
            costs[1] = 0;
            DataSourceComps bd;
            bd = new DataSourceComps(this);
            bd.open();
            // Obtenim tots els personatges
            List<Character> llistaChars = bd.getCharsComp(id);
            ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
            for (int i = 0; i < llistaChars.size(); i++) {
                HashMap<String, String> map = new HashMap<String, String>();
                Character membre = llistaChars.get(i);
                map.put("id", String.valueOf(membre.getIdChar()));
                map.put("nom", membre.getNomchar());
                map.put("raza", membre.getRazachar());
                map.put("rang", membre.getRang());
                map.put("cost", String.valueOf(membre.getCost()));
                map.put("bg", String.valueOf(membre.isRest()));
                if (!membre.isRest()){
                    costs[1] += membre.getCost();
                }
                costs[0] += membre.getCost();
                map.put("cost", String.valueOf(membre.getCost()));
                map.put("c", String.valueOf(membre.getC())+"/"+String.valueOf(membre.getCd()));
                map.put("a", String.valueOf(membre.getA()));
                map.put("h", String.valueOf(membre.getH()));
                map.put("f", String.valueOf(membre.getF()));
                map.put("d", String.valueOf(membre.getD()));
                map.put("v", String.valueOf(membre.getV()));
                //if (membre.isHero()) {
                    map.put("pdr", String.valueOf(membre.getPdr()));
                    map.put("vlt", String.valueOf(membre.getVlt()));
                    map.put("dst", String.valueOf(membre.getDst()));
                //}
                //map.put("img", comp.getImg());
                llista.add(map);
            }
            //Tanquem la BD
            bd.close();
            //Assignar a la listview
            adapter = new SimpleAdapter(this, llista,R.layout.character_list,
                    //new String[]{"id","nom","raza","rang","cost","img"},
                    new String[]{"id","nom","raza","rang","cost","c","a","h","f","d","v","pdr","vlt","dst"},
                    new int[]{R.id.id,R.id.nom,R.id.razaView,R.id.rang,R.id.cost,R.id.C,R.id.A,R.id.H,R.id.F,R.id.D,R.id.V,R.id.pdr,R.id.vlt,R.id.dst});
            lv.setAdapter(adapter);
            return costs;
        }
    private long[] mostraMembres(long id){
        long[] costs = new long[2];
        costs[0] = 0;
        costs[1] = 0;
        DataSourceComps bd;
        bd = new DataSourceComps(this);
        bd.open();
        // Obtenim tots els personatges
        List<Character> llistaChars = bd.getCharsComp(id);
        bd.close();

        CharacterListAdapter whatever = new CharacterListAdapter(this, llistaChars);
        lv.setAdapter(whatever);
        return costs;
    }
    private void spinnerArmy() {
        final Spinner spinnerTipus = (Spinner) findViewById(R.id.spinnerArmy);
        DataSourceComps bd;
        bd = new DataSourceComps(this);
        bd.open();
        List<String> llista;
        llista=bd.getAllArmies();
        // Crear adapter
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<String>(this,R.layout.spinner_item, llista);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // assignar adapter
        spinnerTipus.setAdapter(dataAdapter);
        bd.close();
        spinnerTipus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String logo = getLogoArmy(spinnerTipus.getSelectedItem().toString());
                setLogo(logo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });
    }
    private void updateComp(){
        long compId = Long.parseLong(((TextView) findViewById(R.id.idComp)).getText().toString());
        String nomComp = ((TextView) findViewById(R.id.compNom)).getText().toString();
        long cost = Long.parseLong(((TextView) findViewById(R.id.compCost)).getText().toString());
        long inf = Long.parseLong(((EditText) findViewById(R.id.infl)).getText().toString());
        String army = ((TextView) findViewById(R.id.army)).getText().toString();
        String desc = ((EditText) findViewById(R.id.desc)).getText().toString();
        String logo = getLogoArmy(army);
        String date = currentDate();

        bd.open();
        Company comp = new Company();
        comp.setCompId(compId);
        comp.setCompNom(nomComp);
        comp.setCompCost(cost);
        comp.setInfl(inf);
        comp.setTipus(army);
        comp.setCompDesc(desc);
        comp.setLogo(logo);
        comp.setDate(date);

        bd.updateCompany(comp);
        bd.close();

        finish();
    }
    private void insertComp(){
        String nomComp = ((EditText) findViewById(R.id.editCompNom)).getText().toString();
        long cost = Long.parseLong(((TextView) findViewById(R.id.compCost)).getText().toString());
        long inf = Long.parseLong(((EditText) findViewById(R.id.infl)).getText().toString());
        String army = ((Spinner) findViewById(R.id.spinnerArmy)).getSelectedItem().toString();
        String desc = ((EditText) findViewById(R.id.desc)).getText().toString();
        String logo = getLogoArmy(army);
        String date = currentDate();

        bd.open();
        Company comp = new Company();
        comp.setCompNom(nomComp);
        comp.setCompCost(cost);
        comp.setInfl(inf);
        comp.setTipus(army);
        comp.setCompDesc(desc);
        comp.setLogo(logo);
        comp.setDate(date);

        bd.createCompany(comp);
        bd.close();
        long idComp = comp.getCompId();
        //if (idComp){//msg creada con exito }

            finish();

    }
    private String getLogoArmy(String army){
        String logo;
        switch(army) {
            case "Minas Tirith":
            case "Osgiliath":
            case "Feudos":
                logo = "gondor";
                break;
            case "Rohan":
                logo = "rohan";
                break;
            case "Arnor":
            case "Montaraces del Norte":
            case "Hombres del Oeste":
                logo = "arnor";
                break;
            case "Ciudad del Lago":
                logo = "dale";
                break;
            case "Erebor y Ciudad de Valle":
                logo = "erebor_dale";
                break;
            case "La Última Alianza":
                logo = "ua";
                break;
            case "Rivendell":
                logo = "rivendell";
                break;
            case "Lothlórien":
                logo = "lothlorien";
                break;
            case "Salones de Thranduil":
                logo = "woodlandrealms";
                break;
            case "Colinas de Hierro":
                logo = "ironhills";
                break;
            case "Pueblo de Durin":
            case "Expedicinarios de Moria":
                logo = "erebor";
                break;
            case "La Comarca":
            case "Caminantes Infatigables":
                logo = "shire";
                break;
            case "Mordor":
            case "Cirith Ungol":
            case "Minas Morgul":
                logo = "mordor";
                break;
            case "Isengard":
            case "Jinetes de Huargo":
                logo = "isengard";
                break;
            case "Harad":
            case "Lejano Harad":
            case "Kârna":
                logo = "harad";
                break;
            case "El Este":
                logo = "rhun";
                break;
            case "Dol Guldur":
                logo = "dolguldur";
                break;
            case "Corsarios de Umbar":
                logo = "umbar";
                break;
            case "Gundabad":
                logo = "gundabad";
                break;
            case "Kand":
                logo = "kand";
                break;
            case "Dunland":
                logo = "dunland";
                break;
            default:
                logo = "logo";
        }
        //ciudad lago
        //moria
        //ciudad trasgos
        //angmar
        //mirkwood
        //cirith ungol
        //caminantes
        //rufianes
        return logo;
    }
    private String getRang(int i){
        String rang;
        switch(i) {
            case 1:
                rang = "Lider";
                break;
            case 2:
                rang = "Sargento";
                break;
            case 3:
                rang = "Heroe";
                break;
            case 4:
                rang = "Heroe herrante";
                break;
            case 5:
                rang = "Guerrero";
                break;
            case 6:
                rang = "Guerrero herrante";
                break;
            case 7:
                rang = "Criatura";
                break;
            default:
                rang = "";
        }
        return (rang);
    }
    public void setLogo(String logo){
        ((ImageView) findViewById(R.id.logo)).setImageResource(getResources()
                .getIdentifier(logo,"drawable",getPackageName()));
    }
    public void deleteComp(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Si eliminas esta compañia sus miembros y equipo desaparecerá de la faz de la Tierra Media. ¿Estas seguro?");
        alertDialog.setTitle("Disolver compañia");
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
        alertDialog.setCancelable(false);
        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which) {
                long id = Integer.parseInt(((TextView)findViewById(R.id.idComp)).getText().toString());

                Company comp = new Company();
                comp.setCompId(id);

                bd.open();
                bd.deleteCompany(comp);
                bd.close();

                finish();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        alertDialog.show();
    }


//    public boolean confirmar(String msg){
//        boolean agree;
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setMessage(msg);
//        alertDialog.setTitle("Disolver compañia");
//        alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
//        alertDialog.setCancelable(false);
//        alertDialog.setPositiveButton("Sí", new DialogInterface.OnClickListener()
//        {
//            public void onClick(DialogInterface dialog, int which) {
//                agree = true;
//            }
//        });
//        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener()
//        {
//            public void onClick(DialogInterface dialog, int which)
//            {
//                agree = false;
//            }
//        });
//        alertDialog.show();
//        return agree;
//    }
    public String currentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        String date = mdformat.format(calendar.getTime());
        return date;
    }
}

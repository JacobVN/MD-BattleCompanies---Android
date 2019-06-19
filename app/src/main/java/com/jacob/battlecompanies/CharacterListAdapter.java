package com.jacob.battlecompanies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CharacterListAdapter  extends ArrayAdapter {

    private final Activity context;

    private final List<Character> chars;

    public CharacterListAdapter(Activity context, List<Character> chars){

        super(context,R.layout.companies_list , chars);
        this.context=context;
        this.chars = chars;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.companies_list, null,true);

        Character character = chars.get(position);

        TextView id = rowView.findViewById(R.id.id);
        id.setText(String.valueOf(character.getCompId()));

        TextView nom = rowView.findViewById(R.id.compNom);
        nom.setText(character.getNomchar());

        TextView army = rowView.findViewById(R.id.army);
        army.setText(character.getTipus());

        TextView cost = rowView.findViewById(R.id.cost);
        cost.setText(String.valueOf(character.getCost()));

        TextView date = rowView.findViewById(R.id.date);
        date.setText(character.getDate());

        String logo;
        if (character.getImg()!= null){
            logo = character.getImg();
        }else{
            logo = "logo";
        }

        ImageView logoV = rowView.findViewById(R.id.logo);
        logoV.setImageResource(context.getResources().getIdentifier(logo,"drawable",context.getPackageName()));

        return rowView;
    }
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
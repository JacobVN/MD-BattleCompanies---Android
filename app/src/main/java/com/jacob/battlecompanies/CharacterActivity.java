package com.jacob.battlecompanies;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CharacterActivity extends AppCompatActivity {
    DataSourceComps bd = new DataSourceComps(this);

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        Bundle bundle = getIntent().getExtras();
        Button save = (Button) findViewById(R.id.saveChar);
        Button delete = (Button) findViewById(R.id.deleteChar);
        String charId = bundle.getString("CHARID");
        String compId = bundle.getString("COMPID");
        {
            TextView idc = (TextView) findViewById(R.id.charId);
            TextView comp = (TextView) findViewById(R.id.compId);
            if (!charId.equals("0")){
                idc.setText(charId);
                bd.open();
                Character membre = bd.getCharacter(Integer.parseInt(charId));
                emplenaDades(membre);
                bd.close();
            }else{
                idc.setText(charId);
                comp.setText(compId);
                delete.setVisibility(View.INVISIBLE);
                (findViewById(R.id.nom)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.razaView)).setVisibility(View.INVISIBLE);
                (findViewById(R.id.editCharNom)).setVisibility(View.VISIBLE);
                (findViewById(R.id.spinnerRaza)).setVisibility(View.VISIBLE);
                spinnerRaza();
            }
        }
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = ((TextView)findViewById(R.id.charId)).getText().toString();
                if (!id.equals("0")){
                    updateChar();
                } else {
                    insertChar();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String id = ((TextView)findViewById(R.id.charId)).getText().toString();
                if (!id.equals("0")){
                    deleteChar();
                }
            }
        });
    }

    private void emplenaDades(Character membre){
        //foto
        ((TextView) findViewById(R.id.charId)).setText(Long.toString(membre.getIdChar()));
        ((TextView) findViewById(R.id.nom)).setText(membre.getNomchar());
        ((TextView) findViewById(R.id.cost)).setText(Long.toString(membre.getCost()));
        ((TextView) findViewById(R.id.rang)).setText(membre.getRang());
        ((TextView) findViewById(R.id.razaView)).setText(membre.getRazachar());
        ((TextView) findViewById(R.id.compId)).setText(Long.toString(membre.getCompId()));
        ((TextView) findViewById(R.id.mv)).setText(Long.toString(membre.getMov()));
        ((TextView) findViewById(R.id.c)).setText(Long.toString(membre.getC()));
        ((TextView) findViewById(R.id.cd)).setText(Long.toString(membre.getCd()));
        ((TextView) findViewById(R.id.f)).setText(Long.toString(membre.getF()));
        ((TextView) findViewById(R.id.d)).setText(Long.toString(membre.getD()));
        ((TextView) findViewById(R.id.a)).setText(Long.toString(membre.getA()));
        ((TextView) findViewById(R.id.h)).setText(Long.toString(membre.getH()));
        ((TextView) findViewById(R.id.v)).setText(Long.toString(membre.getV()));
        ((TextView) findViewById(R.id.pd)).setText(Long.toString(membre.getPdr()));
        ((TextView) findViewById(R.id.vl)).setText(Long.toString(membre.getVlt()));
        ((TextView) findViewById(R.id.dt)).setText(Long.toString(membre.getDst()));
        ((TextView) findViewById(R.id.exp)).setText(Long.toString(membre.getExp()));
        ((TextView) findViewById(R.id.desc)).setText(membre.getDescChar());
        ((TextView) findViewById(R.id.rules)).setText(membre.getRules());
        ((TextView) findViewById(R.id.eqp)).setText(membre.getEqp());
        ((TextView) findViewById(R.id.inj)).setText(membre.getInj());
        ((CheckBox) findViewById(R.id.rest)).setChecked(membre.isRest());
    }
    private void spinnerRaza() {
        Spinner spinnerRaza = (Spinner) findViewById(R.id.spinnerRaza);
        DataSourceComps bd;
        bd = new DataSourceComps(this);
        bd.open();
        List<String> llista;
        llista=bd.getAllRazas();
        // Crear adapter
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, llista);
        // Drop down estil – llista amb radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // assignar adapter
        spinnerRaza.setAdapter(dataAdapter);
        bd.close();
    }
    private void spinnerRang() {
        Spinner spinnerRang = (Spinner) findViewById(R.id.spinnerRang);
    }
    private void insertChar(){
        String nomChar = ((EditText) findViewById(R.id.editCharNom)).getText().toString();
        long comp = Long.parseLong(((TextView) findViewById(R.id.compId)).getText().toString());
        long exp = Long.parseLong(((EditText) findViewById(R.id.exp)).getText().toString());
        long cost = Long.parseLong(((EditText) findViewById(R.id.cost)).getText().toString());
        String raza = ((Spinner) findViewById(R.id.spinnerRaza)).getSelectedItem().toString();
        String rang = ((EditText) findViewById(R.id.rang)).getText().toString();
        String desc = ((EditText) findViewById(R.id.desc)).getText().toString();
        long c = Long.parseLong(((TextView) findViewById(R.id.c)).getText().toString());
        long cd = Long.parseLong(((TextView) findViewById(R.id.cd)).getText().toString());
        long f = Long.parseLong(((TextView) findViewById(R.id.f)).getText().toString());
        long d = Long.parseLong(((TextView) findViewById(R.id.d)).getText().toString());
        long a = Long.parseLong(((TextView) findViewById(R.id.a)).getText().toString());
        long h = Long.parseLong(((TextView) findViewById(R.id.h)).getText().toString());
        long v = Long.parseLong(((TextView) findViewById(R.id.v)).getText().toString());
        long pdr = Long.parseLong(((TextView) findViewById(R.id.pd)).getText().toString());
        long vlt = Long.parseLong(((TextView) findViewById(R.id.vl)).getText().toString());
        long dst = Long.parseLong(((TextView) findViewById(R.id.dt)).getText().toString());
        long mov = Long.parseLong(((TextView) findViewById(R.id.mv)).getText().toString());
        String rules = ((EditText) findViewById(R.id.rules)).getText().toString();
        String eqp = ((EditText) findViewById(R.id.eqp)).getText().toString();
        String inj = ((EditText) findViewById(R.id.inj)).getText().toString();
        String foto = "noFoto";

        bd.open();
        Character character = new Character();
        character.setNomchar(nomChar);
        character.setCompId(comp);
        character.setExp(exp);
        character.setCost(cost);
        character.setRazaChar(raza);
        character.setRang(rang);
        character.setDescChar(desc);
        character.setC(c);
        character.setCd(cd);
        character.setF(f);
        character.setD(d);
        character.setA(a);
        character.setH(h);
        character.setV(v);
        character.setHero(false);
        character.setPdr(pdr);
        character.setVlt(vlt);
        character.setDst(dst);
        character.setMov(mov);
        character.setRules(rules);
        character.setEqp(eqp);
        character.setInj(inj);
        character.setImg(foto);
        character.setRest(false);

        bd.createCharacter(character);
        bd.close();
        long idChar = character.getIdChar();

        finish();
    }
    private void updateChar(){
        long idChar = Long.parseLong(((TextView) findViewById(R.id.charId)).getText().toString());
        String nomChar = ((TextView) findViewById(R.id.nom)).getText().toString();
        long comp = Long.parseLong(((TextView) findViewById(R.id.compId)).getText().toString());
        long exp = Long.parseLong(((EditText) findViewById(R.id.exp)).getText().toString());
        long cost = Long.parseLong(((EditText) findViewById(R.id.cost)).getText().toString());
        String raza = ((TextView) findViewById(R.id.razaView)).getText().toString();
        String rang = ((EditText) findViewById(R.id.rang)).getText().toString();
        String desc = ((EditText) findViewById(R.id.desc)).getText().toString();
        long c = Long.parseLong(((TextView) findViewById(R.id.c)).getText().toString());
        long cd = Long.parseLong(((TextView) findViewById(R.id.cd)).getText().toString());
        long f = Long.parseLong(((TextView) findViewById(R.id.f)).getText().toString());
        long d = Long.parseLong(((TextView) findViewById(R.id.d)).getText().toString());
        long a = Long.parseLong(((TextView) findViewById(R.id.a)).getText().toString());
        long h = Long.parseLong(((TextView) findViewById(R.id.h)).getText().toString());
        long v = Long.parseLong(((TextView) findViewById(R.id.v)).getText().toString());
//        boolean hero = ((CheckBox) findViewById(R.id.hero)).isChecked();
        long pdr = Long.parseLong(((TextView) findViewById(R.id.pd)).getText().toString());
        long vlt = Long.parseLong(((TextView) findViewById(R.id.vl)).getText().toString());
        long dst = Long.parseLong(((TextView) findViewById(R.id.dt)).getText().toString());
        long mov = Long.parseLong(((TextView) findViewById(R.id.mv)).getText().toString());
        String rules = ((EditText) findViewById(R.id.rules)).getText().toString();
        String eqp = ((EditText) findViewById(R.id.eqp)).getText().toString();
        String inj = ((EditText) findViewById(R.id.inj)).getText().toString();
        String foto = "noFoto";
        boolean rest = ((CheckBox) findViewById(R.id.rest)).isChecked();

        bd.open();
        Character character = new Character();
        character.setIdChar(idChar);
        character.setNomchar(nomChar);
        character.setCompId(comp);
        character.setExp(exp);
        character.setCost(cost);
        character.setRazaChar(raza);
        character.setRang(rang);
        character.setDescChar(desc);
        character.setC(c);
        character.setCd(cd);
        character.setF(f);
        character.setD(d);
        character.setA(a);
        character.setH(h);
        character.setV(v);
//        character.setHero(hero);
        character.setPdr(pdr);
        character.setVlt(vlt);
        character.setDst(dst);
        character.setMov(mov);
        character.setRules(rules);
        character.setEqp(eqp);
        character.setInj(inj);
        character.setImg(foto);
        character.setRest(rest);

        bd.updateCharacter(character);
        bd.close();
        long id = character.getIdChar();

        finish();
    }
    public void deleteChar(){
        Toast.makeText(this,"@TODO: confirm deleteChar", Toast.LENGTH_SHORT).show();
        long id = Integer.parseInt(((TextView)findViewById(R.id.charId)).getText().toString());

        String compId =  (((TextView)findViewById(R.id.compId)).getText().toString());

        Character character = new Character();
        character.setIdChar(id);

        bd.open();
        bd.deleteCharacter(character);//
        bd.close();

        finish();
    }
}

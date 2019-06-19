package com.jacob.battlecompanies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataSourceComps {
    private SQLiteDatabase database;
    private HelperComp dbAjuda; //CLASSE AJUDA
    private String[] allColumnsComp = {HelperComp.COLUMN_COMPID, HelperComp.COLUMN_COMPNOM, HelperComp.COLUMN_COMPCOST,
            HelperComp.COLUMN_INFL, HelperComp.COLUMN_COMPDESC, HelperComp.COLUMN_TIPUS, HelperComp.COLUMN_LOGO, HelperComp.COLUMN_DATE};

    private String[] allColumnsChar = {HelperComp.COLUMN_IDCHAR, HelperComp.COLUMN_COMP, HelperComp.COLUMN_NOMCHAR,
            HelperComp.COLUMN_RANG, HelperComp.COLUMN_RAZACHAR,  HelperComp.COLUMN_DESCCHAR, HelperComp.COLUMN_MOV, HelperComp.COLUMN_COST,
            HelperComp.COLUMN_IMG, HelperComp.COLUMN_C, HelperComp.COLUMN_CD, HelperComp.COLUMN_A, HelperComp.COLUMN_H,
            HelperComp.COLUMN_F, HelperComp.COLUMN_D, HelperComp.COLUMN_V, HelperComp.COLUMN_HERO, HelperComp.COLUMN_PDR,
            HelperComp.COLUMN_VLT, HelperComp.COLUMN_DST, HelperComp.COLUMN_EXP, HelperComp.COLUMN_RULES, HelperComp.COLUMN_INJ,
            HelperComp.COLUMN_EQP, HelperComp.COLUMN_REST};

    private String[] allRazas = {HelperComp.COLUMN_RAZA};
    private String[] allArmies = {HelperComp.COLUMN_ARMY};

    public DataSourceComps(Context context) { //CONSTRUCTOR
        dbAjuda = new HelperComp(context);
    }

    public void open() throws SQLException {
        database = dbAjuda.getWritableDatabase();
    }

    public void close() {
        dbAjuda.close();
    }

    public Company createCompany(Company comp) {
        // insert d'un nou comp
        ContentValues values = new ContentValues();
        values.put(HelperComp.COLUMN_COMPNOM, comp.getCompNom());
        values.put(HelperComp.COLUMN_COMPCOST, comp.getCompCost());
        values.put(HelperComp.COLUMN_TIPUS, comp.getTipus());
        values.put(HelperComp.COLUMN_COMPDESC, comp.getCompDesc());
        values.put(HelperComp.COLUMN_INFL, comp.getInfl());
        values.put(HelperComp.COLUMN_LOGO, comp.getLogo());
        values.put(HelperComp.COLUMN_DATE, comp.getDate());
        long insertId = database.insert(HelperComp.TABLE_COMP, null, values);
        comp.setCompId(insertId);
        return comp;
    }
    public boolean updateCompany(Company comp) {
        // update comp
        ContentValues values = new ContentValues();
        long id = comp.getCompId();
        values.put(HelperComp.COLUMN_COMPNOM, comp.getCompNom());
        values.put(HelperComp.COLUMN_COMPDESC, comp.getCompDesc());
        values.put(HelperComp.COLUMN_COMPCOST, comp.getCompCost());
        values.put(HelperComp.COLUMN_TIPUS, comp.getTipus());
        values.put(HelperComp.COLUMN_LOGO, comp.getLogo());
        values.put(HelperComp.COLUMN_INFL, comp.getInfl());
        values.put(HelperComp.COLUMN_DATE, comp.getDate());
        return database.update(HelperComp.TABLE_COMP, values, HelperComp.COLUMN_COMPID + "=" + id, null) > 0;
    }
    public void deleteCompany(Company comp) {
        long id = comp.getCompId();
        database.delete(HelperComp.TABLE_CHARACTER, HelperComp.COLUMN_COMP + " = " + id, null);
        database.delete(HelperComp.TABLE_COMP, HelperComp.COLUMN_COMPID + " = " + id, null);
    }
    public Company getCompany(long id) {
        Company comp;
        Cursor cursor = database.query(HelperComp.TABLE_COMP,
                allColumnsComp, HelperComp.COLUMN_COMPID + " = " + id, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            comp = cursorToCompany(cursor);
        } else {
            comp = new Company();
        } // id=-1 no trobat
        cursor.close();
        return comp;
    }
    public List<Company> getAllComps() {
        List<Company> comps = new ArrayList<Company>();
        Cursor cursor = database.query(HelperComp.TABLE_COMP, allColumnsComp, null, null, null, null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Company comp = cursorToCompany(cursor);
            comps.add(comp);
            cursor.moveToNext();
        }
        cursor.close();
        return comps;
    }
    private Company cursorToCompany(Cursor cursor) {
        Company v = new Company();
        v.setCompId(cursor.getLong(0));
        v.setCompNom(cursor.getString(1));
        v.setCompCost(cursor.getLong(2));
        v.setInfl(cursor.getLong(3));
        v.setCompDesc(cursor.getString(4));
        v.setTipus(cursor.getString(5));
        v.setLogo(cursor.getString(6));
        v.setDate(cursor.getString(7));
        return v;
    }

    public List<String> getAllRazas() {
        List<String> listRazas= new ArrayList<String>();
        Cursor cursor = database.query(HelperComp.TABLE_RAZA, allRazas, null, null, null, null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String raza = cursorToString(cursor);
            listRazas.add(raza);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return listRazas;
    }

    public List<String> getAllArmies() {
        List<String> listArmies= new ArrayList<String>();
        Cursor cursor = database.query(HelperComp.TABLE_ARMY, allArmies, null, null, null, null,
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String army = cursorToString(cursor);
            listArmies.add(army);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return listArmies;
    }

    private String cursorToString(Cursor cursor) {
        String s = cursor.getString(0);
        return s;
    }

    public List<Character> getCharsComp(long id){
        List<Character> charList = new ArrayList<Character>();
        Cursor cursor = database.query(HelperComp.TABLE_CHARACTER, allColumnsChar, HelperComp.COLUMN_COMP + "=" + id,
                null, null, null,HelperComp.COLUMN_RANG + " ASC");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Character membre = cursorToChar(cursor);
            charList.add(membre);
            cursor.moveToNext();
        }
        cursor.close();
        return charList;
    }
    private Character cursorToChar(Cursor cursor) {
        Character v = new Character();
        v.setIdChar(cursor.getLong(0));
        v.setCompId(cursor.getLong(1));
        v.setNomchar(cursor.getString(2));
        v.setRang(cursor.getString(3));
        v.setRazaChar(cursor.getString(4));
        v.setDescChar(cursor.getString(5));
        v.setMov(cursor.getLong(6));
        v.setCost(cursor.getLong(7));
        v.setImg(cursor.getString(8));
        v.setC(cursor.getLong(9));
        v.setCd(cursor.getLong(10));
        v.setA(cursor.getLong(11));
        v.setH(cursor.getLong(12));
        v.setF(cursor.getLong(13));
        v.setD(cursor.getLong(14));
        v.setV(cursor.getLong(15));
        v.setHero(cursor.getInt(16) == 1);
        v.setPdr(cursor.getLong(17));
        v.setVlt(cursor.getLong(18));
        v.setDst(cursor.getLong(19));
        v.setExp(cursor.getLong(20));
        v.setRules(cursor.getString(21));
        v.setInj(cursor.getString(22));
        v.setEqp(cursor.getString(23));
        v.setRest(cursor.getInt(24) == 1);
        return v;
    }

    public Character createCharacter(Character character) {
        // insert d'un nou comp
        ContentValues values = new ContentValues();
        values.put(HelperComp.COLUMN_NOMCHAR, character.getNomchar());
        values.put(HelperComp.COLUMN_COMP, character.getCompId());
        values.put(HelperComp.COLUMN_RAZACHAR, character.getRazachar());
        values.put(HelperComp.COLUMN_RANG, character.getRang());
        values.put(HelperComp.COLUMN_DESCCHAR, character.getDescChar());
        values.put(HelperComp.COLUMN_COST, character.getCost());
        values.put(HelperComp.COLUMN_EXP, character.getExp());
        values.put(HelperComp.COLUMN_C, character.getC());
        values.put(HelperComp.COLUMN_CD, character.getCd());
        values.put(HelperComp.COLUMN_F, character.getF());
        values.put(HelperComp.COLUMN_D, character.getD());
        values.put(HelperComp.COLUMN_A, character.getA());
        values.put(HelperComp.COLUMN_H, character.getH());
        values.put(HelperComp.COLUMN_V, character.getV());
        values.put(HelperComp.COLUMN_HERO, character.isHero());
        values.put(HelperComp.COLUMN_PDR, character.getPdr());
        values.put(HelperComp.COLUMN_VLT, character.getVlt());
        values.put(HelperComp.COLUMN_DST, character.getDst());
        values.put(HelperComp.COLUMN_MOV, character.getMov());
        values.put(HelperComp.COLUMN_IMG, character.getImg());
        values.put(HelperComp.COLUMN_RULES, character.getRules());
        values.put(HelperComp.COLUMN_EQP, character.getEqp());
        values.put(HelperComp.COLUMN_INJ, character.getInj());
        values.put(HelperComp.COLUMN_REST, character.isRest());
        long insertId = database.insert(HelperComp.TABLE_CHARACTER, null, values);
        character.setIdChar(insertId);
        return character;
    }
    public boolean updateCharacter(Character character) {
        // insert d'un nou comp
        ContentValues values = new ContentValues();
        long id = character.getIdChar();
        values.put(HelperComp.COLUMN_IDCHAR, character.getIdChar());
        values.put(HelperComp.COLUMN_NOMCHAR, character.getNomchar());
        values.put(HelperComp.COLUMN_COMP, character.getCompId());
        values.put(HelperComp.COLUMN_RAZACHAR, character.getRazachar());
        values.put(HelperComp.COLUMN_RANG, character.getRang());
        values.put(HelperComp.COLUMN_DESCCHAR, character.getDescChar());
        values.put(HelperComp.COLUMN_COST, character.getCost());
        values.put(HelperComp.COLUMN_EXP, character.getExp());
        values.put(HelperComp.COLUMN_C, character.getC());
        values.put(HelperComp.COLUMN_CD, character.getCd());
        values.put(HelperComp.COLUMN_F, character.getF());
        values.put(HelperComp.COLUMN_D, character.getD());
        values.put(HelperComp.COLUMN_A, character.getA());
        values.put(HelperComp.COLUMN_H, character.getH());
        values.put(HelperComp.COLUMN_V, character.getV());
        values.put(HelperComp.COLUMN_HERO, character.isHero());
        values.put(HelperComp.COLUMN_PDR, character.getPdr());
        values.put(HelperComp.COLUMN_VLT, character.getVlt());
        values.put(HelperComp.COLUMN_DST, character.getDst());
        values.put(HelperComp.COLUMN_MOV, character.getMov());
        values.put(HelperComp.COLUMN_IMG, character.getImg());
        values.put(HelperComp.COLUMN_RULES, character.getRules());
        values.put(HelperComp.COLUMN_EQP, character.getEqp());
        values.put(HelperComp.COLUMN_INJ, character.getInj());
        values.put(HelperComp.COLUMN_REST, character.isRest());
        return database.update(HelperComp.TABLE_CHARACTER, values, HelperComp.COLUMN_IDCHAR + "=" + id, null) > 0;

    }
    public Character getCharacter(long id) {
        Character membre;
        Cursor cursor = database.query(HelperComp.TABLE_CHARACTER,
                allColumnsChar, HelperComp.COLUMN_IDCHAR + " = " + id, null,
                null, null, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            membre = cursorToChar(cursor);
        } else {
            membre = new Character();
        } // id=-1 no trobat
        cursor.close();
        return membre;
    }
    public void deleteCharacter(Character character) {
        long id = character.getIdChar();
        database.delete(HelperComp.TABLE_CHARACTER, HelperComp.COLUMN_IDCHAR + " = " + id, null);
    }
}

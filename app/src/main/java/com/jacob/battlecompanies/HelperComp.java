package com.jacob.battlecompanies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperComp extends SQLiteOpenHelper {

    public static final String TABLE_COMP = "comp";
    public static final String COLUMN_COMPID = "compid";
    public static final String COLUMN_COMPNOM = "compnom";
    public static final String COLUMN_COMPCOST = "compcost";
    public static final String COLUMN_INFL = "infl";
    public static final String COLUMN_COMPDESC = "compdesc";
    public static final String COLUMN_TIPUS = "tipus";
    public static final String COLUMN_LOGO = "logo";
    public static final String COLUMN_DATE = "date";

    public static final String TABLE_CHARACTER = "character";
    public static final String COLUMN_IDCHAR = "idchar";
    public static final String COLUMN_COMP = "comp";
    public static final String COLUMN_NOMCHAR = "nomchar";
    public static final String COLUMN_RANG = "rang";
    public static final String COLUMN_RAZACHAR = "razachar";
    public static final String COLUMN_DESCCHAR = "descchar";
    public static final String COLUMN_MOV = "mov";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_C = "c";
    public static final String COLUMN_CD = "cd";
    public static final String COLUMN_A = "a";
    public static final String COLUMN_H = "h";
    public static final String COLUMN_F = "f";
    public static final String COLUMN_D = "d";
    public static final String COLUMN_V = "v";
    public static final String COLUMN_HERO = "hero";
    public static final String COLUMN_PDR = "pdr";
    public static final String COLUMN_VLT = "vlt";
    public static final String COLUMN_DST = "dst";
    public static final String COLUMN_EXP = "exp";
    public static final String COLUMN_RULES = "rules";
    public static final String COLUMN_INJ = "inj";
    public static final String COLUMN_EQP = "eqp";
    public static final String COLUMN_REST = "rest";

    public static final String TABLE_RAZA = "raza";
    public static final String COLUMN_RAZA= "razanom";

    public static final String TABLE_ARMY = "army";
    public static final String COLUMN_ARMY= "armynom";

    private static final String DATABASE_NAME = "battleCompanies";
    private static final int DATABASE_VERSION = 8; // Controla la versió de la base de dades

    // Setències de creació de la base de dades
    private static final String DATABASE_CREATE_COMP = "create table "
            + TABLE_COMP + "("
            + COLUMN_COMPID + " integer primary key autoincrement, "
            + COLUMN_COMPNOM + " text,"
            + COLUMN_COMPCOST + " text,"
            + COLUMN_COMPDESC + " text,"
            + COLUMN_INFL + " text,"
            + COLUMN_TIPUS + " text,"
            + COLUMN_LOGO + " text,"
            + COLUMN_DATE + " text);";
    private static final String DATABASE_CREATE_CHARACTER = "create table "
            + TABLE_CHARACTER + "("
            + COLUMN_IDCHAR + " integer primary key autoincrement, "
            + COLUMN_COMP + " text,"
            + COLUMN_NOMCHAR + " text,"
            + COLUMN_RANG + " text,"
            + COLUMN_RAZACHAR + " text,"
            + COLUMN_DESCCHAR + " text,"
            + COLUMN_MOV + " text,"
            + COLUMN_COST + " text,"
            + COLUMN_IMG + " text,"
            + COLUMN_C + " text,"
            + COLUMN_CD + " text,"
            + COLUMN_A + " text,"
            + COLUMN_H + " text,"
            + COLUMN_F + " text,"
            + COLUMN_D + " text,"
            + COLUMN_V + " text,"
            + COLUMN_HERO + " text,"
            + COLUMN_PDR + " text,"
            + COLUMN_VLT + " text,"
            + COLUMN_DST + " text,"
            + COLUMN_EXP + " text,"
            + COLUMN_RULES + " text,"
            + COLUMN_INJ + " text,"
            + COLUMN_EQP + " text,"
            + COLUMN_REST + " text);";

    private static final String DATABASE_CREATE_RAZA = "create table "
            + TABLE_RAZA + "("
            + COLUMN_RAZA + " text not null primary key);";
    private static final String DATABASE_CREATE_ARMY = "create table "
            + TABLE_ARMY + "("
            + COLUMN_ARMY + " text not null primary key);";

    public HelperComp(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        // CREAM LA BASE DE DADES
        database.execSQL(DATABASE_CREATE_COMP);
        database.execSQL(" insert into "+TABLE_COMP+"(compnom, compcost, infl, compdesc,tipus,date,logo) values('Incursores de Mauhur',56,4, 'desc1' ,'Isengard','25/02/2019','isengard')");
        database.execSQL(DATABASE_CREATE_CHARACTER);
        database.execSQL(" insert into "+TABLE_CHARACTER+"(COMP,NOMCHAR,RANG,RAZACHAR,DESCCHAR,MOV,COST,IMG,C,CD,A,H,F,D,V,HERO,PDR,VLT,DST,EXP,RULES,INJ,EQP,REST)" +
                " values(1,'Mauhur','Lider','Uruk-Hai','Miembro inicial',14,9,'img',4,4,1,1,4,5,3,1,1,1,1,0,'','','Armadura, Escudo',1)");
        database.execSQL(DATABASE_CREATE_RAZA);
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Hombre'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Elfo'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Enano'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Hobbit'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Espiritu'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Troll'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Orco'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Huargo'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Araña'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Uruk-Hai'))");
        database.execSQL(" insert into "+TABLE_RAZA+"(razaNom) values(('Trasgo'))");

        database.execSQL(DATABASE_CREATE_ARMY);
        //luz
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Minas Tirith'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Osgiliath'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Feudos'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Rohan'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Hombres del Oeste'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Muertos del Sagrario'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Arnor'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Montaraces del Norte'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Ciudad del Lago'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('La Última Alianza'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Erebor y Ciudad de Valle'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Lothlórien'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Rivendell'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Salones de Thranduil'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Colinas de Hierro'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('El Pueblo de Durin'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Expedicinarios de Moria'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('La Comarca'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Caminantes Infatigables'))");
        //oscuridad
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Mordor'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Cirith Ungol'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Minas Morgul'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Isengard'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Jinetes de Huargo'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Dunland'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Rufianes de Zarquino'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Moria'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Ciudad de los Trasgos'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Angmar'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Gundabad'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Dol Guldur'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Habitantes de Mirkwood'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Harad'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Kârna'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Lejano Harad'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Corsarios de Umbar'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('El Este'))");
        database.execSQL(" insert into "+TABLE_ARMY+"(armyNom) values(('Kand'))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Detecta si hi ha una canvi a DATABASE_VERSION i recrea la base de dades
        Log.w(HelperComp.class.getName(),"Modificant desde la versió " + oldVersion + " a "+ newVersion );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMP);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHARACTER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RAZA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARMY);
        onCreate(db);
    }
}

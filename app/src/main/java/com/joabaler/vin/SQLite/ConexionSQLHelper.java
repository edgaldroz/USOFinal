package com.joabaler.vin.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ConexionSQLHelper extends SQLiteOpenHelper {

    public ConexionSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase dbvin) {
        dbvin.execSQL(ScriptDB.CreateTableMATRIZ);
        dbvin.execSQL(ScriptDB.CreateTableVECTORPROYECCION);
        dbvin.execSQL(ScriptDB.CREATE_TABLE_INFORMACION);
        dbvin.execSQL(ScriptDB.CREATE_TABLE_DATOS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase dbvin, int vAntigua, int vNueva) {

        if(vNueva > vAntigua){
            dbvin.execSQL("DROP TABLE IF EXISTS "+ScriptDB.tMATRIZ);
            dbvin.execSQL("DROP TABLE IF EXISTS "+ScriptDB.tVECTORPROYECCION);
            dbvin.execSQL("DROP TABLE IF EXISTS "+ScriptDB.TABLE_INFO_GENERAL);
            dbvin.execSQL("DROP TABLE IF EXISTS "+ScriptDB.TABLE_DATOS);
            onCreate(dbvin);
        }
    }
}

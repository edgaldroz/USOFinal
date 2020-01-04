package com.joabaler.vin.SQLite;

public class ScriptDB {

    //============= ESTRUCTURA GENERAL DEL LA BASE DE DATOS =============

    public static final String nameDataBase = "vin.db";
    public static final Integer numVersionDataBase = 15;

    //Tabla MATRIZ
    public static final String tMATRIZ = "matriz";
    public static final String cMIdRegistro ="idregistro";  //0
    public static final String cMIdEjercicio ="idejercicio";  //1
    public static final String cMIdEcuacion ="idecuacion";  //2
    public static final String cMw ="w";  //3
    public static final String cMx ="x";  //4
    public static final String cMy ="y";  //5
    public static final String cMz ="z";  //6
    public static final String cMIgual ="igual";  //7
    public static final String cMTamano ="tamano";  //8
    public static final String cMNombreTema ="nombretema";  //9
    public static final String cMCategoriaTema ="categoriatema";  //10
    public static final String cMIdTema ="idtema";  //11

    public static final String CreateTableMATRIZ = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                                 " %s INTEGER, %s INTEGER, %s REAL, %s REAL," +
                                                                                 " %s REAL, %s REAL, %s REAL, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER)",
                                                                tMATRIZ,cMIdRegistro,cMIdEjercicio,cMIdEcuacion,cMw,cMx,cMy,cMz,cMIgual,cMTamano,cMNombreTema,cMCategoriaTema,cMIdTema);

    //Tabla VECTOR PROYECCION
    public static final String tVECTORPROYECCION = "vproyeccion";
    public static final String cVPIdRegistro = "idregistro"; //0
    public static final String cVPIdEjercicio = "idejercicio"; //1
    public static final String cVPIdEcuacion = "idecuacion"; //2
    public static final String cVPCuadrante = "cuadrante"; //3
    public static final String cVPDireccion = "direccion"; //4
    public static final String cVPApunta = "apunta"; //5
    public static final String cVPFuerza = "fuerza"; //6
    public static final String cVPAngulo = "angulo"; //7
    public static final String cVPAngRespectoA = "angrespectoa"; //8
    public static final String cVPNombreTema = "nombretema"; //9
    public static final String cVPCategoriaTema = "categoriatema"; //10
    public static final String cVPIdTema = "idtema"; //11

    public static final String CreateTableVECTORPROYECCION = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                                           " %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s REAL, %s REAL, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                                                                            tVECTORPROYECCION,cVPIdRegistro,cVPIdEjercicio,cVPIdEcuacion,cVPCuadrante,cVPDireccion,cVPApunta,cVPFuerza,cVPAngulo,cVPAngRespectoA,cVPNombreTema,cVPCategoriaTema,cVPIdTema);



    //Tabla EJERCICIOS GENERALES
    public static final String TABLE_INFO_GENERAL = "infogeneral";

    public static final String CAMPO_IDREG = "idreg"; //0
    public static final String CAMPO_IDEXERCISE = "idexercise"; //1
    public static final String CAMPO_NAMETOPIC = "nametopic"; //2
    public static final String CAMPO_NAMECATEGORY = "namecategory"; //3
    public static final String CAMPO_IDCATEGORY = "idcategory"; //4
    public static final String CAMPO_IDTOPIC = "idtopic"; //5
    public static final String CAMPO_GRAPHIC = "graphics"; //6
    public static final String CAMPO_PASO = "paso"; //7

    public static final String CREATE_TABLE_INFORMACION = "CREATE TABLE "+ TABLE_INFO_GENERAL +" ("+CAMPO_IDREG+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_IDEXERCISE+" INTEGER,"+CAMPO_NAMETOPIC+" TEXT,"+CAMPO_NAMECATEGORY+" TEXT,"+CAMPO_IDCATEGORY+" INTEGER,"+CAMPO_IDTOPIC+" INTEGER,"+CAMPO_GRAPHIC+" INTEGER, "+CAMPO_PASO+" INTEGER)";    /*
    public static final String CreateTableEJERCICIOSGENERALES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                                           " %s INTEGER, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)",
            TABLE_INFO_GENERAL,CAMPO_IDREG,CAMPO_IDEXERCISE,CAMPO_NAMETOPIC,CAMPO_NAMECATEGORY,CAMPO_IDCATEGORY,CAMPO_IDTOPIC);
    */





    //Tabla DATOS EGENERALES
    public static final String TABLE_DATOS = "datos";
    public static final String CAMPO_IDDATO = "iddato"; //0
    public static final String CAMPO_IDFK_EXERCISE = "idfkexercise"; //1
    public static final String CAMPO_VALOR = "valor"; //2
    public static final String CAMPO_POSICION = "posicion"; //3

    public static final String CREATE_TABLE_DATOS = "CREATE TABLE "+TABLE_DATOS+" ("+CAMPO_IDDATO+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_IDFK_EXERCISE+" INTEGER, "+CAMPO_VALOR+" REAL,"+CAMPO_POSICION+" INTEGER)";

    //public static final String CreateTableDATOSEGENERALES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +" %s INTEGER, %s REAL, %s INTEGER)",TABLE_DATOS,CAMPO_IDDATO,CAMPO_IDFK_EXERCISE,CAMPO_VALOR,CAMPO_POSICION);
}

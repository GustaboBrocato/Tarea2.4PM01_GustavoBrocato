package com.example.tarea24pm01_gustavobrocato.Config;

public class Transacciones {
    //Nombre de la base de datos
    public static final String namedb = "PM01Tarea2_4";

    //Tabla de Contactos
    public static final String tablaFirmas = "firmas";

    //Campos de la tabla de Firmas
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String firma = "firma";

    //Consultas de tabla Firmas
    public static final String CreateTableFirmas = "CREATE TABLE " + tablaFirmas +"( id INTEGER PRIMARY KEY AUTOINCREMENT,"+ descripcion + " TEXT," + firma + " BLOB)";
    public static final String DropTableFirmas = "DROP TABLE IF EXISTS" + tablaFirmas;
    public static final String SelectTableFirmas = "SELECT * FROM "+ Transacciones.tablaFirmas;
}

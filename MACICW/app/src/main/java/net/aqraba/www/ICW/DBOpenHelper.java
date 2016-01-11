package net.aqraba.www.ICW;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aqrabawi on 15-08-10.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String LOGTAG="EXPLORECA";

    //Constants for db name and version
    public static final String DATABASE_NAME = "prayertimes.db";
    public static final int DATABASE_VERSION = 1;


    //Constants for identifying table and columns
    public static final String TABLE_PRAYER = "prayertimes";

    public static final String P_ID = "p_id";
    public static final String P_DATE = "p_date";
    public static final String P_DAY = "p_day";
    public static final String P_FAJR = "p_fajr";
    public static final String P_SUN = "p_sun";
    public static final String P_ZUHR = "p_zuhr";
    public static final String P_ASR_SHAF = "p_asr_shaf";
    public static final String P_ASR_HAN = "p_asr_han";
    public static final String P_MAGHRIB = "p_maghrib";
    public static final String P_ISHA = "p_isha";
    public static final String IQAMA_FAJR = "iqama_fajr";
    public static final String IQAMA_ZUHR = "iqama_zuhr";
    public static final String IQAMA_ASR = "iqama_asr";
    public static final String IQAMA_MAGHRIB = "iqama_maghrib";
    public static final String IQAMA_ISHA = "iqama_isha";


    public static final String[] ALL_COLUMNS =
            {P_ID, P_DATE, P_DAY, P_FAJR, P_SUN,P_ZUHR, P_ASR_SHAF,P_ASR_HAN, P_MAGHRIB,P_ISHA,IQAMA_FAJR,IQAMA_ZUHR,IQAMA_ASR,IQAMA_MAGHRIB,IQAMA_ISHA};

    //SQL to create table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PRAYER + " (" +
                    P_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    P_DATE + " TEXT, " +
                    P_DAY  + " TEXT, " +
                    P_FAJR + " TEXT, " +
                    P_SUN  + " TEXT, " +
                    P_ZUHR + " TEXT, " +
                    P_ASR_SHAF  + " TEXT, " +
                    P_ASR_HAN   + " TEXT, " +
                    P_MAGHRIB   + " TEXT, " +
                    P_ISHA      + " TEXT, " +
                    IQAMA_FAJR  + " TEXT, " +
                    IQAMA_ZUHR  + " TEXT, " +
                    IQAMA_ASR   + " TEXT, " +
                    IQAMA_MAGHRIB   + " TEXT, " +
                    IQAMA_ISHA      + " TEXT " +
                    ")";


    public DBOpenHelper(Context context) {
        //super(context,context.getExternalFilesDir(null).getAbsolutePath() + "/"+ DATABASE_NAME, null, DATABASE_VERSION);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         db.execSQL(TABLE_CREATE);
         Log.i(LOGTAG, "Table has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRAYER);
        onCreate(db);
    }
}

package net.aqraba.www.ICW;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Aqrabawi on 15-08-11.
 */
public class DataSource {

    public static final String LOGTAG="EXPLORECA";


    SQLiteOpenHelper dbhelpter;
    SQLiteDatabase database;

    public DataSource(Context context) {
        dbhelpter = new DBOpenHelper(context);
    }


    public void open() {
    Log.i(LOGTAG,"Database opened");
        database = dbhelpter.getWritableDatabase();

    }

    public void close() {
    Log.i(LOGTAG, "Database closed");
    dbhelpter.close();
    }

    public Prayer create(Prayer prayer) {
        ContentValues values = new ContentValues();
        values.put(DBOpenHelper.P_DATE, prayer.getP_date());
        values.put(DBOpenHelper.P_DAY,prayer.getP_day());
        values.put(DBOpenHelper.P_FAJR,prayer.getP_fajr());
        values.put(DBOpenHelper.P_SUN, prayer.getP_sun());
        values.put(DBOpenHelper.P_ZUHR, prayer.getP_zuhr());
        values.put(DBOpenHelper.P_ASR_SHAF, prayer.getP_asr_shaf());
        values.put(DBOpenHelper.P_ASR_HAN, prayer.getP_asr_han());
        values.put(DBOpenHelper.P_MAGHRIB, prayer.getP_maghrib());
        values.put(DBOpenHelper.P_ISHA, prayer.getP_isha());
        values.put(DBOpenHelper.IQAMA_FAJR, prayer.getIqama_fajr());
        values.put(DBOpenHelper.IQAMA_ZUHR, prayer.getIqama_zuhr());
        values.put(DBOpenHelper.IQAMA_ASR, prayer.getIqama_asr());
        values.put(DBOpenHelper.IQAMA_MAGHRIB, prayer.getIqama_maghrib());
        values.put(DBOpenHelper.IQAMA_ISHA, prayer.getIqama_isha());

        long insertid = database.insert(DBOpenHelper.TABLE_PRAYER,null,values);

        /*
        database.execSQL("INSERT INTO prayertimes (p_date,p_day,p_fajr,p_sun,p_zuhr,p_asr_shaf, p_asr_han,p_maghrib, p_isha, iqama_fajr, iqama_zuhr,iqama_asr,iqama_maghrib,iqama_isha) values (" +
                prayer.getP_date() + "," + prayer.getP_day() + "," + prayer.getP_fajr() + "," +
                        prayer.getP_sun() + "," + prayer.getP_zuhr() + "," + prayer.getP_asr_shaf() + "," + prayer.getP_asr_han() + "," +
                        prayer.getP_maghrib() + "," + prayer.getP_isha() + "," +
                        prayer.getIqama_fajr() + "," + prayer.getIqama_zuhr() + "," +
                        prayer.getIqama_asr() + "," + prayer.getIqama_maghrib() + "," + prayer.getIqama_isha()  + ")");

    */
                   prayer.setP_id(insertid);
        return prayer;
    }

}

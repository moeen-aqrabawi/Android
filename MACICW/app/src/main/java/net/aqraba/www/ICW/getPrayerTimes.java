package net.aqraba.www.ICW;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Aqrabawi on 15-08-09.
 */
public class getPrayerTimes extends Activity {

    static class ItemViewHolder {
        TextView m_date;
        TextView m_day;
        TextView m_fajr;
        TextView m_sun;
        TextView m_zuhr;
        TextView m_asr_shaf;
        TextView m_asr_han;
        TextView m_maghrib;
        TextView m_isha;
        TextView m_iqama_fajr;
        TextView m_iqama_zuhr;
        TextView m_iqama_asr;
        TextView m_iqama_maghrib;
        TextView m_iqama_isha;

    }


    public List<String[]> prayertimes = new ArrayList<String[]>();

    DataSource datasource;
    public static final String LOGTAG="EXPLORECA";


    public Prayer add(String[] object, Prayer prayer) {
        prayertimes.add(object);
        prayer.setP_date(object[0].toString());
        prayer.setP_day(object[1].toString());
        prayer.setP_fajr(object[2].toString());
        prayer.setP_sun(object[3].toString());
        prayer.setP_zuhr(object[4].toString());
        prayer.setP_asr_shaf(object[5].toString());
        prayer.setP_asr_han(object[6].toString());
        prayer.setP_maghrib(object[7].toString());
        prayer.setP_isha(object[8].toString());
        prayer.setIqama_fajr(object[9].toString());
        prayer.setIqama_zuhr(object[10].toString());
        prayer.setIqama_asr(object[11].toString());
        prayer.setIqama_maghrib(object[12].toString());
        prayer.setIqama_isha(object[13].toString());

        return prayer;
    }

    public int getCount() {
        return this.prayertimes.size();
    }

    public String[] getItem(int position) {
        return this.prayertimes.get(position);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.m_date = (TextView) row.findViewById(R.id.m_date);
            viewHolder.m_day = (TextView) row.findViewById(R.id.m_day);
            viewHolder.m_fajr = (TextView) row.findViewById(R.id.m_fajr);
            viewHolder.m_sun = (TextView) row.findViewById(R.id.m_sun);
            viewHolder.m_zuhr = (TextView) row.findViewById(R.id.m_zuhr);
            viewHolder.m_asr_shaf = (TextView) row.findViewById(R.id.m_asr_shaf);
            viewHolder.m_asr_han = (TextView) row.findViewById(R.id.m_asr_han);
            viewHolder.m_maghrib = (TextView) row.findViewById(R.id.m_maghrib);
            viewHolder.m_isha = (TextView) row.findViewById(R.id.m_isha);
            viewHolder.m_iqama_fajr = (TextView) row.findViewById(R.id.m_iqama_fajr);
            viewHolder.m_iqama_zuhr = (TextView) row.findViewById(R.id.m_iqama_zuhr);
            viewHolder.m_iqama_asr = (TextView) row.findViewById(R.id.m_iqama_asr);
            viewHolder.m_iqama_maghrib = (TextView) row.findViewById(R.id.m_iqama_maghrib);
            viewHolder.m_iqama_isha = (TextView) row.findViewById(R.id.m_iqama_isha);
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = (String[]) getItem(position);
        viewHolder.m_date.setText(stat[0]);
        viewHolder.m_day.setText(stat[1]);
        viewHolder.m_fajr.setText(stat[2]);
        viewHolder.m_sun.setText(stat[3]);
        viewHolder.m_zuhr.setText(stat[4]);
        viewHolder.m_asr_shaf.setText(stat[5]);
        viewHolder.m_asr_han.setText(stat[6]);
        viewHolder.m_maghrib.setText(stat[7]);
        viewHolder.m_isha.setText(stat[8]);
        viewHolder.m_iqama_fajr.setText(stat[9]);
        viewHolder.m_iqama_zuhr.setText(stat[10]);
        viewHolder.m_iqama_asr.setText(stat[11]);
        viewHolder.m_iqama_maghrib.setText(stat[12]);
        viewHolder.m_iqama_isha.setText(stat[13]);


        return row;
    }




    public void btnDispRemote_Click(View view) {
        Button btnSer = (Button) findViewById(R.id.btnServer);
        btnSer.setText("Updating !!");
    }


    public class CSVFile {
        InputStream inputStream;

        public CSVFile(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public List<String[]> read() {
            List<String[]> resultList = new ArrayList<String[]>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String csvLine;
                while ((csvLine = reader.readLine()) != null) {
                    String[] row = csvLine.split(",");
                    resultList.add(row);
                }
            } catch (IOException ex) {
                throw new RuntimeException("Error in reading CSV file: " + ex);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error while closing input stream: " + e);
                }
            }
            return resultList;
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prayer_times);

        ListView listTimes = (ListView) findViewById(R.id.listTimes);
        //ItemArrayAdapter itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listTimes.onSaveInstanceState();
        //listTimes.setAdapter(itemArrayAdapter);
        listTimes.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);

        //Create table if not exists

        datasource = new DataSource(this);
        Prayer prayer = new Prayer();


            List<String[]> prayerList = csvFile.read();

            for (String[] prayerData : prayerList) {
                prayertimes.add(prayerData);

                /*
                prayer.setP_date("1");
                prayer.setP_day("2");
                prayer.setP_fajr("3");
                prayer.setP_sun("4");
                prayer.setP_zuhr("5");
                prayer.setP_asr_shaf("6");
                prayer.setP_asr_han("7");
                prayer.setP_maghrib("8");
                prayer.setP_isha("9");
                prayer.setIqama_fajr("10");
                prayer.setIqama_zuhr("11");
                prayer.setIqama_asr("12");
                prayer.setIqama_maghrib("13");
                prayer.setIqama_isha("14");
                prayer.setP_date(prayerData[0]);
                prayer.setP_day(prayerData[1]);
                prayer.setP_fajr(prayerData[2]);
                prayer.setP_sun(prayerData[3]);
                prayer.setP_zuhr(prayerData[4]);
                prayer.setP_asr_shaf(prayerData[5]);
                prayer.setP_asr_han(prayerData[6]);
                prayer.setP_maghrib(prayerData[7]);
                prayer.setP_isha(prayerData[8]);
                prayer.setIqama_fajr(prayerData[9]);
                prayer.setIqama_zuhr(prayerData[10]);
                prayer.setIqama_asr(prayerData[11]);
                prayer.setIqama_maghrib(prayerData[12]);
                prayer.setIqama_isha(prayerData[13]);

*/
                Toast toast = Toast.makeText(this, "Prayer : " + prayer.getP_id() + prayer.getP_date(), Toast.LENGTH_SHORT);
                toast.show();

                prayer = datasource.create(prayer);
                Log.i(LOGTAG, "record created with id " + prayer.getP_id());

            }


        //String destPath = "/data/data/" + getPackageName() + "/sdcard/prayertimes.db";
        //String destPath = "/sdcard/prayertimes.db";
        //File f = new File(destPath);
        // if (f.exists()) {
        //  if (db.tableExists("prayertimes"))
        // {
        // CopyDB( getBaseContext().getAssets().open("mydb"),
        //         new FileOutputStream(destPath));
        //Toast toast = Toast.makeText(this, "Database Exists", Toast.LENGTH_SHORT);
        //toast.show();
        //String[] prayerData = null;
        //Cursor cursor = db.query(db.CONTENT_URI, prayerData, "*", DBOpenHelper.ALL_COLUMNS, null, null);
        //db.ToastRecord(cursor);
        //} else {
        // Toast toast = Toast.makeText(this, "No Database", Toast.LENGTH_SHORT);
        //toast.show();
        //  }




    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }


}




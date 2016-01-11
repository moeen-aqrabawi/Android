package net.aqraba.www.ICW;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
/*
    public class ItemArrayAdapter extends ArrayAdapter<String[]> {
/*
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

        public ItemArrayAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public Prayer add(String[] object, Prayer prayer) {
            prayertimes.add(object);
            super.add(object);

            prayer.setP_date(prayertimes.get(0).toString());
            prayer.setP_day(prayertimes.get(1).toString());
            prayer.setP_fajr(prayertimes.get(2).toString());
            prayer.setP_sun(prayertimes.get(3).toString());
            prayer.setP_zuhr(prayertimes.get(4).toString());
            prayer.setP_asr_shaf(prayertimes.get(5).toString());
            prayer.setP_asr_han(prayertimes.get(6).toString());
            prayer.setP_maghrib(prayertimes.get(7).toString());
            prayer.setP_isha(prayertimes.get(8).toString());
            prayer.setIqama_fajr(prayertimes.get(9).toString());
            prayer.setIqama_zuhr(prayertimes.get(10).toString());
            prayer.setIqama_asr(prayertimes.get(11).toString());
            prayer.setIqama_maghrib(prayertimes.get(12).toString());
            prayer.setIqama_isha(prayertimes.get(13).toString());

            return prayer;
        }

        @Override
        public int getCount() {
            return this.prayertimes.size();
        }

        @Override
        public String[] getItem(int position) {
         return this.prayertimes.get(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ItemViewHolder viewHolder;

            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) this.getContext().
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



*/


    }


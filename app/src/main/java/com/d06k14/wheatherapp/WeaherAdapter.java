//package com.d06k14.wheatherapp;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class WeaherAdapter extends BaseAdapter {
//    Context context;
//    int layout;
//    List<Weather> list;
//
//    public WeaherAdapter(Context context, int layout, List<Weather> list) {
//        this.context = context;
//        this.layout = layout;
//        this.list = list;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    class ViewHolder{
//        TextView dt;
//        TextView State;
//        ImageView imgIcon;
//        TextView dt2;
//        TextView temp2;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if (view ==  null) {
//            viewHolder = new ViewHolder();
//            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            view = layoutInflater.inflate(layout, null);
//            viewHolder.dt = view.findViewById(R.id.tv_dt);
//            viewHolder.State     = view.findViewById(R.id.tv_State);
//            viewHolder.imgIcon = view.findViewById(R.id.imgIcon);
//            viewHolder.dt2 = view.findViewById(R.id.tv_temp2);
//            viewHolder.temp2 = view.findViewById(R.id.tv_dt2);
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) view.getTag();
//        }
//        Weather weather = list.get(i);
//        viewHolder.dt.setText(weather.getDt());
//        viewHolder.State.setText(weather.getDescription());
////        viewHolder.dt2.setText(weather.getDt2());
//        viewHolder.temp2.setText(weather.getTemp2());
//        return view;
//    }
//}

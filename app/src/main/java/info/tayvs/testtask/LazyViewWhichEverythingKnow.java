package info.tayvs.testtask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedHashMap;

/**
 * Created by tayvs on 30.11.2016.
 */

public class LazyViewWhichEverythingKnow extends BaseAdapter {

    ///All you need is here Pam-Pa-pa-pa-pam
    ///All you need is here (pause) here
    ///Here is all you need

    private Context context;
    private LayoutInflater layoutInflater;
    private int count;

    //Some Cache variables;
    private int maxCacheSize = 50;
    private LinkedHashMap<Integer, User> cache = new LinkedHashMap<>();

    DBHelper dbHelper;


    public LazyViewWhichEverythingKnow(Context context){
        this.context = context;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        dbHelper = new DBHelper(context);
        count = dbHelper.countOFDatabaseRaws();
    }

    public void setMaxCacheSize(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public Object getItem(int position) {
        User item = cache.get(position + 1);
        if (item == null) {
            item = dbHelper.selectByID(position);

            if (cache.size() == maxCacheSize) cache.clear(); //TODO create something better
            cache.put(position, item);
        }
        return item.toString();
    }

    @Override
    public long getItemId(int position) {
        return position + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView item;
        if (convertView != null) {
            item = (TextView) convertView;
        } else {
            item = (TextView) layoutInflater.inflate(android.R.layout.simple_list_item_1, null);
        }

        item.setText(getItem(position + 1).toString());

        return item;
    }

}

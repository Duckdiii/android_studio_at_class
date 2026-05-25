package com.example.practiceday1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private final Context context;
    private List<Phone> items;

    public GridAdapter(Context context, List<Phone> objects) {
        // REMOVED the super() call that was causing the error
        this.context = context;
        this.items = objects;
    }

    @Override
    public int getCount() {
        return items.size(); // Dùng .size() thay vì .length
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); // Dùng .get(position)
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // Ensure R.layout.grid_item exists in your res/layout folder
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.gridImage);
        TextView textView = convertView.findViewById(R.id.gridText);

        Phone currentPhone = items.get(position);
        textView.setText(currentPhone.getName() + " - $" + currentPhone.getPrice());

        if (currentPhone.getImg() != 0) {
            imageView.setImageResource(currentPhone.getImg());
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        return convertView;
    }
}
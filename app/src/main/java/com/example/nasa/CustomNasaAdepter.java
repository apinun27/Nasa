package com.example.nasa;


        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Color;
        import android.util.Base64;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;
public class CustomNasaAdepter extends ArrayAdapter<DataNasa> {

    Context mContext;
    ArrayList<DataNasa> data;
    private static class ViewHolder {
        TextView t1,t2;
    }

    public CustomNasaAdepter( Context context,ArrayList<DataNasa> data){
        super(context, R.layout.listcustom, data);
        this.mContext=context;
        this.data=data;



    }



    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataNasa dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.listcustom, parent, false);
        viewHolder.t1 = (TextView) convertView.findViewById(R.id.t1);
        viewHolder.t2 = (TextView) convertView.findViewById(R.id.t2);
        lastPosition = position;
        viewHolder.t1.setText("date :"+dataModel.getDate());
        viewHolder.t2.setText("title :"+dataModel.getTitle());
        return convertView;
    }


}

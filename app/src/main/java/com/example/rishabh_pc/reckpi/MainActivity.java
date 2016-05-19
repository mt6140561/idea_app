package com.example.rishabh_pc.reckpi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements kpidet.OnFragmentInteractionListener{

    public String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        fullname = "Welcome "+bundle.getString("fullname", "Stranger");
        setContentView(R.layout.activity_main);
        TextView te = (TextView) findViewById(R.id.welc);
        final FragmentManager fm = getFragmentManager();
        te.setText(fullname);
        Button button = (Button) findViewById(R.id.butkpi);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isd = ((EditText)findViewById(R.id.isdn)).getText().toString();
                String url = "http://192.168.137.1:8000/reckpi/default/getkpi.json/"+isd;
                Log.d("this url", url);
                MyJsonRequest req = new MyJsonRequest(url, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            if(response.getBoolean("success")){
                                kpidet det = new kpidet();
                                fm.beginTransaction()
                                        .replace(R.id.detkpi, det.newInstance(converter(response.getJSONObject("kpi")))).addToBackStack(null)
                                        .commit();
                            }
                        } catch (Exception e){}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                Singleton.getInstance().addToRequestQueue(req);
            }
        });
    }

    public String[] converter(JSONObject json){
        String[] ret = new String[9];
        try {
            ret[0] = json.getString("lmtd_act");
            ret[1] = json.getString("mtd_act");
            ret[2] = json.getString("created_at");
            ret[3] = json.getString("vtoplast");
            ret[4] = json.getString("vtopcurr");
            ret[5] = json.getString("ftd_act");
            ret[6] = json.getString("commlast");
            ret[7] = json.getString("commcurr");
            ret[8] = json.getString("ret_msisdn");
        } catch (Exception e){

        }
        return ret;
    }

    @Override
    public void onFragmentInteraction(Uri uri){

    }
}

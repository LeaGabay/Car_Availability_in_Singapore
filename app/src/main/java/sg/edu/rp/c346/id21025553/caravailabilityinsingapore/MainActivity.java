package sg.edu.rp.c346.id21025553.caravailabilityinsingapore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    // Declaring variables
    ListView lvCar;
    Spinner spinner;
    Button lotsBtn;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking UI elements to variables
        lvCar = findViewById(R.id.lvCarAvailability);
        spinner = findViewById(R.id.spinner);
        lotsBtn = findViewById(R.id.lotTypeBtn);
        client = new AsyncHttpClient();

    }

    @Override
    protected void onResume(){
        super.onResume();

        ArrayList<CarAvailability> alCar = new ArrayList<CarAvailability>();
        ArrayList<CarAvailability> alCarLot = new ArrayList<CarAvailability>();
        ArrayList<String> alLots = new ArrayList<String>();
        alLots.add(0, "Filter by lot type");
        ArrayAdapter<String> aaLots = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, alLots);

        spinner.setAdapter(aaLots);
        spinner.setSelection(0);

        client.get("https://api.data.gov.sg/v1/transport/carpark-availability", new JsonHttpResponseHandler() {

            String carpark_number, lot_type;
            int lots_available, total_lots;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){

                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrData = firstObj.getJSONArray("carpark_data");

                    for (int i = 0; i < jsonArrData.length(); i++){
                        JSONObject jsonObjData = jsonArrData.getJSONObject(i);
                        carpark_number = jsonObjData.getString("carpark_number");
                        JSONArray jsonArrCarparkInfo = jsonObjData.getJSONArray("carpark_info");

                        for (int x = 0; x < jsonArrCarparkInfo.length(); x++){
                            JSONObject jsonObjInfo = jsonArrCarparkInfo.getJSONObject(x);
                            total_lots = Integer.parseInt(jsonObjInfo.getString("total_lots"));
                            lot_type = jsonObjInfo.getString("lot_type");
                            lots_available = Integer.parseInt(jsonObjInfo.getString("lots_available"));

                            CarAvailability carAvailability = new CarAvailability(carpark_number, total_lots, lot_type, lots_available);

                            if(!(alLots.contains(lot_type))){
                                alLots.add(lot_type);
                            }

                            if(spinner.getSelectedItem().toString().equals("Filter by lot type")){
                                alCar.add(carAvailability);
                            }
                            else if (spinner.getSelectedItem().toString().equals(lot_type)){
                                alCarLot.add(carAvailability);
                            }

                        }

                    }

                }
                catch (JSONException e){

                }
                ArrayAdapter<CarAvailability> aaCarLot = new ArrayAdapter<CarAvailability>(MainActivity.this, android.R.layout.simple_list_item_1, alCarLot);
                ArrayAdapter<CarAvailability> aaCar = new ArrayAdapter<CarAvailability>(MainActivity.this, android.R.layout.simple_list_item_1, alCar);

                if(spinner.getSelectedItem().toString().equals("Filter by lot type")){
                    lvCar.setAdapter(aaCar);
                } else {
                    lvCar.setAdapter(aaCarLot);
                }

                lotsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(spinner.getSelectedItem().toString().equals("Filter by lot type")){
                            Toast.makeText(MainActivity.this, "Please select a lot type", Toast.LENGTH_LONG);
                        } else if (spinner.getSelectedItem().toString().equals("View all lot types")) {
                            alLots.set(0, "Filter by lot type");
                            alCar.clear();
                            alCarLot.clear();

                            try {
                                JSONArray jsonArrItems = response.getJSONArray("items");
                                JSONObject firstObj = jsonArrItems.getJSONObject(0);
                                JSONArray jsonArrData = firstObj.getJSONArray("carpark_data");

                                for (int i = 0; i < jsonArrData.length(); i++){
                                    JSONObject jsonObjData = jsonArrData.getJSONObject(i);
                                    carpark_number = jsonObjData.getString("carpark_number");
                                    JSONArray jsonArrCarparkInfo = jsonObjData.getJSONArray("carpark_info");

                                    for (int x = 0; x < jsonArrCarparkInfo.length(); x++){
                                        JSONObject jsonObjInfo = jsonArrCarparkInfo.getJSONObject(x);
                                        total_lots = Integer.parseInt(jsonObjInfo.getString("total_lots"));
                                        lot_type = jsonObjInfo.getString("lot_type");
                                        lots_available = Integer.parseInt(jsonObjInfo.getString("lots_available"));

                                        CarAvailability carAvailability = new CarAvailability(carpark_number, total_lots, lot_type, lots_available);

                                        if(!(alLots.contains(lot_type))){
                                            alLots.add(lot_type);
                                        }

                                        alCarLot.add(carAvailability);

                                    }

                                }

                            }
                            catch (JSONException e){

                            }

                        } else {
                            alCar.clear();
                            alCarLot.clear();
                            try {
                                JSONArray jsonArrItems = response.getJSONArray("items");
                                JSONObject firstObj = jsonArrItems.getJSONObject(0);
                                JSONArray jsonArrData = firstObj.getJSONArray("carpark_data");

                                for (int i = 0; i < jsonArrData.length(); i++){
                                    JSONObject jsonObjData = jsonArrData.getJSONObject(i);
                                    carpark_number = jsonObjData.getString("carpark_number");
                                    JSONArray jsonArrCarparkInfo = jsonObjData.getJSONArray("carpark_info");

                                    for (int x = 0; x < jsonArrCarparkInfo.length(); x++){
                                        JSONObject jsonObjInfo = jsonArrCarparkInfo.getJSONObject(x);
                                        total_lots = Integer.parseInt(jsonObjInfo.getString("total_lots"));
                                        lot_type = jsonObjInfo.getString("lot_type");
                                        lots_available = Integer.parseInt(jsonObjInfo.getString("lots_available"));

                                        CarAvailability carAvailability = new CarAvailability(carpark_number, total_lots, lot_type, lots_available);

                                        if(!(alLots.contains(lot_type))){
                                            alLots.add(lot_type);
                                        }

                                        alCarLot.add(carAvailability);

                                    }

                                }

                            }
                            catch (JSONException e){

                            }
                            alLots.set(0, "View all lot types");
                        }

                        aaCar.notifyDataSetChanged();
                        aaCarLot.notifyDataSetChanged();

                    }
                });

            } // end onSuccess

        });
    } // end onResume
}
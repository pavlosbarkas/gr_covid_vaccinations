package com.example.barkas_pavlos_18022_androidjan21;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchDataTask extends AsyncTask<String, Void, List<DataEntry>> {

    private final String TAG = "CORONA";

    private DataAdapter dataAdapter;

    public FetchDataTask(DataAdapter dataAdapter){this.dataAdapter = dataAdapter;}

    private List<DataEntry> getDataFromJSON(String dataJSONString) throws JSONException{
        final String areaKey = "area";
        final String daydiffKey = "daydiff";
        final String daytotalKey = "daytotal";
        final String totalvaccinationsKey = "totalvaccinations";

        JSONArray dataArray = new JSONArray(dataJSONString);

        List<DataEntry> dataEntries = new ArrayList<>();
        for (int i = 0; i <dataArray.length(); i++) {
            DataEntry dataEntry = new DataEntry();
            JSONObject jsonEntry = (JSONObject) dataArray.get(i);
            dataEntry.setArea(jsonEntry.getString(areaKey));
            dataEntry.setDaydiff(jsonEntry.getString(daydiffKey));
            dataEntry.setDaytotal(jsonEntry.getString(daytotalKey));
            dataEntry.setTotalVaccinations(jsonEntry.getString(totalvaccinationsKey));

            Log.d(TAG, "Data Entry: " + dataEntry);

            dataEntries.add(dataEntry);
        }
        return dataEntries;
    }

    @Override
    protected List<DataEntry> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        String baseUrl = "https://data.gov.gr/api/v1/query/mdg_emvolio";

        try{

            Uri builtUri = Uri.parse(baseUrl).buildUpon()
                    .appendQueryParameter("date_from","2021-01-21")
                    .appendQueryParameter("date_to", "2021-01-31")
                    .build();

            URL url = new URL(builtUri.toString());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Token 08a68376e2b0409fccd7414b48829eedb7779ef9");
            connection.connect();
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();

                if (inputStream == null){
                    return new ArrayList<>();
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while((line = reader.readLine()) != null){
                    stringBuilder.append(line).append("\n");
                }
            }
            if (stringBuilder.length() == 0) {
                return new ArrayList<>();
            }
            String dataJSONString = stringBuilder.toString();
            Log.d(TAG, "DATA RECEIVED: " + dataJSONString);

            return getDataFromJSON(dataJSONString);

        }catch(Exception e){
            Log.d(TAG, "ERROR: ", e);
            return new ArrayList<>();
        }finally {
            if (connection!=null) {
                connection.disconnect();
            }
            if (reader != null) {
                try{
                    reader.close();
                }catch(final IOException e){
                    Log.d(TAG, "ERROR CLOSING STREAM, e");
                }
            }
        }
    }

    @Override
    protected void onPostExecute(List<DataEntry> dataEntries) {
        dataAdapter.setDataEntries(dataEntries);
    }
}
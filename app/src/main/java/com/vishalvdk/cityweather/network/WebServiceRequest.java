package com.vishalvdk.cityweather.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by vdkale on 14/10/15.
 */
public class WebServiceRequest extends AsyncTask<URL,Void,String>{

    private static final String TAG = "WebServiceRequest";
    private Context context;
    private ResponseListener responseListener;

    public WebServiceRequest(Context context, ResponseListener responseListener) {
        this.context = context;
        this.responseListener = responseListener;
    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }

    @Override
    protected String doInBackground(URL... params) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) params[0].openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseString = readStream(urlConnection.getInputStream());
                return responseString;
            } else {
                Log.v(TAG, "Response code:" + responseCode);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if(result!=null){
            Log.v(TAG, result);
            responseListener.onSuccess(result);
        }else{
            responseListener.onError();
        }
    }

    public interface ResponseListener {
        void onSuccess(String response);
        void onError();
    }
}

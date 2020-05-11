package com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils;



import android.os.AsyncTask;

import com.example.ahmad_afaq_s1625481.mpdcoursework_aa.utils.XmlToJavaParser;
/*  Created by: Afaq Ahmad
        Student number: S1625481
*/
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HttpRequest extends AsyncTask<String, Void, ArrayList<?>> {
    private String url;
    private XmlToJavaParser xmlParser;
    private Async async;

    public HttpRequest(String url, XmlToJavaParser xmlParser, Async async) {
        this.url = url;
        this.xmlParser = xmlParser;
        this.async = async;
    }

    @Override
    protected ArrayList<?> doInBackground(String... urls) {
        HttpURLConnection conn;
        try {
            URL url = new URL(this.url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream is = conn.getInputStream();



            return this.xmlParser.xmlParser(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(ArrayList<?> list) {
        super.onPostExecute(list);
        async.processFinish(list);
    }
}

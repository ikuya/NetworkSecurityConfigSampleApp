package com.anif.networksecurityconfigsampleapp;

import android.os.AsyncTask;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by if on 2016/12/06.
 */
public class HTTPSGet extends AsyncTask<String, Void, Object> {


    public HTTPSGet(View.OnClickListener ctx) {
    }

    @Override
    protected Object doInBackground(String... params) {
        BufferedInputStream is = null;
        ByteArrayOutputStream os = null;
        byte[] buff = new byte[1024];
        int length;

        try {
            URL url = new URL(params[0]);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            is = new BufferedInputStream(conn.getInputStream());
            os = new ByteArrayOutputStream();
            while ((length = is.read(buff)) != -1) {
                if (length > 0) os.write(buff, 0, length);
            }
            return os.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e;
        } catch (IOException e) {
            e.printStackTrace();
            return e;
        } finally {
            try {
                if (is != null) is.close();
                if (os != null) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

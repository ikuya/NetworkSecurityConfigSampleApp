package com.anif.networksecurityconfigsampleapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mUrlBox;
    private TextView mTextView;
    private ImageView mImageView;

    private AsyncTask<String, Void, Object> mAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUrlBox = (EditText) findViewById(R.id.urlBox);
        mTextView = (TextView) findViewById(R.id.msgView);
        mImageView = (ImageView) findViewById(R.id.imageView);

        final Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url =  mUrlBox.getText().toString();
                mImageView.setImageBitmap(null);

                if (mAsyncTask != null) mAsyncTask.cancel(true);

                mAsyncTask = new HTTPSGet(this) {
                    @Override
                    protected void onPostExecute(Object o) {
                        if (o instanceof Exception) {
                            Exception e = (Exception) o;
                            mTextView.append("Exception!\n" + e.toString());
                        } else {
                            byte[] data = (byte[]) o;
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            mImageView.setImageBitmap(bitmap);
                        }
                    }
                }.execute(url);
            }
        });
    }

}

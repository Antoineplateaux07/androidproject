package com.example.android;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    TextView txtString;
    Button synchronousGet;
    ImageButton  asynchronousPOST ,asynchronousPOSTNO;

    public String url = "https://api-project-1026902595480.firebaseio.com/ACTUATOR5_STATE.json";
    public String postUrl = "https://api-project-1026902595480.firebaseio.com/ACTUATOR5_STATE.json";
//    public String postBody = "{\n" +
//            "    \"name\": \"morpheus\",\n" +
//            "    \"job\": \"leader\"\n" +
//            "}";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        synchronousGet = (Button) findViewById(R.id.synchronousGet);
        asynchronousPOST = (ImageButton) findViewById(R.id.asynchronousPost);
        asynchronousPOSTNO = (ImageButton) findViewById(R.id.asynchronousPostNo);

        synchronousGet.setOnClickListener(this);
        asynchronousPOST.setOnClickListener(this);
        asynchronousPOSTNO.setOnClickListener(this);

        txtString = (TextView) findViewById(R.id.txtString);
    }

    void postRequest(String postUrl) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json,text/html");

        RequestBody body = RequestBody.create(mediaType, "{\n\t\"ACTUATOR5_STATE\":1\n}");


        Request request = new Request.Builder()
                .url(postUrl)
                .method("PATCH", body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("TAG", response.body().string());
            }
        });

    }


    void postRequestNo(String postUrl) throws IOException {

        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json,text/html");

        RequestBody body = RequestBody.create(mediaType, "{\n\t\"ACTUATOR5_STATE\":0\n}");


        Request request = new Request.Builder()
                .url(postUrl)
                .method("PATCH", body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("TAG", response.body().string());
            }
        });

    }




    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                Main2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            JSONObject json = new JSONObject(myResponse);
                            txtString.setText("Active: "+json.getJSONObject("data").getString("ACTUATOR5_STATE"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.synchronousGet:
                OkHttpHandler okHttpHandler = new OkHttpHandler();
                okHttpHandler.execute(url);

                break;
            case R.id.asynchronousPost:
                try {
                    postRequest(postUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                okHttpHandler = new OkHttpHandler();
                okHttpHandler.execute(url);
                break;

            case R.id.asynchronousPostNo:
                try {
                    postRequestNo(postUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                okHttpHandler = new OkHttpHandler();
                okHttpHandler.execute(url);
                break;

        }
    }

    public class OkHttpHandler extends AsyncTask<String, Void, String> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... params) {

            Request.Builder builder = new Request.Builder();
            builder.url(params[0]);
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
//                String result=response.body().string();
                 //   System.out.println(result);
                return response.body().string();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtString.setText(s);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(url);

    }

    public void page2(View view){
        startActivity(new Intent(this, MainActivity.class));
    }
}


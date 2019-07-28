package com.example.cfrob;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.cfrob.QRcode.codeActivity;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class robActivity extends AppCompatActivity {

    private TextView ct;
    private Button btn;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rob);

        ct = (TextView)findViewById(R.id.Customer_text);
        btn = (Button)findViewById(R.id.button_start);

        btn.setOnClickListener(new ClickToStart());
        Log.i("flag_233",flag+"");


    }

    class ClickToStart implements View.OnClickListener{
        @Override
        public void onClick(View v){
            if(flag == 0){
                Intent intent = new Intent(robActivity.this, codeActivity.class);
                startActivityForResult(intent,1);
            }else if (flag ==1){
                Intent intent = new Intent(robActivity.this,selectDrinkActivity.class);
                startActivityForResult(intent,2);
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK){
                    String returnedData = data.getStringExtra("dataRTN");
                    ct.setText(returnedData+" robot is running for you now");
                    btn.setText("select your drink here");
                    flag=1;
                    Log.i("flag_233",flag+" selecting_drink_");
                    Toast.makeText(getApplicationContext(),"please select your drink now",Toast.LENGTH_LONG).show();
                }
                break;
            case 2:
                if (resultCode==RESULT_OK){
                    //插入付款方式的activity
                    final String returnedData = data.getStringExtra("selected");
                    ct.setText("you have selected drink "+ returnedData);
                    btn.setText("Robot is running, no touchy touchy");
                    btn.setEnabled(false);
                    //传递信息给服务器
                    //就是这

                    AsyncTask asyncTask = new AsyncTask() {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            OkHttpClient okHttpClient = new OkHttpClient();
                            RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), returnedData);
                            Request request = new Request.Builder()
                                    .url("http://192.168.43.214:6666/test")
                                    .post(requestBody)
                                    .build();
                            Response response = null;

                            try{
                                response = okHttpClient.newCall(request).execute();
                                return response.body().string();

                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void  onPostExecute(Object o){
                            ct.setText(o.toString());
                        }
                    }.execute();

                }
                break;
        }

    }




//    public String sendData(String order){
//        try {
//            OkHttpClient client = new OkHttpClient();
//
//            RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), order);
//
//            Request request = new Request.Builder()
//                    .url("http://10.0.2.2:6666/test")
//                    .post(requestBody)
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            return response.body().string();
//        } catch (IOException e) {
//            return "Error: " + e.getMessage();
//        }
//    }


}
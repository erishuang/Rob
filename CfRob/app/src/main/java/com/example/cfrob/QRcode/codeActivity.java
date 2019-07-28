package com.example.cfrob.QRcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cfrob.R;

public class codeActivity extends AppCompatActivity {

    private EditText robtxt;
    private Button btn_for_rob;
    private String robotCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        robtxt = (EditText)findViewById(R.id.robotName);
        btn_for_rob = (Button)findViewById(R.id.buttonConfirm);
        btn_for_rob.setOnClickListener(new ClicktoConfirm());
    }
    class ClicktoConfirm implements View.OnClickListener{
        @Override
        public void onClick(View v){
            robotCode = robtxt.getText().toString();
            if (robotCode.equals("QR")){
                Intent intent = getIntent();
                intent.putExtra("dataRTN","QR");
                setResult(RESULT_OK,intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(),"No machine found",Toast.LENGTH_LONG).show();
            }
        }
    }


}

package com.example.cfrob;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class selectDrinkActivity extends AppCompatActivity {

    private RadioGroup drinkgp;
    private Button confirmBtn;
    private String selectedDrink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_drink);

        confirmBtn = (Button)findViewById(R.id.confirm);
        drinkgp = (RadioGroup)findViewById(R.id.drink_select);


        confirmBtn.setOnClickListener(new selectDrink());
    }
    class selectDrink implements View.OnClickListener{
        @Override
        public void onClick(View v){
            int c = drinkgp.getCheckedRadioButtonId();
            //如果是-1就是什么都没选
            int bar = c%3;
            String drink = "";

            if (bar ==1){drink = "Cappuccino";}else if (bar ==2){drink="Americano";};

            Log.i("flag_2334",bar+" selecting_drink_");
            Log.i("buttonid",c+"");


            Intent intent = getIntent();
            intent.putExtra("selected"," "+bar+" "+drink);
            setResult(RESULT_OK,intent);
            finish();
        }

    }
}

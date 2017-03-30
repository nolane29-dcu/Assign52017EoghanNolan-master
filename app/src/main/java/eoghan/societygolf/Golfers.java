package eoghan.societygolf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Golfers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_golfers);

        //create the link to the button in the interface

        Button btngolfer = (Button) findViewById(R.id.btnNewGolfer);
        btngolfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoAddGolf = new Intent(Golfers.this, AddGolfer.class);
                startActivity(gotoAddGolf);
            }
        });
    }

}

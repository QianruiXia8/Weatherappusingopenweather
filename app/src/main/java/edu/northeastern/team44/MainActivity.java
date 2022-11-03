package edu.northeastern.team44;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button atYourService;
    Button stickItToEm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        atYourService = findViewById(R.id.atYourServiceButton);
        stickItToEm = findViewById(R.id.b2);

        atYourService.setOnClickListener(view -> {
            Intent atYourServiceIntent = new Intent(view.getContext(), AtYourService.class);
            view.getContext().startActivity(atYourServiceIntent);
        });
        stickItToEm.setOnClickListener(view -> {
            Intent stickItToEmIntent = new Intent(view.getContext(), Stick_It_To_Em.class);
            view.getContext().startActivity(stickItToEmIntent);
        });
    }


}
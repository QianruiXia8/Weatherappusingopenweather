package edu.northeastern.team44;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button atYourService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        atYourService = findViewById(R.id.atYourServiceButton);

        atYourService.setOnClickListener(view -> {
            Intent aboutMeIntent = new Intent(view.getContext(), AtYourService.class);
            view.getContext().startActivity(aboutMeIntent);
        });
    }


}
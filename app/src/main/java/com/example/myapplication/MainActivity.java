package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.SurfaceView;

import android.widget.TextView;





public class MainActivity extends AppCompatActivity {


    private SurfaceView camera;

    private TextView text;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        camera = (SurfaceView)findViewById(R.id.Camera);
        text= (TextView)findViewById(R.id.Resultado);


        CamerQR_Redirect cameraqrope = new CamerQR_Redirect(MainActivity.this,camera, text );
        cameraqrope.QRcodeCamera();




    }



}













package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;


public class MainActivity extends AppCompatActivity {


    private SurfaceView camera;

    private TextView text;

    private BarcodeDetector detector; //lector qr
    protected CameraSource cameraSource; //Activar camara


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        camera = (SurfaceView) findViewById(R.id.Camera);
        text = (TextView) findViewById(R.id.Resultado);


//        CamerQR_Redirect cameraqrope = new CamerQR_Redirect(MainActivity.this,camera, text );
        /*cameraqrope.*/
        NotificacionesQR notificacionesQR = new NotificacionesQR(this);
        notificacionesQR.Empezar();
        QRcodeCamera();



    }

    public void QRcodeCamera() {
        Toast.makeText(getApplicationContext(), "CameraHolderIncio", LENGTH_LONG).show();
        detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.QR_CODE).build();


        cameraSource = new CameraSource.Builder(getApplicationContext(), detector).setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();


        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1000);

        }

        camera.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override

            public void surfaceCreated(SurfaceHolder holder) {

                Toast.makeText(getApplicationContext(), "CameraHolderIncio", LENGTH_LONG).show();
                try {

                    if (cameraSource.getCameraFacing() >= 0) {
                        cameraSource.getCameraFacing();
                        try {
                            cameraSource.start(camera.getHolder());
                        } catch (Exception ei) {

                        }

                    } else {
                        makeText(getApplicationContext(), "No se puedo concetar con la camara", LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    makeText(getApplicationContext(), e.toString(), LENGTH_LONG).show();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();

            }
        });


        detector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {

                SparseArray<Barcode> QRCode = detections.getDetectedItems();


                if (QRCode.size() > 0) {
                    //text.setText(QRCode.valueAt(0).displayValue);
                    String link = QRCode.valueAt(0).displayValue;
                    Intent i = new Intent(MainActivity.this, wView.class);
                    i.putExtra("link", link);
                    Log.i("petQR", link);
                    startActivity(i);
                }
            }
        });


    }
}













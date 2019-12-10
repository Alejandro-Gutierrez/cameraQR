package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import static android.widget.Toast.*;

public class CamerQR_Redirect extends AppCompatActivity {

    private BarcodeDetector detector; //lector qr
    private  SurfaceView camera; //Instansia del view Camera
    protected CameraSource cameraSource ; //Activar camara
    private TextView text; //Resultado del QR
    private String url=null;

    private Activity activity;

    public CamerQR_Redirect(Activity activityM,  SurfaceView cameraM, TextView textM) {

        camera = cameraM;
        activity=activityM;
        text = textM;





    }


    public void QRcodeCamera(){
        Toast.makeText(activity, "CameraHolderIncio", LENGTH_LONG).show();
        detector = new BarcodeDetector.Builder(this.activity)
                .setBarcodeFormats(Barcode.QR_CODE).build();



        cameraSource = new CameraSource.Builder(this.activity, detector).setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .setRequestedPreviewSize(1600, 1024)
                .build();


        if(ContextCompat.checkSelfPermission(this.activity, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this.activity, new String[]{ Manifest.permission.CAMERA}, 1000);

        }

        camera.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override

            public void surfaceCreated(SurfaceHolder holder) {

                Toast.makeText(activity, "CameraHolderIncio", LENGTH_LONG).show();
                try {

                    if(cameraSource.getCameraFacing()>= 0){
                        cameraSource.getCameraFacing();
                        try {
                            cameraSource.start(camera.getHolder());
                        }catch (Exception ei){

                        }

                    }else{
                        makeText(activity, "No se puedo concetar con la camara", LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    makeText(activity, e.toString(), LENGTH_LONG).show();
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


                if(QRCode.size()>0){
                    text.setText(QRCode.valueAt(0).displayValue);
                    Intent i = new Intent(activity, Page.class);

                    startActivity(i);
                }
            }
        });

    }







}

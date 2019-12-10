package com.example.myapplication;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificacionesQR  extends AppCompatActivity {

    private final static String CHANNEL_ID= "NOFITICATION";
    private final static int NOFITICATION_id = 0;



    private Activity activity;

    public NotificacionesQR(Activity actividad) {
        activity=actividad;

    }

    public void IniciarNotification(){
        Time tiempo = new Time();
        tiempo.execute();
    }


    public void Hilo(){
        try {
            Thread.sleep(3000);
        }catch(InterruptedException e){


        }
    }

    public void Empezar(){


        Time tiempo = new Time();
        Toast.makeText(activity, "sirveeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", Toast.LENGTH_SHORT).show();
        tiempo.execute();



    }

    public class Time extends AsyncTask<Void, Integer, Boolean>
    {


        @Override
        protected Boolean doInBackground(Void... voids) {
            for(int i=1; i<=3; i++){

                Hilo();
            }
            return true;
        }

        protected  void onPostExecute(Boolean aBoolean){
            Empezar();




        }


    }

    private  void Canal(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence Nombre = "notificacion";
            NotificationChannel NotificacionCanal = new NotificationChannel(CHANNEL_ID, Nombre, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager Notificacionmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Notificacionmanager.createNotificationChannel(NotificacionCanal);
        }
    }

    public void Notificaciones(){
        NotificationCompat.Builder Construir = new NotificationCompat.Builder(activity, CHANNEL_ID);
        Construir.setSmallIcon(R.drawable.ic_launcher_background);
        Construir.setContentTitle("Notificacion android");
        Construir.setContentText("Apuntate aqui");
        Construir.setColor(Color.GREEN);
        Construir.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        Construir.setLights(Color.MAGENTA, 100, 1000);
        Construir.setVibrate(new long[]{1000, 1000, 1000, 1000});
        Construir.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat NotificacionConstruir = NotificationManagerCompat.from(activity);
        NotificacionConstruir.notify(NOFITICATION_id, Construir.build());
    }
}

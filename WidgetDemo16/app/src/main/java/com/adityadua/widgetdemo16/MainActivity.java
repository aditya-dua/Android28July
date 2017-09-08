package com.adityadua.widgetdemo16;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by AdityaDua on 04/09/17.
 */

public class MainActivity extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i =0;i<appWidgetIds.length;i++){
            int currentId = appWidgetIds[i];

            String url ="http://www.acadgild.com";

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse(url));

            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);
            RemoteViews view = new RemoteViews(context.getPackageName(),R.layout.activity_main);

            view.setOnClickPendingIntent(R.id.button,pendingIntent);
            appWidgetManager.updateAppWidget(currentId,view);
            Toast.makeText(context, "Widget has been added", Toast.LENGTH_SHORT).show();




        }

    }
}

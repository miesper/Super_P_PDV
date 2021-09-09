package com.superp.superppdv_consulta;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        int status = NetworkUtil.getConnectivityStatusString(context);
        Log.e("teste", "network");
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()))
        {
            if (status != 1) {
                Log.e("Network", "saiu do wifi");
                Toast.makeText(
                        context,
                        "Aplicativo foi fechado por erro de conexão ao wifi, servidor indisponível",
                        Toast.LENGTH_LONG).show();

                Intent i = new Intent(context, FullscreenActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags (Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.putExtra("close_activity",true);
                context.startActivity(i);
            }
        }
    }
}
package com.finkevolution.thecard.ExternalServices;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import java.nio.charset.Charset;

/**
 * Created by alexander on 2017-06-28.
 */

public class NFC extends Thread{
    private NfcManager nfcManager;
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private Activity activity;
    private int test = 0;


    public NFC (Activity activity){
       this.activity = activity;
    }

    public void readFromTag(Intent intent) {
        if(intent!=null && NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())){
            Parcelable[] ndefMessages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            for(Parcelable message : ndefMessages) {
                NdefRecord[] records = ((NdefMessage)message).getRecords(); for(NdefRecord record : records){
                    String msg = new String(record.getPayload(), Charset.forName("ISO-8859-1"));
                    Log.d("THE MESSAGE", msg);
                    Toast.makeText(activity, "THE MESS", Toast.LENGTH_SHORT).show();
                } }
        }

    }




    public void resolveIntent(Intent intent){
        String tag_id = "";
        String action = intent.getAction();
        if (intent != null && action.equals(NfcAdapter.ACTION_TAG_DISCOVERED)
                || action.equals(NfcAdapter.ACTION_TECH_DISCOVERED)
                || action.equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            Log.d("NFC Tag", "Read");
            //    clearValues();
            // Hämta NFC taggen
        }

        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG); // Hämta dess id (UID, hexadecimal)
        byte[] id = tag.getId();
        if (id != null) {
            tag_id = bytesToHex(id);
            Log.d("ID", tag_id);

            // Skriv ut ID i ditt gränssnitt
        } else {
            // Inget id...
        }
        // Hämta all teknologi som stöds i taggen

            readFromTag(intent);

        test++;
        Toast.makeText(activity,test + "",Toast.LENGTH_SHORT).show();

    }

    final protected static char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
}

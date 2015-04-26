package com.codepath.beacon.events;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.UUID;

public class InstallationID {
    public static InstallationID INSTANCE;
    private String mID = null;
    private static final String INSTALLATION = "INSTALLATION";

    public static synchronized void initialize(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new InstallationID(context);
        }
    }

    private InstallationID(Context context) {
        createId(context);
    }

    public String getID() {
        return mID;
    }

    private synchronized String createId(Context context) {
        if (mID == null) {
            File installation = new File(context.getFilesDir(), INSTALLATION);
            try {
                if (!installation.exists()) {
                    writeInstallationFile(context, installation);
                }
                mID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return mID;
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static String generateID(Context context) {
        TelephonyManager tm = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        String uniqueId = tm.getDeviceId();
        if (TextUtils.isEmpty(uniqueId)) {
            uniqueId = UUID.randomUUID().toString();
        }
        return uniqueId;

    }

    private static void writeInstallationFile(Context context, File installation)
            throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = generateID(context);
        out.write(id.getBytes());
        out.close();
    }
}
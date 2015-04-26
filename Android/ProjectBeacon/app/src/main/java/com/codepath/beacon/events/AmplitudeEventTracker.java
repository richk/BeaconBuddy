package com.codepath.beacon.events;

import android.content.Context;
import android.util.Log;

import com.amplitude.api.Amplitude;
import com.codepath.beacon.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class AmplitudeEventTracker implements EventTracker {
    private static final String TAG = AmplitudeEventTracker.class.getSimpleName();
    private static AmplitudeEventTracker mEventTracker;
    private static final String AMPLITUDE_DEVICE_ID_KEY = "device_id";
    private static final String AMPLITUDE_DEVICE_KEY = "2f82511dce18aac5ca45d87b921fd3de";

    private AmplitudeEventTracker() {

    }

    private void setAmplitudeDeviceId() {
        JSONObject userProperties = new JSONObject();
        try {
            userProperties.put(AMPLITUDE_DEVICE_ID_KEY, InstallationID.INSTANCE.getID());
        } catch (JSONException exception) {}
        Amplitude.getInstance().setUserProperties(userProperties);
    }

    public static void initialize(Context context) {
        Amplitude.getInstance().initialize(context, AMPLITUDE_DEVICE_KEY);
        mEventTracker = new AmplitudeEventTracker();
    }

    public static final AmplitudeEventTracker getInstance() {
        return mEventTracker;
    }

    @Override
    public void track(String eventName) {
        if (isDebugBuild()) {
            Log.i(TAG, "Debug build. Not logging event");
            return;
        }
        Amplitude.getInstance().logEvent(eventName);
    }

    @Override
    public void track(String name, EventProperty properties[]) {
        if (isDebugBuild()) {
            Log.i(TAG, "Debug build. Not logging event");
            return;
        }
        if (properties == null || properties.length == 0) {
            track(name);
            return;
        }
        JSONObject props = getJsonFromProps(properties);

        JSONObject eventProperties = new JSONObject();
        try {
            for (EventProperty eventProperty : properties) {
                if (eventProperty.value != null) {
                    eventProperties.put(eventProperty.key, eventProperty.value);
                }
            }
        } catch (JSONException exception) {}
        Amplitude.getInstance().logEvent(name, eventProperties);
    }

    private JSONObject getJsonFromProps (EventProperty properties[]) {
        JSONObject props = new JSONObject();
        try {
            for (EventProperty p : properties) {
                if (p.value != null) {
                    props.put(p.key, p.value);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return props;
    }


    @Override
    public void startSession() {
        if (isDebugBuild()) {
            Log.i(TAG, "Debug build. Not logging event");
            return;
        }
        Amplitude.getInstance().startSession();
    }

    @Override
    public void endSession() {
        if (isDebugBuild()) {
            Log.i(TAG, "Debug build. Not logging event");
            return;
        }
        Amplitude.getInstance().endSession();
    }

    private boolean isDebugBuild() {
        return BuildConfig.DEBUG;
    }
}

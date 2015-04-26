package com.codepath.beacon.events;

public interface EventTracker {
    public void track(String eventName);
    public void track(String name, EventProperty properties[]);
    public void startSession();
    public void endSession();
}

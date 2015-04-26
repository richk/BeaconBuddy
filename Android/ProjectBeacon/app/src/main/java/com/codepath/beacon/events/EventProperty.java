package com.codepath.beacon.events;

public class EventProperty<T> {
    public final String key;
    public final T value;
    public EventProperty(String key, T value) {
        this.key = key;
        this.value = value;
    }
}
package com.example.sosknop;

public class Json {
    private String location;

    public Json (String location) {
        this.location = location;
    }

    // returns the json value
    public String getLocation() {
        return location;
    }

    //set the json value
    public String setLocation(String location) {
        return this.location = location;
    }
}

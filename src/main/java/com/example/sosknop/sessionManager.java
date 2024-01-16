package com.example.sosknop;

public class sessionManager {
    private static sessionManager instance;
    private int loggedInUserId;

    public static sessionManager getInstance() {
        if (instance == null) {
            instance = new sessionManager();
        }
        return instance;
    }

    // Creates a session
    public void createSession(int userId) {
        loggedInUserId = userId;
    }

    // Gets the loggin user id
    public  int getLoggedInUserId() {
        return loggedInUserId;
    }

    public void endSession() {
        loggedInUserId = 0; // Reset the session data

    }

    public boolean isUserLoggedIn() {
        return loggedInUserId != 0;
    }

}
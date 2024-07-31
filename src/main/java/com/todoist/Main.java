package com.todoist;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The Main class is the entry point of the Todoist Calendar application.
 * It creates an instance of the EventManager class and performs some operations on it.
 */

public class Main {
    public static void main(String[] args) throws IOException, GeneralSecurityException {
        // Create an instance of EventManager
        EventManager eventManager = new EventManager();
        
        // Call the createEvent method to create a new event
        eventManager.createEvent();
    }
}

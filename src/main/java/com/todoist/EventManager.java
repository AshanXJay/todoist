package com.todoist;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * The EventManager class is responsible for managing events in the application.
 * It provides methods to create events, save them to storage, and broadcast them.
 */
public class EventManager {
    private EventInput input; // Handles user input for events
    private EventStorage storage; // Handles storage of events
    private EventBroadcaster broadcaster; // Handles broadcasting of events

    /**
     * Constructor for EventManager.
     * Initializes the input, storage, broadcaster, and events list.
     */
    public EventManager() {
        input = new EventInput();
        storage = new EventStorage();
        broadcaster = new EventBroadcaster();
    }

    /**
     * Creates a new event by getting input from the user, saving it to storage, and broadcasting it.
     * @throws IOException if an I/O error occurs
     * @throws GeneralSecurityException if a security error occurs
     */
    public void createEvent() throws IOException, GeneralSecurityException {
        // Get event details from the user
        Event event = input.getEventFromUser();
        
        // If event is not null, save and broadcast it
        if (event != null) {
            storage.saveEvent(event); // Save event to storage
            broadcaster.broadcastEvent(event); // Broadcast the event
        } else {
            // If event is null, print a message
            System.out.println("Event not saved and broadcasted!");
        }
    }
}
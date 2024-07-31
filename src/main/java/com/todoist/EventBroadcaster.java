/**
 * 
 * READ THIS!!!!
 * 
 * This class is not complete and is provided as a reference for the implementation of the EventBroadcaster class.
 * I planned to send authentication request to users who wants to get events in their calendar but I haven't enough time and resources to complete it.
 * So, I make a localhost server and send the request to the user who use the software for show how works this software.
 * 
 * Please watch the video for see how works my software.
 * 
 */

package com.todoist;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * The EventBroadcaster class is responsible for broadcasting events to a Google Calendar.
 * It uses the Google Calendar API to authenticate and insert events into the calendar.
 **/

public class EventBroadcaster {

    private static final String APPLICATION_NAME = "todoist"; // Application name for Google Calendar API
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance(); // JSON factory instance
    private static final String TOKENS_DIRECTORY_PATH = "tokens"; // Directory to store user tokens
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR); // Required scopes for Google Calendar API
    private static final String CREDENTIALS_FILE_PATH = "extras\\credentials.json"; // Path to the credentials file

    /**
     * Broadcasts the given event to Google Calendar.
     *
     * @param event the event to be broadcasted
     * @throws IOException if an I/O error occurs
     * @throws GeneralSecurityException if a security error occurs
     */
    public boolean broadcastEvent(com.todoist.Event event) throws IOException, GeneralSecurityException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+05:30'"); // Date format for Google Calendar

        // Load client secrets from the credentials file
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(new FileInputStream(CREDENTIALS_FILE_PATH)));

        // Build flow and trigger user authorization request
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH))) // Set data store factory
                .setAccessType("offline") // Set access type to offline
                .build();

        // Set up local server receiver for authorization
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user"); // Authorize user

        // Build the calendar service
        Calendar service = new Calendar.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                credential)
                .setApplicationName(APPLICATION_NAME) // Set application name
                .build();

        // Create an event for Google Calendar
        com.google.api.services.calendar.model.Event googleEvent = new com.google.api.services.calendar.model.Event()
        .setSummary(event.getTitle()) // Set event title
        .setStart(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(dateFormat.format(event.getStartDate())))) // Set event start date
        .setEnd(new EventDateTime().setDateTime(new com.google.api.client.util.DateTime(dateFormat.format(event.getEndDate())))); // Set event end date

        // Insert the event into the calendar
        Event createdEvent = service.events().insert("primary", googleEvent).execute();

        // Print the ID of the created event
        System.out.println("Event created: " + createdEvent.getId());
        return true;
    }
}

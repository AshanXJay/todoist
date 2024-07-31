package com.todoist;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * The EventStorage class is responsible for saving and loading events to/from a file.
 */
public class EventStorage {

    public void saveEvent(Event event) {
        // Define the date format for saving the event dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        
        // Use try-with-resources to ensure the BufferedWriter is closed after use
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("events.txt", true))) {
            // Write the event title to the file
            writer.write("Title: " + event.getTitle() + "\n");
            // Write the event start date to the file
            writer.write("Start Date: " + dateFormat.format(event.getStartDate()) + "\n");
            // Write the event end date to the file
            writer.write("End Date: " + dateFormat.format(event.getEndDate()) + "\n");
            // Add a blank line to separate events
            writer.write("\n");
        } catch (IOException e) {
            // Print the stack trace if an I/O error occurs
            e.printStackTrace();
        }
    }

}

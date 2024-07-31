package com.todoist;

import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The EventInput class is responsible for getting user input to create an Event object.
 */
public class EventInput {
    private Scanner scanner; // Scanner object to read user input

    /**
     * Constructs a new EventInput object.
     * Initializes the scanner to read from the standard input.
     */
    public EventInput() {
        scanner = new Scanner(System.in);
    }

    /**
     * Gets event details from user input.
     * @return Event object with user-provided details.
     */
    public Event getEventFromUser() {
        String title;
        String[] dateTimeComponents = new String[4]; // Array to store date and time components

        // Prompt user for event title
        System.out.print("Enter event title: ");
        title = scanner.nextLine();

        // Prompt user for start date
        System.out.print("Enter start date (YYYYMMDD): ");
        dateTimeComponents[0] = scanner.nextLine();

        // Prompt user for start time
        System.out.print("Enter start time (HHMM): ");
        dateTimeComponents[1] = scanner.nextLine();

        // Prompt user for end date
        System.out.print("Enter end date (YYYYMMDD): ");
        dateTimeComponents[2] = scanner.nextLine();

        // Prompt user for end time
        System.out.print("Enter end time (HHMM): ");
        dateTimeComponents[3] = scanner.nextLine();

        return createEvent(title, dateTimeComponents);
    }

    /**
     * Creates an Event object using provided details.
     * @param title Event title.
     * @param dateTimeComponents Array containing start date, start time, end date, and end time.
     * @return Event object with provided details.
     */
    public Event createEvent(String title, String[] dateTimeComponents) {
        // Extract date and time components
        String startDateStr = dateTimeComponents[0];
        String startTimeStr = dateTimeComponents[1];
        String endDateStr = dateTimeComponents[2];
        String endTimeStr = dateTimeComponents[3];

        // Define the date format for parsing
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyyMMdd HHmm");
        Date startDate = null, endDate = null;

        try {
            // Parse the start date and time
            startDate = inputDateFormat.parse(startDateStr + " " + startTimeStr);
            // Parse the end date and time
            endDate = inputDateFormat.parse(endDateStr + " " + endTimeStr);
        } catch (ParseException e) {
            // Handle invalid date or time format
            System.out.println("Invalid date or time format.");
            return null; // Return null if parsing fails
        }

        // Print confirmation message
        System.out.println("Event Creation in progress...!");

        // Return a new Event object with the provided details
        return new Event(title, startDate, endDate); // Assuming Event constructor takes these parameters
    }
}
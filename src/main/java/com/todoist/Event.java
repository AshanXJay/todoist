package com.todoist;

import java.io.Serializable;
import java.util.Date;

/**
 * The Event class represents an event with a title, start date, and end date.
 * It implements the Serializable interface to support serialization.
 */
public class Event implements Serializable {

    private String title; // Title of the event
    private Date startDate; // Start date of the event
    private Date endDate; // End date of the event

    // Constructor to initialize the event with title, start date, and end date
    public Event(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter for the event title
    public String getTitle() {
        return title;
    }

    // Setter for the event title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for the event start date
    public Date getStartDate() {
        return startDate;
    }

    // Setter for the event start date
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Getter for the event end date
    public Date getEndDate() {
        return endDate;
    }

    // Setter for the event end date
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Override the toString method to provide a string representation of the event
    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
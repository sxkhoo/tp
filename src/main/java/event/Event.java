package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String description;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime, String location, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
    }

    // Getters and setters (omitted for brevity)
    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Event duplicate(String newName) {
        return new Event(newName, this.startTime, this.endTime, this.location, this.description);
    }

    @Override
    public String toString() {
        return String.format(
                "+----------------------+--------------------------------+\n" +
                "| Name                 | %s\n" +
                "| Start Time           | %s\n" +
                "| End Time             | %s\n" +
                "| Location             | %s\n" +
                "| Description          | %s\n" +
                "+----------------------+--------------------------------+",
                name,
                startTime.format(formatter),
                endTime.format(formatter),
                location,
                description
        );
    }
}

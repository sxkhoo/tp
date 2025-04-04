package commandfactory;

import java.time.LocalDateTime;

import command.AddEventCommand;
import command.Command;
import exception.SyncException;
import parser.CommandParser;
import participant.ParticipantManager;
import ui.UI;
import event.Event;

public class AddEventCommandFactory implements CommandFactory {
    private final ParticipantManager participantManager;
    private final UI ui;

    public AddEventCommandFactory(ParticipantManager participantManager, UI ui) {
        this.participantManager = participantManager;
        this.ui = ui;
    }

    public Command createCommand() throws SyncException {
        if (participantManager.getCurrentUser() == null) {
            throw new SyncException("You are not logged in. Please enter 'login' to login.");
        } else if (!participantManager.isCurrentUserAdmin()) {
            throw new SyncException("Only admin can create events!");
        } else {
            String input = CommandParser.readAddCommandInput();
            String[] parts = CommandParser.splitAddCommandInput(input);
            String name = parts[0].trim();
            LocalDateTime startTime = CommandParser.parseDateTime(parts[1]);
            LocalDateTime endTime = CommandParser.parseDateTime(parts[2]);

            assert startTime != null : "Start time should not be null";
            assert endTime != null : "End time should not be null";
            assert !endTime.isBefore(startTime) : "End time should not be before start time";

            String location = parts[3].trim();
            String description = parts[4].trim();

            assert !name.isEmpty() : "Event name should not be empty";
            assert !location.isEmpty() : "Event location should not be empty";
            assert !description.isEmpty() : "Event description should not be empty";

            Event newEvent = new Event(name, startTime, endTime, location, description);
            return new AddEventCommand(newEvent);
        }
    }
}

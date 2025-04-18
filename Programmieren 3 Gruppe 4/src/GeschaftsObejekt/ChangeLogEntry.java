package GeschaftsObejekt;

import GeschaftsObejekt.Musik;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class ChangeLogEntry {

    private LocalDateTime timestamp;
    private String action;
    private Musik originalState;
    private Musik newState;

    public ChangeLogEntry(String action, Musik originalState, Musik newState) {
        this.timestamp = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        this.action = action;
        this.originalState = originalState;
        this.newState = newState;
    }

    public ChangeLogEntry(LocalDateTime timestamp, String action, Musik originalState, Musik newState) {
        this.timestamp = timestamp;
        this.action = action;
        this.originalState = originalState;
        this.newState = newState;
    }

    // Getter
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getAction() {
        return action;
    }

    public Musik getOriginalState() {
        return originalState;
    }

    public Musik getNewState() {
        return newState;
    }

    // Setter
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setOriginalState(Musik originalState) {
        this.originalState = originalState;
    }

    public void setNewState(Musik newState) {
        this.newState = newState;
    }

    // timestamps werden beim Vergleich nicht beim vertgleich beachtet, um bei doppelten eingaben durch das set trotzdem nur einen Changelog aufzunehmen
    @Override
    public String toString() {
        return "ChangeLogEntry{"
                + "timestamp=" + timestamp
                + ", action='" + action + '\''
                + ", originalState=" + (originalState != null ? originalState.toString() : "null")
                + ", newState=" + (newState != null ? newState.toString() : "null")
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChangeLogEntry that = (ChangeLogEntry) o;
        return Objects.equals(action, that.action)
                && Objects.equals(originalState, that.originalState)
                && Objects.equals(newState, that.newState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, originalState, newState);
    }
}

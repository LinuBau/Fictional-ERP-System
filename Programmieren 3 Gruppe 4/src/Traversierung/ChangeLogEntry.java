
package Traversierung;

import GeschaftsObejekt.Musik;
import java.time.LocalDateTime;


public class ChangeLogEntry {
    private LocalDateTime timestamp;
    private String action;
    private Musik originalState;
    private Musik newState;

    public ChangeLogEntry(String action, Musik originalState, Musik newState) {
        this.timestamp = LocalDateTime.now(); 
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

    @Override
    public String toString() {
        return "ChangeLogEntry{" +
                "timestamp=" + timestamp +
                ", action='" + action + '\'' +
                ", originalState=" + (originalState != null ? originalState.toString() : "null") +
                ", newState=" + (newState != null ? newState.toString() : "null") +
                '}';
    }
}
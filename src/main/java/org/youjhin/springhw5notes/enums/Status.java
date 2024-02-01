package org.youjhin.springhw5notes.enums;

import lombok.Getter;

@Getter
public enum Status {
    //NOT_STARTED, IN_PROGRESS, COMPLETED, ALL


    NOT_STARTED("Not Started"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    ALL("All");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

}

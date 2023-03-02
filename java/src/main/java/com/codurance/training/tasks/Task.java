package com.codurance.training.tasks;

import java.util.Date;

public final class Task {
    private final String id;
    private final String description;
    private boolean done;
    private Date deadline;
    public Task(String id, String description, boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }
    private Date createdDate;

    public String getId() {
        return id;
    }
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return done;
    }
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

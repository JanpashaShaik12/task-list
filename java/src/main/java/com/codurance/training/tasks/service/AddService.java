package com.codurance.training.tasks.service;

import com.codurance.training.tasks.Task;

import java.util.List;

public interface AddService {
//    private String id;
//    private Date date;
//    private Data today;
    void add(String commandLine);
    void addProject(String name);
    void addTask(String taskId, String project, String description);

}

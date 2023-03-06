package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.CheckService;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckServiceImpl implements CheckService {
    private  Map<String, List<Task>> tasks ;
    private PrintWriter out;

    public CheckServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void check(String idString) {
        setDone(idString, true);
    }
    @Override
    public void uncheck(String idString) {
        setDone(idString, false);
    }
    @Override
    public void setDone(String idString, boolean done) {
        String id = idString;
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            for (Task task : project.getValue()) {
                if (task.getId() == id) {
                    task.setDone(done);
                    return;
                }
            }
        }
        out.printf("Could not find a task with an ID of %d.", id);
        out.println();
    }

}

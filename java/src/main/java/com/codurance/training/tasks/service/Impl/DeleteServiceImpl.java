package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.DeleteService;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeleteServiceImpl implements DeleteService {
    private Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private  PrintWriter out;
    public DeleteServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.out = out;
        this.tasks = tasks;
    }
    @Override
    public void deleteTask(String id){
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            project.getValue().removeIf(task -> task.getId().equals(id));
        }
        out.printf("Could not find a task with an ID of %s.", id);
        out.println();
    }
}

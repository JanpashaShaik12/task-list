package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Common;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskList;
import com.codurance.training.tasks.service.ViewService;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class ViewServiceImpl implements ViewService {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public ViewServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void viewByDate() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> Common.parseDate(p.getCreatedDate()));
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription(), Common.parseDate(task.getDeadline()));
            }
            out.println();
        }
    }
    @Override
    public void viewByDeadline() {
        Comparator<Task> compareByDate = Comparator.comparing(p -> Common.parseDate(p.getDeadline()));

        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            List<Task> newTasks = project.getValue();
            Collections.sort(newTasks, compareByDate);
            for (Task task : newTasks) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
    @Override
    public void viewByProject() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }
}

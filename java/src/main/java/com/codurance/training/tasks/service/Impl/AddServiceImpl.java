package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.AddService;

import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddServiceImpl implements AddService {
    private String id;
    private Date date;
    private Date today;
//    @Override
//    public void addDeadline(String id) {
//        this.id = id;
//
//    }
//
//    @Override
//    public List<Task> todayTasks() {
//        return null;
//    }
    private  Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private  PrintWriter out;

    public AddServiceImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks = tasks;
    }
    private boolean checkIdValidity(String id) {
        if(id.contains(" "))
            return false;

        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(id);
        return !(matcher.find());
    }
    @Override
    public void add(String commandLine) {
        String[] subcommandRest = commandLine.split(" ", 2);
        String subcommand = subcommandRest[0];
        if (subcommand.equals("project")) {
            addProject(subcommandRest[1]);
        } else if (subcommand.equals("task")) {
            String[] projectTask = subcommandRest[1].split(" ", 2);
            addTask(projectTask[0], projectTask[1],projectTask[2]);
        }
    }
    @Override
    public void addProject(String name) {
        tasks.put(name, new ArrayList<Task>());
    }
    @Override
    public void addTask(String taskId, String project, String description) {
        List<Task> projectTasks = tasks.get(project);
        if (projectTasks == null) {
            out.printf("Could not find a project with the name \"%s\".", project);
            out.println();
            return;
        }
        if(checkIdValidity(taskId))
            projectTasks.add(new Task(taskId, description, false));
        else
            out.println("ID should not contain spaces or special characters");
        projectTasks.add(new Task(taskId, description, false));
    }

}

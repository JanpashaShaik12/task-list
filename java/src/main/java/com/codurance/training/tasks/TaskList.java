package com.codurance.training.tasks;

import com.codurance.training.tasks.taskImpl.TaskRun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public final class TaskList  {
    private final Map<String, List<Task>> tasks;
    private final BufferedReader in;
    private final PrintWriter out;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        Map<String, List<Task>> tasks = new HashMap<>();
        new TaskList(tasks, in, out);
    }
    public TaskList(Map<String, List<Task>> tasks, BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        this.tasks = tasks;
        new TaskRun(this.out,this.in, this.tasks).run();
    }

}

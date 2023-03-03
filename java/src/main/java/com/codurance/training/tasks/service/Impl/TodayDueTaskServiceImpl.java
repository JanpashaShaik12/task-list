package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.TaskListUtil;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.TodayDueTaskService;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TodayDueTaskServiceImpl implements TodayDueTaskService {
    private PrintWriter out;
    private Map<String, List<Task>> tasks;

    public TodayDueTaskServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }
    @Override
    public void showDueTasksToday() {
        Date today = new Date();
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                if(task.getDeadline() != null && TaskListUtil.parseDate(task.getDeadline()).equals(TaskListUtil.parseDate(today)))
                    out.printf("    [%c] %s: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

}

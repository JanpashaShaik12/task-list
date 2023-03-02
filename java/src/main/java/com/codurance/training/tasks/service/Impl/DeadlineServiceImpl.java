package com.codurance.training.tasks.service.Impl;

import com.codurance.training.tasks.Common;
import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.service.DeadlineService;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeadlineServiceImpl  implements DeadlineService {
    private Map<String, List<Task>> tasks;
    private PrintWriter out;

    public DeadlineServiceImpl(Map<String, List<Task>> tasks, PrintWriter out) {
        this.tasks = tasks;
        this.out = out;
    }

    @Override
    public void addDeadline(String taskId, String deadline) {
        Date date=null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(deadline);
            for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
                for(Task task: project.getValue()) {
                    if(task.getId().equals(taskId)) {
                        task.setDeadline(date);
                    }
                }
            }
            out.printf("There is no task with the id provided");
            out.println();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }


    }
}

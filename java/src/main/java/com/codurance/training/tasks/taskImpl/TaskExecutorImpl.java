package com.codurance.training.tasks.taskImpl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskExecutor;
import com.codurance.training.tasks.service.*;
import com.codurance.training.tasks.service.Impl.*;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskExecutorImpl implements TaskExecutor {
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;
    private final AddService addService;
    private final CheckService checkService;
    private final DeleteService deleteService;
    private final ViewService viewService;
    private final TodayDueTaskService todayDueTaskService;
    public TaskExecutorImpl(PrintWriter out, Map<String, List<Task>> tasks) {
        this.out = out;
        this.tasks =tasks;
        addService = new AddServiceImpl(this.out, tasks);
        checkService = new CheckServiceImpl(tasks, this.out);
        deleteService = new DeleteServiceImpl(tasks, this.out);
        viewService = new ViewServiceImpl(tasks,this.out);
        todayDueTaskService = new TodayDueTaskServiceImpl(tasks, this.out);
    }

    @Override
    public void execute(String commandLine) {
        String[] commandRest = commandLine.split(" ", 2);
        String command = commandRest[0];
        switch (command) {
            case "show":
                show();
                break;
            case "add":
                addService.add(commandRest[1]);
                break;
            case "check":
                checkService.check(commandRest[1]);
                break;
            case "uncheck":
                checkService.uncheck(commandRest[1]);
                break;
            case "todaysDueTasks":
                todayDueTaskService.showDueTasksToday();
            case "delete":
                deleteService.deleteTask(commandRest[1]);
            case "view":
                String viewBy = commandRest[1];
                switch (viewBy) {
                    case "byDate":
                        viewService.viewByDate();
                        break;
                    case "byDeadline":
                        viewService.viewByDeadline();
                        break;
                    case "byProject":
                        viewService.viewByProject();
                        break;
                }
            case "help":
                help();
                break;
            default:
                error(command);
                break;
        }
    }
    private void show() {
        for (Map.Entry<String, List<Task>> project : tasks.entrySet()) {
            out.println(project.getKey());
            for (Task task : project.getValue()) {
                out.printf("    [%c] %d: %s%n", (task.isDone() ? 'x' : ' '), task.getId(), task.getDescription());
            }
            out.println();
        }
    }

    private void help() {
        out.println("Commands:");
        out.println("  show");
        out.println("  add project <project name>");
        out.println("  add task <project name> <task description>");
        out.println("  check <task ID>");
        out.println("  uncheck <task ID>");
        out.println();
    }


    private void error(String command) {
        out.printf("I don't know what the command \"%s\" is.", command);
        out.println();
    }

}

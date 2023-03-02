package com.codurance.training.tasks;

import com.codurance.training.tasks.service.*;
import com.codurance.training.tasks.service.Impl.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public final class TaskList implements Runnable {
    private static final String QUIT = "quit";

    private final Map<String, List<Task>> tasks = new LinkedHashMap<>();
    private final BufferedReader in;
    private final PrintWriter out;
    private final AddService addService;
    private final CheckService checkService;
    private final DeleteService deleteService;
    private final ViewService viewService;
    private final TodayDueTaskService todayDueTaskService;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new TaskList(in, out).run();
    }

    public TaskList(BufferedReader reader, PrintWriter writer) {
        this.in = reader;
        this.out = writer;
        addService = new AddServiceImpl(this.out, tasks);
        checkService = new CheckServiceImpl(tasks, this.out);
        deleteService = new DeleteServiceImpl(tasks, this.out);
        viewService = new ViewServiceImpl(tasks,this.out);
        todayDueTaskService = new TodayDueTaskServiceImpl(tasks, this.out);
    }

    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            execute(command);
        }
    }

    private void execute(String commandLine) {
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

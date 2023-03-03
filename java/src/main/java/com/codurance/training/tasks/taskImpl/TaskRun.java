package com.codurance.training.tasks.taskImpl;

import com.codurance.training.tasks.Task;
import com.codurance.training.tasks.TaskExecutor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class TaskRun  implements Runnable {
    private static final String QUIT = "quit";
    private final Map<String, List<Task>> tasks;
    private final PrintWriter out;
    private final BufferedReader in;
    private final TaskExecutor taskExecutor;

    public TaskRun(PrintWriter out, BufferedReader in, Map<String, List<Task>> tasks) {
        this.tasks = tasks;
        this.out = out;
        this.in = in;
        taskExecutor = new TaskExecutorImpl(this.out,this.tasks);
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
            taskExecutor.execute(command);
        }
    }
}

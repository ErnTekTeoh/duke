package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.task.Task;
import main.java.duke.entity.TaskList;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

import java.io.IOException;
import java.util.List;

public class AddCommand extends Command {

    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        List<Task> tasks = taskList.getTasks();
        tasks.add(newTask);
        try {
            storage.saveTasksToMemory(tasks);
            ui.confirmAddTask(newTask, taskList);
        } catch (IOException e) {
            tasks.remove(tasks.size() - 1);
            ui.errorSavingChanges();
            throw new IOException();
        }
    }

    public String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController) {
        try {
            this.execute(taskList, ui, storage);
            taskData.add(new TaskModel(newTask));
        } catch (IOException e) {
            return uiController.errorSavingChanges();
        }
        return uiController.confirmAddTask(newTask, taskList);
    }


    @Override
    public boolean isExit() {
        return false;
    }

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }
}

package main.java.duke.entity.command;

import javafx.collections.ObservableList;
import main.java.duke.entity.task.Task;
import main.java.duke.entity.TaskList;
import main.java.duke.gui.TaskModel;
import main.java.duke.gui.view.UiController;
import main.java.duke.handler.Storage;
import main.java.duke.handler.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckCommand extends Command {

    private Date dueDate;
    private List<Task> dueTasks;
    public CheckCommand(Date date) {
        this.dueDate = date;
    }


    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).isDue(dueDate)) {
                dueTasks.add(tasks.get(i));
            }
        }
        ui.listDueTasks(dueTasks, dueDate);
    }
    public String execute(TaskList taskList, Ui ui, Storage storage, ObservableList<TaskModel> taskData, UiController uiController) {
        this.execute(taskList, ui, storage);
        return uiController.listDueTasks(dueTasks, dueDate);
    }
    @Override
    public boolean isExit() {
        return false;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

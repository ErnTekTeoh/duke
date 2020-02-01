package entity.task;

import java.util.Date;

public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    public String printTask() {
        return "[T][" +  (super.isDone() ? "\u2713" : "\u2718" ) + "] "+ super.getTaskName();
    }


    public boolean isDue(Date date) {
        return false;
    }

    public String toStringForm() {
        return "T|" +  (super.isDone() ? "1" : "0" ) + "|"+ super.getTaskName();

    }
}

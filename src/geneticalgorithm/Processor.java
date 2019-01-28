package geneticalgorithm;

import java.util.LinkedList;

public class Processor {
    private LinkedList<Task> tasks;

    public Processor() {
        this.tasks = new LinkedList<>();
    }

    public Processor(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void setTask(Task task){
        this.tasks.add(task);   
    }

    public LinkedList<Task> getTasks() {
        return tasks;
    }
    
    public int numberOfTasks(){
        return this.tasks.size();
    }
    
    public Task getLastTask(){
        return this.tasks.getLast();
    }
    
    public Task getSpecificTask(int position_task){
        return this.tasks.get(position_task);
    }
}
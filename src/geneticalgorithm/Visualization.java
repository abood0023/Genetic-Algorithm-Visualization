package geneticalgorithm;

import javax.swing.JFrame;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import java.awt.BorderLayout;
import java.util.LinkedList;
import javax.swing.JPanel;

public class Visualization extends JFrame {

    private static MyGraph graph = new MyGraph();
    private JPanel panel;
    private static Task[] tasks;
            
    public Visualization() {
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);//disable adding edges
        getContentPane().add(graphComponent);
        graph.setDropEnabled(false);//disable drop vertex on the edge
        graph.setAllowDanglingEdges(false);
        graph.setCellsResizable(false);
        this.tasks = null;
        panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
    }
    
    public Visualization(Task[] tasks) {
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false);//disable adding edges
        getContentPane().add(graphComponent);
        graph.setDropEnabled(false);//disable drop vertex on the edge
        graph.setAllowDanglingEdges(false);
        graph.setCellsResizable(false);
        this.tasks = tasks;
        panel = new JPanel();
        add(panel, BorderLayout.SOUTH);
        mxConstants.DEFAULT_FONTSIZE = 15;

    }
    public void drawBestStringSearch(StringSearch best, int number_iteration){
        LinkedList<Task> tasks = best.getProcessors()[0].getTasks();
        Task task, temp0;
        int x = 130;
        graph.insertVertex(graph.getDefaultParent(), null, "The best schedule: ", 140, 10, 80, 40, "fontSize=40;fontColor=#04B404;strokeColor=none;fillColor=none");
        graph.insertVertex(graph.getDefaultParent(), null, "Processor 1", 20, 50, 80, 40, "fontSize=20;strokeColor=none;fillColor=none");
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if(i > 0 && task.getStart_time() != tasks.get(i-1).getFinish_time())
                graph.insertVertex(graph.getDefaultParent(), null, null, tasks.get(i-1).getFinish_time()*20+x, 50,(tasks.get(i).getStart_time()-tasks.get(i-1).getFinish_time())*20 , 40, "strokeColor=000000;fillColor=ff0000");
            graph.insertVertex(graph.getDefaultParent(), null, "T"+task.getName(), task.getStart_time()*20+x, 50, task.getExecution_time()*20, 40, "strokeColor=000000;fillColor=8A98F5");
        }
        graph.insertVertex(graph.getDefaultParent(), null, "Processor 2", 20, 100, 80, 40, "fontSize=20;strokeColor=none;fillColor=none");
        tasks = best.getProcessors()[1].getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            task = tasks.get(i);
            if(i > 0 && task.getStart_time() != tasks.get(i-1).getFinish_time())
                graph.insertVertex(graph.getDefaultParent(), null, null, tasks.get(i-1).getFinish_time()*20+x, 100, (tasks.get(i).getStart_time()-tasks.get(i-1).getFinish_time())*20, 40, "strokeColor=000000;fillColor=ff0000");
            graph.insertVertex(graph.getDefaultParent(), null, "T"+task.getName(), task.getStart_time()*20+x, 100, task.getExecution_time()*20, 40, "strokeColor=000000;fillColor=8A98F5");
        }
        graph.insertVertex(graph.getDefaultParent(), null, "The finish time is: "+best.getFinish_time(), 150, 160, 80, 40, "fontSize=30;fontColor=#FF0040;strokeColor=none;fillColor=none");
        graph.insertVertex(graph.getDefaultParent(), null, "The number of iterations is: "+number_iteration, 550, 160, 80, 40, "fontSize=30;fontColor=#6600ff;strokeColor=none;fillColor=none");
        System.out.println("\033[32mThe best string search:\033[32m");
        System.out.println("\n^^^^^^Processor1, number of tasks: " + best.getProcessors()[0].numberOfTasks() + "^^^^^^");
        for (int j = 0; j < best.getProcessors()[0].numberOfTasks(); j++) {
                temp0 = best.getProcessors()[0].getSpecificTask(j);
                System.out.println("\n#Name: T" + temp0.getName() + " ");
                System.out.println("Execution time: " + temp0.getExecution_time());
                System.out.println("Level: " + temp0.getLevel());
                System.out.println("Start time: " + temp0.getStart_time());
                System.out.println("Finish time: " + temp0.getFinish_time());
                System.out.print("Predecessors:");
                if (temp0.getPredecessors() != null) {
                    for (int k = 0; k < temp0.getPredecessors().length; k++) {
                        System.out.print(temp0.getPredecessors()[k] + " ");
                    }
                } else {
                    System.out.print(" This task haven't Predecessors");
                }
                System.out.println("\n");
        }
        System.out.println("\n^^^^^^Processor2, number of tasks: " + best.getProcessors()[1].numberOfTasks() + "^^^^^^");
        for (int j = 0; j < best.getProcessors()[1].numberOfTasks(); j++) {
                temp0 = best.getProcessors()[1].getSpecificTask(j);
                System.out.println("\n#Name: T" + temp0.getName() + " ");
                System.out.println("Execution time: " + temp0.getExecution_time());
                System.out.println("Level: " + temp0.getLevel());
                System.out.println("Start time: " + temp0.getStart_time());
                System.out.println("Finish time: " + temp0.getFinish_time());
                System.out.print("Predecessors:");
                if (temp0.getPredecessors() != null) {
                    for (int k = 0; k < temp0.getPredecessors().length; k++) {
                        System.out.print(temp0.getPredecessors()[k] + " ");
                    }
                } else {
                    System.out.print(" This task haven't Predecessors");
                }
                System.out.println("\n");
        }
        System.out.print("\033[31;1mFinish time: " + best.getFinish_time() + "\033[32m\n");
    }
    public void drawTaskGraph() {
        graph.insertVertex(graph.getDefaultParent(), null, "Task Graph: ", 90, 220, 80, 40, "fontSize=40;fontColor=#0404B4;strokeColor=none;fillColor=none");
        int y_for_prev_level = 260;
        int x_for_prev_level = 100;
        tasks[0].setPosition(graph.insertVertex(graph.getDefaultParent(), null, "T"+tasks[0].getName(),x_for_prev_level,y_for_prev_level , 30, 30, "shape=ellipse;strokeColor=8A98F5;fillColor=8A98F5"));
        x_for_prev_level += 100;
        for (int i = 1; i < tasks.length; i++) {
            if(tasks[i].getLevel() != tasks[i-1].getLevel()){
                y_for_prev_level += 60;
                x_for_prev_level = 100;
            }
            tasks[i].setPosition(graph.insertVertex(graph.getDefaultParent(), null, "T"+tasks[i].getName(),x_for_prev_level,y_for_prev_level , 30, 30, "shape=ellipse;strokeColor=8A98F5;fillColor=8A98F5"));
            x_for_prev_level += 100;
        }
    }
    
    public  Task[] getTaskSuccessors(Integer[] successors){
        if(successors != null){
            Task[] temp = new Task[successors.length];
            int successor_number = 0;
            for (int i = 0; i < tasks.length; i++) {
                for (int j = 0; j < successors.length; j++) {
                    if(successors[j] == tasks[i].getName()){
                        temp[successor_number++] = tasks[i];
                        if(successor_number == successors.length)
                            return temp;
                    }                
                }
            }            
        }
        return null;
    }

    public void drawEdges(){
        Task[] successors;
        for (int i = 0; i < tasks.length; i++) {
            successors = getTaskSuccessors(tasks[i].getSuccessors());
            if(successors != null)
                for (int j = 0; j < successors.length; j++) {
                    graph.insertEdge(graph.getDefaultParent(), null, null, tasks[i].getPosition(), successors[j].getPosition(), "LINE;strokeColor=#FF00DA");
                }
        }
    }
}

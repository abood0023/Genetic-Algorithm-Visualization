
package geneticalgorithm;

public class Task {
    private int name;
    private int level;
    private int execution_time;
    private int start_time;
    private int finish_time;
    private Integer[] predecessors;
    private Integer[] successors;
    private Object position;
    
    public Task() {
        this.name = 0;
        this.level = 0;
        this.execution_time = 0;
        this.start_time = 0;
        this.finish_time = 0;
        this.predecessors = null;
        this.successors = null;
        position = null;
    }

    public Task(int name, int level, int execution_time, Integer[] predecessors, Integer[] successors) {
        this.name = name;
        this.level = level;
        this.execution_time = execution_time;
        this.start_time = 0;
        this.finish_time = 0;
        this.predecessors = predecessors;
        this.successors = successors;
        position = null;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExecution_time(int execution_time) {
        this.execution_time = execution_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setFinish_time(int finish_time) {
        this.finish_time = finish_time;
    }

    public void setPredecessors(Integer[] predecessors) {
        this.predecessors = predecessors;
    }

    public void setSuccessors(Integer[] successors) {
        this.successors = successors;
    }

    public void setPosition(Object position) {
        this.position = position;
    }

    public int getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getExecution_time() {
        return execution_time;
    }

    public int getStart_time() {
        return start_time;
    }

    public int getFinish_time() {
        return finish_time;
    }

    public Integer[] getPredecessors() {
        return predecessors;
    }

    public Integer[] getSuccessors() {
        return successors;
    }    

    public Object getPosition() {
        return position;
    }
}

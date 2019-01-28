package geneticalgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class GeneticAlgorithm {

    private static StringSearch[] population;
    protected static StringSearch best_overall;
    private static Task[] tasks;
    private static StringSearch[] poll;
    private static int height;
    private static int stopping_counter = 0;

    public GeneticAlgorithm() {
    }

    public static void readFile(String file_path) throws IOException {
        File file = new File(file_path);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        int number_of_tasks = Integer.parseInt(line);
        height = 0;
        String[] line_parts, predecessors_s, successors_s;
        Integer[] predecessors, successors;
        tasks = new Task[number_of_tasks];
        for (int i = 0; i < number_of_tasks; i++) {
            line = br.readLine();
            tasks[i] = new Task();
            tasks[i].setName(i + 1);
            line_parts = line.split(" ");
            if (!line_parts[0].equals("-")) {
                predecessors_s = line_parts[0].split(",");
                predecessors = new Integer[predecessors_s.length];
                for (int j = 0; j < predecessors_s.length; j++) {
                    predecessors[j] = Integer.parseInt(predecessors_s[j]);
                }
                tasks[i].setPredecessors(predecessors);
            } else {
                tasks[i].setPredecessors(null);
            }
            tasks[i].setExecution_time(Integer.parseInt(line_parts[2]));
            tasks[i].setLevel(Integer.parseInt(line_parts[3]));
            if (tasks[i].getLevel() > height) {
                height = tasks[i].getLevel();
            }

            if (!line_parts[1].equals("-")) {
                successors_s = line_parts[1].split(",");
                successors = new Integer[successors_s.length];
                for (int j = 0; j < successors_s.length; j++) {
                    successors[j] = Integer.parseInt(successors_s[j]);
                }
                tasks[i].setSuccessors(successors);
            } else {
                tasks[i].setSuccessors(null);
            }
        }
    }

    protected static Task[] orderedTasksByLevel() {
        Task[] result = new Task[tasks.length];
        for (int i = 0, k = 0; i <= height; i++) {
            for (int j = 0; j < tasks.length; j++) {
                if (tasks[j].getLevel() == i) {
                    result[k] = tasks[j];
                    k++;
                }
            }
        }
        return result;
    }

    private static void randomOrderForTasks() {
        Task[] ordered_by_level = orderedTasksByLevel();
        Processor[] temp;
        Random random = new Random();
        int rand;
        Task temp_task;
        for (int i = 0; i < population.length; i++) {
            population[i] = new StringSearch();
            temp = new Processor[2];
            temp[0] = new Processor();
            temp[1] = new Processor();
            for (int j = 0; j < ordered_by_level.length; j++) {
                temp_task = new Task(ordered_by_level[j].getName(), ordered_by_level[j].getLevel(), ordered_by_level[j].getExecution_time(), ordered_by_level[j].getPredecessors(), ordered_by_level[j].getSuccessors());
                rand = random.nextInt(2);
                if (temp[rand].numberOfTasks() != 0) {
                    temp_task.setStart_time(temp[rand].getLastTask().getFinish_time());
                    temp_task.setFinish_time(temp[rand].getLastTask().getFinish_time() + temp_task.getExecution_time());
                } else {
                    temp_task.setStart_time(0);
                    temp_task.setFinish_time(temp_task.getExecution_time());
                }
                temp[rand].setTask(temp_task);
            }
            population[i].setProcessors(temp);
        }
    }

    public static int getFinishTimeForTask(Processor[] processors, int task_name) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < processors[i].numberOfTasks(); j++) {
                if (task_name == processors[i].getSpecificTask(j).getName()) {
                    return processors[i].getSpecificTask(j).getFinish_time();
                }
            }
        }
        return 0;
    }

    public static void setActualStartTime(Task task, Task prev, int number_string_search, int number_processor) {
        if (task.getPredecessors() != null) {
            Integer[] finish_times;
            finish_times = new Integer[task.getPredecessors().length + 1];
            for (int i = 0; i < task.getPredecessors().length; i++) {
                finish_times[i] = getFinishTimeForTask(population[number_string_search].getProcessors(), task.getPredecessors()[i]);
            }
            finish_times[task.getPredecessors().length] = ((prev != null) ? prev.getFinish_time() : 0);
            Arrays.sort(finish_times, Collections.reverseOrder());
            task.setStart_time(finish_times[0]);
            task.setFinish_time(finish_times[0] + task.getExecution_time());
        } else if (prev == null) {
            task.setStart_time(0);
            task.setFinish_time(task.getExecution_time());
        } else {
            task.setStart_time(prev.getFinish_time());
            task.setFinish_time(prev.getFinish_time() + task.getExecution_time());
        }
    }

    public static void calculateFinishTime() {
        Processor[] temp;
        int j, k;
        Task task0, task1, prev;
        if (best_overall == null) {
            best_overall = new StringSearch(null, Integer.MAX_VALUE);
        }
        StringSearch prev_best = best_overall;
        for (int i = 0; i < population.length; i++) {
            temp = population[i].getProcessors();
            j = 0;
            k = 0;
            while ((j < temp[0].numberOfTasks()) || (k < temp[1].numberOfTasks())) {
                if ((j < temp[0].numberOfTasks()) && (k < temp[1].numberOfTasks())) {
                    task0 = temp[0].getSpecificTask(j);
                    task1 = temp[1].getSpecificTask(k);
                    if (task0.getLevel() < task1.getLevel()) {
                        prev = ((j != 0) ? temp[0].getSpecificTask(j - 1) : null);
                        setActualStartTime(task0, prev, i, 0);
                        j++;
                    } else if (task0.getLevel() > task1.getLevel()) {
                        prev = ((k != 0) ? temp[1].getSpecificTask(k - 1) : null);
                        setActualStartTime(task1, prev, i, 1);
                        k++;
                    } else {
                        prev = ((j != 0) ? temp[0].getSpecificTask(j - 1) : null);
                        setActualStartTime(task0, prev, i, 0);
                        j++;
                        prev = ((k != 0) ? temp[1].getSpecificTask(k - 1) : null);
                        setActualStartTime(task1, prev, i, 1);
                        k++;
                    }
                } else if (j < temp[0].numberOfTasks()) {
                    task0 = temp[0].getSpecificTask(j);
                    prev = ((j != 0) ? temp[0].getSpecificTask(j - 1) : null);
                    setActualStartTime(task0, prev, i, 0);
                    j++;
                } else {
                    task1 = temp[1].getSpecificTask(k);
                    prev = ((k != 0) ? temp[1].getSpecificTask(k - 1) : null);
                    setActualStartTime(task1, prev, i, 1);
                    k++;
                }
            }
            try {
                population[i].setFinish_time(Math.max(population[i].getProcessors()[0].getLastTask().getFinish_time(), population[i].getProcessors()[1].getLastTask().getFinish_time()));
            } catch (Exception e) {
                try {
                    population[i].setFinish_time(population[i].getProcessors()[0].getLastTask().getFinish_time());
                } catch (Exception f) {
                    population[i].setFinish_time(population[i].getProcessors()[1].getLastTask().getFinish_time());
                }
            }
            //NoSuchElementException!!!!!!!!!!!!!!!!!
            if (population[i].getFinish_time() < best_overall.getFinish_time()) {
                best_overall.setStart_roulette_wheel(population[i].getStart_roulette_wheel());
                best_overall.setFinish_roulette_wheel(population[i].getFinish_roulette_wheel());
                best_overall.setFinish_time(population[i].getFinish_time());
                best_overall.setFitness(population[i].getFitness());
                best_overall.setProcessors(population[i].getProcessors());
                stopping_counter = 0;
            }
        }
        if (prev_best.getFinish_time() == best_overall.getFinish_time()) {
            stopping_counter++;
        }
    }

    public static void additionRouletteWheelBoundary() {
        double sum = 0.0;
        for (int i = 0; i < population.length; i++) {
            sum += (1.0 / population[i].getFinish_time());
        }
        population[0].setFinish_roulette_wheel(100 * (1.0 / population[0].getFinish_time()) / sum);
        population[0].setFitness((1.0 / population[0].getFinish_time()) / sum);
        for (int i = 1; i < population.length; i++) {
            population[i].setStart_roulette_wheel(population[i - 1].getFinish_roulette_wheel());
            population[i].setFinish_roulette_wheel(population[i].getStart_roulette_wheel() + (100 * (1.0 / population[i].getFinish_time()) / sum));
            population[i].setFitness((1.0 / population[i].getFinish_time()) / sum);
        }
    }

    public static void selection() {
        poll = new StringSearch[population.length / 2];
        Random random = new Random();
        double rand;
        Processor[] new_temp;
        LinkedList<Task> temp;
        Task temp0;
        for (int i = 0; i < poll.length; i++) {
            rand = random.nextDouble();
            for (int j = 0; j < population.length; j++) {
                if ((rand * 100) >= population[j].getStart_roulette_wheel() && (rand * 100) <= population[j].getFinish_roulette_wheel()) {
                    new_temp = new Processor[2];
                    new_temp[0] = new Processor();
                    new_temp[1] = new Processor();
                    temp = population[j].getProcessors()[0].getTasks();
                    for (int k = 0; k < temp.size(); k++) {
                        temp0 = temp.get(k);
                        new_temp[0].setTask(new Task(temp0.getName(), temp0.getLevel(), temp0.getExecution_time(), temp0.getPredecessors(), temp0.getSuccessors()));
                        new_temp[0].getLastTask().setStart_time(temp0.getStart_time());
                        new_temp[0].getLastTask().setFinish_time(temp0.getFinish_time());
                    }
                    temp = population[j].getProcessors()[1].getTasks();
                    for (int k = 0; k < temp.size(); k++) {
                        temp0 = temp.get(k);
                        new_temp[1].setTask(new Task(temp0.getName(), temp0.getLevel(), temp0.getExecution_time(), temp0.getPredecessors(), temp0.getSuccessors()));
                        new_temp[1].getLastTask().setStart_time(temp0.getStart_time());
                        new_temp[1].getLastTask().setFinish_time(temp0.getFinish_time());
                    }
                    poll[i] = new StringSearch(new_temp, population[j].getFinish_time());
                    poll[i].setFitness(population[j].getFitness());
                    poll[i].setStart_roulette_wheel(population[j].getStart_roulette_wheel());
                    poll[i].setFinish_roulette_wheel(population[j].getFinish_roulette_wheel());
                    break;
                }
            }
            if (poll[i] == null) {
                i--;
            }
        }
    }

    public static Task taskForCut(LinkedList<Task> all_tasks, int level) {
        Task selected_task = null;
        for (int j = 0; j < all_tasks.size(); j++) {
            if (all_tasks.get(j).getLevel() == level) {
                selected_task = all_tasks.get(j);
                if (j != (all_tasks.size() - 1) && all_tasks.get(j + 1).getLevel() > level) {
                    break;
                }
            }
        }
        if (selected_task == null) {
            for (int j = 0; j < all_tasks.size(); j++) {
                if (all_tasks.get(j).getLevel() > level) {
                    selected_task = all_tasks.get(j);
                    break;
                }
            }
        }
        return selected_task;
    }

    public static void crossover(double crossover_ratio, double mutation_ratio) {
        Random crossover = new Random();
        Random selection = new Random();
        StringSearch[] new_population = new StringSearch[population.length];
        StringSearch[] temp = new StringSearch[2];
        int selection1, selection2;
        LinkedList<Task> temp1 = null, temp2 = null, temp3 = null, temp4 = null;
        Task selected_task1 = null, selected_task2 = null, selected_task3 = null, selected_task4 = null;

        for (int i = 0; i < population.length; i += 2) {
            while (true) {
                selection1 = selection.nextInt(poll.length);
                selection2 = selection.nextInt(poll.length);
                while (selection1 == selection2) {
                    selection2 = selection.nextInt(poll.length);
                }
                while (poll[selection1].getProcessors()[0].numberOfTasks() == 0 || poll[selection1].getProcessors()[1].numberOfTasks() == 0
                        || poll[selection2].getProcessors()[0].numberOfTasks() == 0 || poll[selection2].getProcessors()[1].numberOfTasks() == 0) //null error
                {
                    selection1 = selection.nextInt(poll.length);
                    selection2 = selection.nextInt(poll.length);
                }
                temp[0] = new StringSearch(poll[selection1].getProcessors(), poll[selection1].getFinish_time());
                temp[0].setFitness(poll[selection1].getFitness());
                temp[0].setStart_roulette_wheel(poll[selection1].getStart_roulette_wheel());
                temp[0].setFinish_roulette_wheel(poll[selection1].getFinish_roulette_wheel());

                temp[1] = new StringSearch(poll[selection2].getProcessors(), poll[selection2].getFinish_time());
                temp[1].setFitness(poll[selection2].getFitness());
                temp[1].setStart_roulette_wheel(poll[selection2].getStart_roulette_wheel());
                temp[1].setFinish_roulette_wheel(poll[selection2].getFinish_roulette_wheel());

                temp1 = temp[0].getProcessors()[0].getTasks();
                temp2 = temp[0].getProcessors()[1].getTasks();
                temp3 = temp[1].getProcessors()[0].getTasks();
                temp4 = temp[1].getProcessors()[1].getTasks();
                selected_task1 = null;
                int counter = 1;
                while (counter <= tasks.length) {
                    selection1 = selection.nextInt(temp[0].getProcessors()[0].numberOfTasks());   //choose random task
                    selected_task1 = temp1.get(selection1);
                    if (selected_task1 != temp1.getLast()) {
                        if (selected_task1.getLevel() == temp1.get(selection1 + 1).getLevel()) {
                            continue;
                        }
                    }
                    selected_task2 = taskForCut(temp2, selected_task1.getLevel());
                    selected_task3 = taskForCut(temp3, selected_task1.getLevel());
                    selected_task4 = taskForCut(temp4, selected_task1.getLevel());
                    if (selected_task2 != null && selected_task3 != null && selected_task4 != null) {
                        break;
                    }
                    counter++;
                }
                if (counter <= tasks.length) {
                    break;
                }
            }
            if (crossover.nextDouble() <= crossover_ratio) {
                LinkedList<Task>[] new_processes = new LinkedList[4];
                new_processes[0] = new LinkedList<>();
                new_processes[1] = new LinkedList<>();
                new_processes[2] = new LinkedList<>();
                new_processes[3] = new LinkedList<>();
                Task old_temp;
                for (int j = 0; j < temp1.size(); j++) {
                    if (temp1.get(j).getLevel() <= selected_task1.getLevel()) {
                        old_temp = temp1.get(j);
                        new_processes[0].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[0].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[0].getLast().setFinish_time(old_temp.getFinish_time());
                    } else {
                        break;
                    }
                }
                for (int j = 0; j < temp3.size(); j++) {
                    old_temp = temp3.get(j);
                    if (selected_task3.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() <= selected_task3.getLevel()) {
                        new_processes[2].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[2].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[2].getLast().setFinish_time(old_temp.getFinish_time());
                    } else if (selected_task3.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() > selected_task3.getLevel()) {
                        new_processes[0].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[0].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[0].getLast().setFinish_time(old_temp.getFinish_time());
                    } else if (selected_task3.getLevel() > selected_task1.getLevel() && old_temp.getLevel() < selected_task3.getLevel()) {
                        new_processes[2].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[2].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[2].getLast().setFinish_time(old_temp.getFinish_time());
                    } else {
                        new_processes[0].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[0].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[0].getLast().setFinish_time(old_temp.getFinish_time());
                    }
                }
                for (int j = 0; j < temp1.size(); j++) {
                    old_temp = temp1.get(j);
                    if (old_temp.getLevel() > selected_task1.getLevel()) {
                        new_processes[2].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[2].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[2].getLast().setFinish_time(old_temp.getFinish_time());
                    }
                }

                for (int j = 0; j < temp2.size(); j++) {
                    old_temp = temp2.get(j);
                    if (selected_task2.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() <= selected_task1.getLevel()) {
                        new_processes[1].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[1].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[1].getLast().setFinish_time(old_temp.getFinish_time());//cut from after
                    } else if (selected_task2.getLevel() > selected_task1.getLevel() && old_temp.getLevel() < selected_task1.getLevel()) {
                        new_processes[1].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[1].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[1].getLast().setFinish_time(old_temp.getFinish_time());//cut from it
                    } else {
                        break;
                    }
                }
                for (int j = 0; j < temp4.size(); j++) {
                    old_temp = temp4.get(j);
                    if (selected_task4.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() <= selected_task4.getLevel()) {
                        new_processes[3].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[3].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[3].getLast().setFinish_time(old_temp.getFinish_time());
                    } else if (selected_task4.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() > selected_task4.getLevel()) {
                        new_processes[1].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[1].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[1].getLast().setFinish_time(old_temp.getFinish_time());
                    } else if (selected_task4.getLevel() > selected_task1.getLevel() && old_temp.getLevel() < selected_task4.getLevel()) {
                        new_processes[3].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[3].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[3].getLast().setFinish_time(old_temp.getFinish_time());
                    } else {
                        new_processes[1].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[1].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[1].getLast().setFinish_time(old_temp.getFinish_time());
                    }
                }
                for (int j = 0; j < temp2.size(); j++) {
                    old_temp = temp2.get(j);
                    if (selected_task2.getLevel() <= selected_task1.getLevel() && old_temp.getLevel() > selected_task1.getLevel()) {
                        new_processes[3].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[3].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[3].getLast().setFinish_time(old_temp.getFinish_time());
                    } else if (selected_task2.getLevel() > selected_task1.getLevel() && old_temp.getLevel() >= selected_task1.getLevel()) {
                        new_processes[3].add(new Task(old_temp.getName(), old_temp.getLevel(), old_temp.getExecution_time(), old_temp.getPredecessors(), old_temp.getSuccessors()));
                        new_processes[3].getLast().setStart_time(old_temp.getStart_time());
                        new_processes[3].getLast().setFinish_time(old_temp.getFinish_time());
                    }
                }
                new_population[i] = new StringSearch(new Processor[2], 0);
                Processor[] tempp = new Processor[2];
                tempp[0] = new Processor();
                tempp[1] = new Processor();
                new_population[i].setProcessors(tempp);
                new_population[i].getProcessors()[0].setTasks(new_processes[0]);
                new_population[i].getProcessors()[1].setTasks(new_processes[1]);
                new_population[i + 1] = new StringSearch(new Processor[2], 0);
                tempp[0] = new Processor();
                tempp[1] = new Processor();
                new_population[i + 1].setProcessors(tempp);
                new_population[i + 1].getProcessors()[0].setTasks(new_processes[2]);
                new_population[i + 1].getProcessors()[1].setTasks(new_processes[3]);
                mutation(new_population[i], mutation_ratio);
                mutation(new_population[i + 1], mutation_ratio);

            } else {
                mutation(temp[0], mutation_ratio);
                mutation(temp[1], mutation_ratio);
                new_population[i] = temp[0];
                new_population[i + 1] = temp[1];
            }
        }
        population = new_population;
    }

    public static void mutation(StringSearch s, double mutation_ratio) {
        Random rand = new Random();
        double ratio = rand.nextDouble();
        int select_task1 = 0, select_task2 = 0;
        Task temp1, temp2;
        if (ratio < mutation_ratio && s.getProcessors()[0].numberOfTasks() != 0 && s.getProcessors()[1].numberOfTasks() != 0) {
            int counter = 0;
            while (counter < tasks.length) {
                select_task1 = rand.nextInt(s.getProcessors()[0].numberOfTasks());
                for (int i = 0; i < s.getProcessors()[1].numberOfTasks(); i++) {
                    if (s.getProcessors()[1].getSpecificTask(i).getLevel() == s.getProcessors()[0].getSpecificTask(select_task1).getLevel()) {
                        select_task2 = i;
                        counter = tasks.length;
                        break;
                    }
                }
                counter++;
            }
            if (counter > tasks.length) {
                temp1 = s.getProcessors()[0].getTasks().remove(select_task1);
                temp2 = s.getProcessors()[1].getTasks().remove(select_task2);
                s.getProcessors()[0].getTasks().add(select_task1, temp2);
                s.getProcessors()[1].getTasks().add(select_task2, temp1);
            }
        }
    }

    public static int geneticAlgorithm(int number_of_pop, int max_iterations, double mutation_ratio, double crossover_ratio) throws IOException {
        population = new StringSearch[number_of_pop];

        for (StringSearch stringSearch : population) {
            stringSearch = new StringSearch();
        }
        
        randomOrderForTasks();
        calculateFinishTime();
        additionRouletteWheelBoundary();

        int iterations = 1;
        while (stopping_counter < 100 && iterations < max_iterations) {
            selection();
            crossover(crossover_ratio, mutation_ratio);
            calculateFinishTime();
            additionRouletteWheelBoundary();
            iterations++;
        }
        return iterations;
    }

}

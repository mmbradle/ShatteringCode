

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue2 {
    /** This queue is where we will add tasks */
    private final BlockingQueue<Runnable> queue;
    
    /**
     * Define a Runnable as a private inner class, this way there is only 1 and no one can run it except TaskQueues 
     * and it has easy access to the queue
     */
    private class TaskQueueRunner implements Runnable{
        @Override
        public void run(){
            boolean interrupted = false;
            while (!interrupted) {
                try {
                    Runnable task = queue.take(); //Waits if no task is available
                    task.run();
                } catch (InterruptedException e) {
                    interrupted = true; //Allow thread to exit
                }
            }
        }
    }
    
    /**
     * Constructor
     */
    public TaskQueue2() {
        this.queue = new LinkedBlockingQueue<Runnable>();
    }
    
    public void addConsumer(){
        new Thread(new TaskQueueRunner()).start();
    }
    
    /**
     * Add a task into the queue to be run as there is an available PDM
     * @param task The task to be run
     */
    public void addTask(Runnable task){
        try{
            this.queue.put(task);
        } catch(Exception e){
            throw new RuntimeException("Unexpected error adding a task to PdmPool", e);
        }
    }
}


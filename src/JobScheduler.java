import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;

public class JobScheduler extends PriorityQueue<Job> {
    private static final Timer timer = new Timer();
    private static long lastTimestamp = 0;
    private static BufferedReader input;

    public static void main(String[] args) throws FileNotFoundException {
        input = new BufferedReader(new FileReader("input.txt"));
        JobScheduler scheduler = new JobScheduler();
//        Job j1 = new Job(1, 3, 34, 5);
//        Job j2 = new Job(2, 5, 34, 4);
//        Job j3 = new Job(3, 2, 34, 3);
//        Job j4 = new Job(4, 2, 34, 2);
//        scheduler.push(j1);
//        scheduler.push(j2);
//        scheduler.push(j3);
//        scheduler.push(j4);
        input.lines().forEach(l -> {
            System.out.println("line");
            scheduler.push(Job.convertToJob(l));
        });

        lastTimestamp = System.currentTimeMillis();
        timer.schedule(new RunJob(scheduler), 0, 1000);
    }

    static class RunJob extends TimerTask {
        JobScheduler scheduler;

        public RunJob(JobScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void run() {
            long waitingTime, executionTime;
            if(scheduler.isEmpty()) {
                timer.cancel();
            } else {
                Job currentJob = scheduler.pop();
                System.out.println("Currently Working On: " + currentJob);
                long newTimestamp = System.nanoTime();
                boolean jobFinished = currentJob.work();
                executionTime = System.nanoTime() - newTimestamp;
                waitingTime = System.currentTimeMillis() - lastTimestamp;
                lastTimestamp = System.currentTimeMillis();
                if(jobFinished) {
                    System.out.println("Job completed and removed from queue");
                } else {
                    System.out.println("Job was not completed, placing in queue");
                    scheduler.push(currentJob);
                }
                System.out.println("The execution time(NS) of that job was: " + executionTime);
                System.out.println("The waiting time(MS) of that job was: " + waitingTime);
                System.out.println("-----------------------------------------");
            }
        }
    }
}

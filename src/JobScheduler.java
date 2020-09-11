import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class JobScheduler extends PriorityQueue<Job> {
    private static final Timer schedulerTimer = new Timer();
    private static final Timer waitListTimer = new Timer();
    private static final ArrayList<Job> waitList = new ArrayList<>();

    private static int globalTime = 0;

    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader input = new BufferedReader(new FileReader("input.txt"));
        fillJobsToWaitlist(input, waitList);

        JobScheduler scheduler = new JobScheduler();
        input.lines().forEach(l -> scheduler.push(Job.convertToJob(l)));

        waitListTimer.schedule(new AddJobsFromWaitList(scheduler), 0, 1000);
        schedulerTimer.schedule(new incrementGlobalTimer(), 1000, 1000);
        schedulerTimer.schedule(new RunJobFromScheduler(scheduler), 0, 1000);
    }

    static class incrementGlobalTimer extends TimerTask {
        @Override
        public void run() {
            globalTime++;
        }
    }

    static class RunJobFromScheduler extends TimerTask {
        JobScheduler scheduler;

        public RunJobFromScheduler(JobScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void run() {
            if(scheduler.isEmpty()) {
                if(waitList.isEmpty()) {
                    schedulerTimer.cancel();
                }
            } else {
                Job currentJob = scheduler.pop();
                if(currentJob.getTimeAtStart() == -1) {
                    currentJob.setTimeAtStart(globalTime);
                    System.out.println("Added Job Number " + currentJob.getJobNumber() + " to the queue");
                    System.out.println("The waiting time(MS) of that job was: " + (globalTime - currentJob.getArrivalTime()));
                }
                System.out.println("Currently Working On: " + currentJob);
                boolean jobFinished = currentJob.work();
                if(jobFinished) {
                    System.out.println("Job completed and removed from queue");
                    System.out.println("The execution time(NS) of that job was: " + (globalTime - currentJob.getTimeAtStart()));
                } else {
                    System.out.println("Job was not completed, placing in queue");
                    scheduler.push(currentJob);
                }
                System.out.println("-----------------------------------------");
            }
        }
    }

    static class AddJobsFromWaitList extends TimerTask {
        JobScheduler scheduler;

        public AddJobsFromWaitList(JobScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void run() {
            if(waitList.isEmpty()) {
                waitListTimer.cancel();
            } else {
                boolean jobAvailable = waitList.get(0).getArrivalTime() <= globalTime;
                while(jobAvailable) {
                    waitList.get(0).setArrivalTime(globalTime);
                    scheduler.push(waitList.remove(0));
                    jobAvailable = (!waitList.isEmpty() && waitList.get(0).getArrivalTime() <= globalTime);
                }
            }
        }
    }

    private static void quickSort(ArrayList<Job> jobs, int begin, int end) {
        if(begin < end) {
            int index = partition(jobs, begin, end);

            quickSort(jobs, begin, index-1);
            quickSort(jobs, index+1, end);
        }
    }

    private static int partition(ArrayList<Job> jobs, int begin, int end) {
        int pivot = jobs.get(end).getArrivalTime();
        int i = (begin - 1);

        for(int j = begin; j < end; j++) {
            if(jobs.get(j).getArrivalTime() <= pivot) {
                i++;

                Job swapTmp = jobs.get(i);
                jobs.set(i, jobs.get(j));
                jobs.set(j, swapTmp);
            }
        }

        Job swapTmp = jobs.get(i + 1);
        jobs.set(i + 1, jobs.get(end));
        jobs.set(end, swapTmp);

        return i + 1;
    }

    public static void fillJobsToWaitlist(BufferedReader input, ArrayList<Job> waitList) {
        input.lines().forEach(l -> waitList.add(Job.convertToJob(l)));
        quickSort(waitList, 0, waitList.size() - 1);
    }
}

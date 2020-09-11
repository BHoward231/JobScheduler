public class Job implements Comparable<Job>{
    private int jobNumber;
    private int priority;
    private int arrivalTime;
    private int duration;
    private int timeAvailable = 0;
    private int timeAtStart = -1;

    public Job(int jobNumber, int priority, int arrivalTime, int duration) {
        this.jobNumber = jobNumber;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getDuration() {
        return duration;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeAvailable() {
        return timeAvailable;
    }

    public int getTimeAtStart() {
        return timeAtStart;
    }

    public void setTimeAtStart(int timeAtStart) {
        this.timeAtStart = timeAtStart;
    }

    public void setTimeAvailable(int timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean work() {
        duration--;
        return getDuration() <= 0;
    }

    public static boolean isValid(String in) {
        if(!in.matches("\\d+\\s*")) {
            return false;
        }
        String[] split = in.split(" ");
        return split.length == 4;
    }

    public static Job convertToJob(String in) {
        String[] split = in.split(" ");
        int[] args = new int[4];
        for(int i = 0; i < args.length; i++) {
            args[i] = Integer.parseInt(split[i]);
        }

        return new Job(args[0], args[1], args[2], args[3]);
    }

    @Override
    public int compareTo(Job other) {
        return Integer.compare(getPriority(), other.getPriority());
    }

    @Override
    public String toString() {
        return "Job Number: " + this.getJobNumber() + " Priority: " + this.priority + " Duration: " + this.duration + " Arrival Time: " + this.arrivalTime;
    }
}

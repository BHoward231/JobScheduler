public class Job implements Comparable<Job>{
    private int jobNumber;
    private int priority;
    private int arrivalTime;
    private int duration;
    private int timeAvailable = 0;
    private int timeAtStart = -1;
    //creates an instance of Job
    public Job(int jobNumber, int priority, int arrivalTime, int duration) {
        this.jobNumber = jobNumber;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
    }
    //returns job number
    public int getJobNumber() {
        return jobNumber;
    }
    //returns arrival time
    public int getArrivalTime() {
        return arrivalTime;
    }
    //states duration
    public int getDuration() {
        return duration;
    }
    //returns priority
    public int getPriority() {
        return priority;
    }
    //shows available time
    public int getTimeAvailable() {
        return timeAvailable;
    }
    //returns current time at the start of the job
    public int getTimeAtStart() {
        return timeAtStart;
    }
    //sets start time
    public void setTimeAtStart(int timeAtStart) {
        this.timeAtStart = timeAtStart;
    }
    //sets available time
    public void setTimeAvailable(int timeAvailable) {
        this.timeAvailable = timeAvailable;
    }
    //sets the time of arrival
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    //sets duration
    public void setDuration(int duration) {
        this.duration = duration;
    }
    //sets job number
    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }
    //sets priority
    public void setPriority(int priority) {
        this.priority = priority;
    }
    //simulates work by deducting one from the duration and states if job is completed
    public boolean work() {
        duration--;
        return getDuration() <= 0;
    }
    //checks to see if input is in valid digit and space form
    public static boolean isValid(String in) {
        if(!in.matches("\\d+\\s*")) {
            return false;
        }
        String[] split = in.split(" ");
        return split.length == 4;
    }
    //converts input to usable data
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

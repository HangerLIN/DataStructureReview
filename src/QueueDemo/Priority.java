package QueueDemo;

public class Priority {
    private int priority;

    public Priority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static void main(String[] args) {
        Priority p1 = new Priority(1);
        Priority p2 = new Priority(2);
        System.out.println(p1.getPriority());
        System.out.println(p2.getPriority());
    }
}

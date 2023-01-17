package ua.nure.cp.enterable;

class Enterable {
    protected final int MAX_VISITORS;
    private final String type;
    protected int visitorsNumber;

    protected Enterable(int max, String type) {
        MAX_VISITORS = max;
        this.type = type;
    }

    public final void enter() {
        synchronized (this) {
            while (visitorsNumber == MAX_VISITORS) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

            visitorsNumber++;
            notifyAll();
        }
    }

    public final void leave() {
        synchronized (this) {
            int MIN_VISITORS = 0;
            while (visitorsNumber == MIN_VISITORS) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }

            visitorsNumber--;
            notifyAll();
        }
    }

    public final String getVisitorsMessage() {
        return "THERE " + (visitorsNumber <= 1 ? "IS " : "ARE ") + visitorsNumber + " READERS IN " + type.toUpperCase() + ".";
    }
}

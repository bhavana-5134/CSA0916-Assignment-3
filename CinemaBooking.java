class Cinema {
    private int seatsAvailable = 10;
    private int seatsSold = 0;

    public synchronized boolean bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
            seatsSold++;
            return true;
        }
        return false;
    }

    public int getSeatsSold() {
        return seatsSold;
    }
}

public class CinemaBooking {
    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema();
        Thread[] customers = new Thread[100];

        for (int i = 0; i < customers.length; i++) {
            customers[i] = new Thread(() -> cinema.bookSeat());
            customers[i].start();
        }
        for (Thread customer : customers) {
            customer.join();
        }
        System.out.println("Seats sold: " + cinema.getSeatsSold());
    }
}

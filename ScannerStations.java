class ScannerStation implements Runnable {
    private final int stationId;

    ScannerStation(int stationId) {
        this.stationId = stationId;
    }

    @Override
    public void run() {
        for (int scan = 1; scan <= 3; scan++) {
            System.out.println("Station " + stationId + " scanning item " + scan
                    + " | thread=" + Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                System.out.println("Station " + stationId + " was interrupted.");
                return;
            }
        }
        System.out.println("Station " + stationId + " completed.");
    }
}

public class ScannerStations {
    public static void main(String[] args) throws InterruptedException {
        Thread[] stations = new Thread[4];
        for (int i = 0; i < stations.length; i++) {
            stations[i] = new Thread(new ScannerStation(i + 1), "Scanner-" + (i + 1));
            stations[i].start();
        }

        for (Thread station : stations) {
            station.join();
        }
        System.out.println("All scanner stations have completed.");
    }
}


package week2;

import java.util.*;

class ParkingSpot {

    String licensePlate;
    long entryTime;
    boolean occupied;

    ParkingSpot() {
        occupied = false;
    }
}

class ParkingLot {

    private ParkingSpot[] table;
    private int capacity;
    private int totalProbes = 0;
    private int vehiclesParked = 0;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        table = new ParkingSpot[capacity];

        for (int i = 0; i < capacity; i++) {
            table[i] = new ParkingSpot();
        }
    }

    private int hash(String licensePlate) {
        return Math.abs(licensePlate.hashCode()) % capacity;
    }

    public void parkVehicle(String licensePlate) {

        int index = hash(licensePlate);
        int probes = 0;

        while (table[index].occupied) {
            index = (index + 1) % capacity;
            probes++;
        }

        table[index].licensePlate = licensePlate;
        table[index].entryTime = System.currentTimeMillis();
        table[index].occupied = true;

        vehiclesParked++;
        totalProbes += probes;

        System.out.println("Vehicle " + licensePlate + " parked at spot #" + index + " (" + probes + " probes)");
    }

    public void exitVehicle(String licensePlate) {

        int index = hash(licensePlate);

        while (table[index].occupied) {

            if (licensePlate.equals(table[index].licensePlate)) {

                long duration = (System.currentTimeMillis() - table[index].entryTime) / 1000;

                table[index].occupied = false;
                vehiclesParked--;

                double fee = duration * 0.01;

                System.out.println("Vehicle " + licensePlate + " exited from spot #" + index);
                System.out.println("Duration: " + duration + " seconds Fee: $" + fee);

                return;
            }

            index = (index + 1) % capacity;
        }

        System.out.println("Vehicle not found");
    }

    public void getStatistics() {

        double occupancy = (vehiclesParked * 100.0) / capacity;
        double avgProbes = vehiclesParked == 0 ? 0 : (double) totalProbes / vehiclesParked;

        System.out.println("Occupancy: " + occupancy + "%");
        System.out.println("Average Probes: " + avgProbes);
    }
}

public class problem8 {

    public static void main(String[] args) throws Exception {

        ParkingLot lot = new ParkingLot(500);

        lot.parkVehicle("ABC-1234");
        lot.parkVehicle("ABC-1235");
        lot.parkVehicle("XYZ-9999");

        Thread.sleep(2000);

        lot.exitVehicle("ABC-1234");

        lot.getStatistics();
    }
}

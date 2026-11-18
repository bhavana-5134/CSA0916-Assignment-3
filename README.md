# Q5 – Race Condition and Synchronization

Compile: `javac CinemaBooking.java`  
Run: `java CinemaBooking`

There are 10 available seats and 100 concurrent booking attempts. The synchronized method makes checking and decrementing the seat count one atomic critical section. Expected output: `Seats sold: 10`. Run it several times and record the result each time.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

// User-defined checked exception
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class ATMWithdrawal {
    private static final double BALANCE = 5000.00;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // The card-reader file represents the ATM card-reader configuration.
            File cardFile = new File("card.txt");
            if (!cardFile.exists()) {
                throw new FileNotFoundException("Card-reader file card.txt was not found.");
            }

            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine().trim();

            if (!accountNumber.matches("\\d{6}")) {
                throw new IllegalArgumentException(
                    "Account number must contain exactly 6 digits."
                );
            }

            System.out.print("Enter withdrawal amount: ");
            String amountText = scanner.nextLine().trim();

            double amount;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException e) {
                // Multi-catch is demonstrated in the outer catch block below.
                throw e;
            }

            if (amount <= 0) {
                throw new IllegalArgumentException(
                    "Withdrawal amount must be greater than zero."
                );
            }

            if (amount > BALANCE) {
                throw new InsufficientFundsException(
                    "Insufficient balance. Available balance: Rs. " + BALANCE
                );
            }

            System.out.printf("Withdrawal successful. Please collect Rs. %.2f%n", amount);
            System.out.printf("Remaining balance: Rs. %.2f%n", BALANCE - amount);

        } catch (FileNotFoundException e) {
            System.out.println("Card reader error: " + e.getMessage());

        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Input error: Please enter numeric values only.");

        } catch (InsufficientFundsException e) {
            System.out.println("Transaction declined: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } finally {
            System.out.println("ATM session ended. Thank you.");
            scanner.close();
        }
    }
}

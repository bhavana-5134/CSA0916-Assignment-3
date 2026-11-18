import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class ATMWithdrawal {
    private static final double BALANCE = 5000.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            File card = new File("card.txt");
            if (!card.exists()) {
                throw new FileNotFoundException("ATM card file was not found.");
            }

            System.out.print("Enter account number: ");
            String account = input.nextLine().trim();
            if (!account.matches("\\d{6}")) {
                throw new IllegalArgumentException("Account number must contain exactly 6 digits.");
            }

            System.out.print("Enter withdrawal amount: ");
            String amountText = input.nextLine().trim();
            double amount;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                throw new InputMismatchException("Withdrawal amount must be numeric.");
            }

            if (amount <= 0) {
                throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
            }
            if (amount > BALANCE) {
                throw new InsufficientFundsException("Insufficient funds. Available balance: Rs. " + BALANCE);
            }

            System.out.println("Withdrawal successful!");
            System.out.println("Remaining balance: Rs. " + (BALANCE - amount));
        } catch (FileNotFoundException ex) {
            System.out.println("Card error: " + ex.getMessage());
        } catch (InputMismatchException | IllegalArgumentException ex) {
            System.out.println("Input error: " + ex.getMessage());
        } catch (InsufficientFundsException ex) {
            System.out.println("Transaction error: " + ex.getMessage());
        } finally {
            System.out.println("ATM session ended.");
            input.close();
        }
    }
}

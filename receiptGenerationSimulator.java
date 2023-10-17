import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.text.DecimalFormat;

class Purchase {
    String item;
    double price;

    public Purchase(String item, double price) {
        this.item = item;
        this.price = price;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return item + " - P" + df.format(price);
    }
}

class ReceiptQueue {
    private Deque<Purchase> queue = new ArrayDeque<>();
    private double totalBill = 0.00;
    private DecimalFormat df = new DecimalFormat("0.00");

    public void addPurchase(Purchase purchase) {
        queue.add(purchase);
        totalBill += purchase.price;
    }

    public void generateReceipt() {
        if (!queue.isEmpty()) {
            Purchase nextPurchase = queue.poll();
            System.out.println("\nReceipt: " + nextPurchase);
            queue.add(nextPurchase);
            totalBill -= nextPurchase.price;
        } else {
            System.out.println("\nNo purchases in the queue.");
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public void displayReceiptQueue() {
        if (queue.isEmpty()) {
            System.out.println("\nReceipt Queue is empty.");
        } else {
            System.out.println("\nCurrent Receipt Queue:");
            for (Purchase purchase : queue) {
                System.out.println(purchase);
            }
            System.out.println("Total Bill: P" + df.format(totalBill));
        }
    }
}

public class receiptGenerationSimulator {
    public static void main(String[] args) {
        ReceiptQueue receiptQueue = new ReceiptQueue();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Receipt Generation Simulator!");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("[1] Add Purchase");
            System.out.println("[2] Generate Receipt");
            System.out.println("[3] Display Receipt Queue");
            System.out.println("[4] Exit");

            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a valid numeric choice (1-4).");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the item name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter the item price: ");
                    double itemPrice = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    Purchase purchase = new Purchase(itemName, itemPrice);
                    receiptQueue.addPurchase(purchase);
                    System.out.println("\nPurchase added: " + purchase);
                    break;
                case 2:
                    receiptQueue.generateReceipt();
                    break;
                case 3:
                    receiptQueue.displayReceiptQueue();
                    break;
                case 4:
                    System.out.println("\nThank you for using the Receipt Generation Simulator!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("\nInvalid input. Please enter a valid choice (1-4).");
            }
        }
    }
}

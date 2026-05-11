public class CoinChange {
    public static void main(String[] args) {

        int amount = 1988;
        int remaining = amount;
        int totalNotes = 0;
        int[] denominations = {5000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};

        System.out.println("\nCoin Change Problem (Pakistani Currency)");
        System.out.println("Amount: Rs. " + amount);
        System.out.print("\n");

        for (int denom : denominations) {
            int count = remaining / denom;
            if (count > 0) {
                System.out.println("Rs. " + denom + " x " + count + " = Rs. " + (count * denom));
                totalNotes += count;
            }
            remaining = remaining - (count * denom);
        }

        System.out.println("\nTotal Notes/Coins used: " + totalNotes);
        System.out.println("Remaining: Rs. " + remaining);
    }
}

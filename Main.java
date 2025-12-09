import java.util.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.HashMap;

class Term {
    int exp;
    int coeff;
}
class Entry{
    int col;
    int row;
    int data;
}
class Main{

    public static Scanner input = new Scanner(System.in);
    static MyHashMap <String, User> socialMedia = new MyHashMap<>();
    static boolean welcome = true;
    public static void main(String[] args) {
        home();


    }
    
    public static void home() {
        System.out.println("==========================================================");
        if (welcome) {
            System.out.println("welcome to our mini social media 😊");
            welcome = false;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nPlease select an option:");
            System.out.println("1. Sign in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character

                switch (choice) {
                    case 1:
                        signIn();
                        break;
                    case 2:
                        signUp();
                        break;
                }
                if (choice == 3) break;
            } else {
                System.out.println("Invalid input! Please enter a number (1 or 2).");
                scanner.nextLine(); // clear the invalid input
            }
        }
        System.out.println("==========================================================");
    }
    public static void signUp() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            // 1 & 2. Pretty title and boundary
            System.out.println("=================================");
            System.out.println("            SIGN UP              ");
            System.out.println("=================================");
            System.out.println("Enter '@' anytime to go BACK.");
            System.out.println();

            // ---------- 3. BACK option ----------
            System.out.print("Enter username: ");
            String username = sc.nextLine().trim();

            if (username.equals("@")) {
                home();        // go back
                return;        // stop signUp()
            }

            // ---------- 4. Username Validation ----------
            // i) Only digits and alphabets
            if (!username.matches("[A-Za-z0-9]+")) {
                System.out.println("❌ Username must contain only letters and digits. Try again.\n");
                continue;
            }

            // ii) Username must be unique
            if (socialMedia.get(username) != null) {
                System.out.println("❌ Username already exists. Please choose another one.\n");
                continue;
            }

            // ---------- 5. Password ----------
            System.out.print("Enter password: ");
            String password = sc.nextLine().trim();

            if (password.equals("@")) {
                home();
                return;
            }

            // i) Minimum length = 8
            if (password.length() < 8) {
                System.out.println("❌ Password must be at least 8 characters long.\n");
                continue;
            }

            // ii) Password must not be the same or 70% similar
            if (password.equals(username)) {
                System.out.println("❌ Password cannot be the same as username.\n");
                continue;
            }

            // Check similarity (Levenshtein-based)
            double similarity = similarityRatio(username, password);
            if (similarity >= 0.70) {
                System.out.println("❌ Password is too similar to username (≥ 70% similarity).\n");
                continue;
            }

            // ---------- 6. Create User and Save ----------
            User newUser = new User(username, password);
            socialMedia.put(username, newUser);

            System.out.println("\n✅ Account created successfully!");
            System.out.println("=================================\n");
            return;
        }
    }

    public static void signIn() {
        Scanner sc = new Scanner(System.in);

        while (true) {

            // 1 & 2. Pretty title + boundary
            System.out.println("=================================");
            System.out.println("             SIGN IN             ");
            System.out.println("=================================");
            System.out.println("Enter '@' anytime to go BACK.\n");

            // ---------- 3. BACK OPTION ----------
            System.out.print("Enter username: ");
            String username = sc.nextLine().trim();

            if (username.equals("@")) {
                home();   // go back
                return;
            }

            // ---------- 4. CHECK USER EXISTS ----------
            User user = socialMedia.get(username);

            if (user == null) {
                System.out.println("❌ No such user exists. Please try again.\n");
                continue;   // restart sign in
            }

            // ---------- 5. PASSWORD CHECK ----------
            System.out.print("Enter password: ");
            String password = sc.nextLine().trim();

            if (password.equals("@")) {
                home();
                return;
            }

            // i & ii. Compare password with user's actual password
            if (!password.equals(user.getPassword())) {
                System.out.println("❌ Incorrect password. Try again.\n");
                continue;
            }

            // iii. If correct → call userInterface()
            System.out.println("\n✅ Login successful!");
            System.out.println("=================================\n");

            user.userInterface();   // Enter the user's dashboard
            return;
        }
    }

    public static double similarityRatio(String a, String b) {
        int dist = levenshtein(a, b);
        int maxLen = Math.max(a.length(), b.length());
        return 1.0 - ((double) dist / maxLen);
    }

    public static int levenshtein(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= s2.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + cost
                );
            }
        }

        return dp[s1.length()][s2.length()];
    }

}
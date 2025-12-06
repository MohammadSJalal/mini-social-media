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
    MyHashMap <String, User> socialMedia = new MyHashMap<>();
    public static void main(String[] args) {

//        MyHashMap<String,User> socialMedia = new MyHashMap<>();
//        User saeed = new User();
//        socialMedia.put("mohammad", saeed);
//        User Sam = new User();
//        socialMedia.put("sam", Sam);
//        User danial = new User();
//        socialMedia.put("danial", danial);


    }
    public static void transposeSparseMatrix(Entry[] sparseMatrix , Entry[] transposedMatrix) {
        int k = 0;
        for (int i = 0; i < sparseMatrix.length; i++) { //where sparseMatrix.length is equal to number of columns
            for (int j = 0; j < sparseMatrix.length; j++) {//since number of term is equal to columns
                if (sparseMatrix[j].col == i) {
                    transposedMatrix[k].row = sparseMatrix[j].col;
                    transposedMatrix[k].col = sparseMatrix[j].row;
                    transposedMatrix[k].data = sparseMatrix[j].data;
                    k++;
                }
            }
        }
    }
    public static void TSM(Entry[] sparseMatrix , Entry[] transposedMatrix) {
        for (int i = 0; i < sparseMatrix.length; i++) {
            transposedMatrix[i].row = sparseMatrix[i].col;
            transposedMatrix[i].col = sparseMatrix[i].row;
            transposedMatrix[i].data = sparseMatrix[i].data;
        }
    }
    public static void addPoly(Term [] A , Term [] B , Term [] C){
        int i = 0, j = 0 , k = 0;
        boolean endA , endB;
        endA = endB = false;
        while(i < A.length || j < B.length){
            if (i == A.length) endA = true;
            if (j == B.length) endB = true;
            if (A[i].exp == B[j].exp && !endA && !endB){
                C[k].coeff = A[i].coeff + B[j].coeff;
                C[k++].exp = A[i++].exp + B[j++].exp;
            }
            else if (A[i].coeff < B[j].coeff && !endA){
                C[k].coeff = A[i].coeff;
                C[k++].exp = A[i++].exp;
            }
            else if (!endB) {
                C[k].coeff = B[j].coeff;
                C[k++].exp = B[j++].exp;
            }
        }
    }
    public static void home() {
        System.out.println("==========================================================");
        System.out.println("welcome to our mini social media 😊");

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
        /*
        write for me this function such:
        1.print title of section (Sign Up)
        2.make it pretty with a boundry
        3.give a special character for BACK that user can in every process of
          function turn back already function (you must call the home() function)
        4.get arbitrary username
            i.username can be consist with digits and alphabet not anything else
            ii.we have a hashmap named socialMedia this new username must not be
              exit so if it is tell user do sign up again
              also pay attention this hashmap is make by me so if you use socialMedia.get(key)
              it return value or null

        5.get password
            i.password at least length is 8
               if it is give a proper warning
            ii.password must not be same with username or has 70 percent similarity
               if it is give a proper warning
        6.make a object of User class such User(String username, String password)
          and save it inside socialMedia hashmap like this
          socialMedia.put(K key, V value)
         */
    }

    public static void signIn() {
        /*
        write for me this function such:
        1.print title of section (Sign In)
        2.make it pretty with a boundry
        3.give a special character for BACK that user can in every process of
          function turn back already function (you must call the home() function)
        4.get arbitrary username and use socialMedia for seeking we have or not
          if we haven't such user give a proper answer for they and again call sign in
          function
        5.
            i.get value of username in socialMedia hash map and save somewhere we need that
            ii.User objects have getPassword func use it for checking given pass is same with
               actual pass
            iii.give proper answer if pass aren't same and if they're same call
                userInterface func of object User that we have
         */
    }
}
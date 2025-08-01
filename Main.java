import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    static ArrayList<Document> documents = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nDOCUMENT INVENTORY SYSTEM");
            System.out.println("1. Add Document");
            System.out.println("2. View Documents");
            System.out.println("3. Save to File");
            System.out.println("4. Load from File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addDocument(); break;
                case 2: viewDocuments(); break;
                case 3: saveAll("documents.txt"); break;
                case 4: loadFromFile("documents.txt"); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void addDocument() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Kind: ");
        String kind = scanner.nextLine();
        System.out.print("Enter Date Created (YYYY-MM-DD): ");
        String dateCreated = scanner.nextLine();
        System.out.print("Enter Office: ");
        String office = scanner.nextLine();
        System.out.print("Enter Image Path: ");
        String imagePath = scanner.nextLine();

        Document doc = new Document(id, name, kind, dateCreated, office, imagePath);
        documents.add(doc);
        System.out.println("Document added successfully.");
    }

    static void viewDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to display.");
        } else {
            for (Document doc : documents) {
                doc.displayInfo();
            }
        }
    }

    static void saveAll(String filename) {
        for (Document doc : documents) {
            doc.saveToFile(filename);
        }
        System.out.println("Documents saved to file.");
    }

    static void loadFromFile(String filename) {
        try (Scanner fileScanner = new Scanner(new File(filename))) {
            documents.clear();
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                if (parts.length == 6) {
                    Document doc = new Document(
                        Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4], parts[5]);
                    documents.add(doc);
                }
            }
            System.out.println("Documents loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}


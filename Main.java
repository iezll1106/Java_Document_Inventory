import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static ArrayList<Document> documents = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static final String FILE_NAME = "documents.txt";

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nDOCUMENT INVENTORY SYSTEM");
            System.out.println("1. Add Document");
            System.out.println("2. View Documents");
            System.out.println("3. Save to File");
            System.out.println("4. Load from File");
            System.out.println("5. Edit Document");
            System.out.println("6. Delete Document");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addDocument(); break;
                case 2: loadFromFile(FILE_NAME); viewDocuments(); break;
                case 3: saveAll(FILE_NAME); break;
                case 4: loadFromFile(FILE_NAME); break;
                case 5: editDocument(); break;
                case 6: deleteDocument(); break;
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
        try (FileWriter writer = new FileWriter(filename)) {
            for (Document doc : documents) {
                writer.write(
                    doc.getId() + "," +
                    doc.getName() + "," +
                    doc.getKind() + "," +
                    doc.getDateCreated() + "," +
                    doc.getOffice() + "," +
                    doc.getImagePath() + "\n"
                );
            }
            System.out.println("Documents saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
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

    static void editDocument() {
        System.out.print("Enter ID of the document to edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Document doc : documents) {
            if (doc.getId() == id) {
                System.out.print("Enter New Name: ");
                doc.setName(scanner.nextLine());
                System.out.print("Enter New Kind: ");
                doc.setKind(scanner.nextLine());
                System.out.print("Enter New Date Created: ");
                doc.setDateCreated(scanner.nextLine());
                System.out.print("Enter New Office: ");
                doc.setOffice(scanner.nextLine());
                System.out.print("Enter New Image Path: ");
                doc.setImagePath(scanner.nextLine());
                System.out.println("Document updated successfully.");
                return;
            }
        }
        System.out.println("Document not found.");
    }

    static void deleteDocument() {
        System.out.print("Enter ID of the document to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getId() == id) {
                documents.remove(i);
                System.out.println("Document deleted successfully.");
                return;
            }
        }
        System.out.println("Document not found.");
    }
}
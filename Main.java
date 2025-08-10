import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    // Stores all document records
    static ArrayList<Document> documents = new ArrayList<>();
    // Scanner for user input
    static Scanner scanner = new Scanner(System.in);
    // Default filename for saving/loading records
    static final String FILE_NAME = "documents.txt";

    /**
     * Main entry point of the program.
     * Displays the menu and handles user choices until they choose to exit.
     */
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
            System.out.println("7. Test Image Path");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addDocument();
                case 2 -> viewDocuments();
                case 3 -> saveAll(FILE_NAME);
                case 4 -> loadFromFile(FILE_NAME);
                case 5 -> editDocument();
                case 6 -> deleteDocument();
                case 7 -> testImagePath();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    /**
     * Prompts the user for document details and adds a new Document object
     * to the documents list.
     */
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

    /**
     * Displays documents based on the user's filter choice:
     * 1. All documents
     * 2. Filter by office
     * 3. Filter by creation date
     */
    static void viewDocuments() {
        if (documents.isEmpty()) {
            System.out.println("No documents to display.");
            return;
        }

        System.out.println("\nVIEW DOCUMENTS");
        System.out.println("1. View All");
        System.out.println("2. Filter by Office");
        System.out.println("3. Filter by Date Created (YYYY-MM-DD)");
        System.out.print("Enter your choice: ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();

        switch (filterChoice) {
            case 1 -> {
                for (Document doc : documents) {
                    doc.displayInfo();
                }
            }
            case 2 -> {
                System.out.print("Enter office to filter by: ");
                String officeFilter = scanner.nextLine();
                boolean found = false;
                for (Document doc : documents) {
                    if (doc.getOffice().equalsIgnoreCase(officeFilter)) {
                        doc.displayInfo();
                        found = true;
                    }
                }
                if (!found) System.out.println("No documents found for office: " + officeFilter);
            }
            case 3 -> {
                System.out.print("Enter date (YYYY-MM-DD) to filter by: ");
                String dateFilter = scanner.nextLine();
                boolean found = false;
                for (Document doc : documents) {
                    if (doc.getDateCreated().equals(dateFilter)) {
                        doc.displayInfo();
                        found = true;
                    }
                }
                if (!found) System.out.println("No documents found for date: " + dateFilter);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    /**
     * Saves all documents from the list into a text file.
     * Each record is stored as a comma-separated line.
     * @param filename The name of the file to save data into.
     */
    static void saveAll(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Document doc : documents) {
                writer.write(doc.getId() + "," +
                             doc.getName() + "," +
                             doc.getKind() + "," +
                             doc.getDateCreated() + "," +
                             doc.getOffice() + "," +
                             doc.getImagePath() + "\n");
            }
            System.out.println("Documents saved to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Loads documents from a text file into the documents list.
     * Clears existing records before loading.
     * @param filename The name of the file to read data from.
     */
    static void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("File does not exist yet.");
            return;
        }
        try (Scanner fileScanner = new Scanner(file)) {
            documents.clear();
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",", -1);
                if (parts.length == 6) {
                    Document doc = new Document(
                            Integer.parseInt(parts[0]),
                            parts[1], parts[2], parts[3], parts[4], parts[5]);
                    documents.add(doc);
                }
            }
            System.out.println("Documents loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    /**
     * Allows the user to update the details of an existing document
     * by specifying its ID.
     */
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

    /**
     * Deletes a document from the list based on the provided ID.
     */
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

    /**
     * Tests if an image file exists at a given path and displays it in a window
     * using a simple Swing-based viewer.
     */
    static void testImagePath() {
        System.out.print("Enter image path to test: ");
        String path = scanner.nextLine();
        File imageFile = new File(path);
        if (!imageFile.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        try {
            JFrame frame = new JFrame("Image Preview");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 500);
            ImageIcon imageIcon = new ImageIcon(path);
            JLabel imageLabel = new JLabel(imageIcon);
            JScrollPane scrollPane = new JScrollPane(imageLabel);
            frame.add(scrollPane);
            frame.setVisible(true);
            System.out.println("Image loaded successfully.");
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getMessage());
        }
    }
}
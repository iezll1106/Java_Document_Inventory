// Document.java (Concrete Class)
import java.io.FileWriter;
import java.io.IOException;

public class Document extends Record implements Storable {
    private String kind;
    private String dateCreated;
    private String office;
    private String imagePath;

    public Document(int id, String name, String kind, String dateCreated, String office, String imagePath) {
        this.id = id;
        this.name = name;
        this.kind = kind;
        this.dateCreated = dateCreated;
        this.office = office;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getKind() { return kind; }
    public String getDateCreated() { return dateCreated; }
    public String getOffice() { return office; }
    public String getImagePath() { return imagePath; }

    public void setName(String name) { this.name = name; }
    public void setKind(String kind) { this.kind = kind; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }
    public void setOffice(String office) { this.office = office; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    @Override
    public void displayInfo() {
        System.out.println("ID: " + id + ", Name: " + name + ", Kind: " + kind + ", Date: " + dateCreated + ", Office: " + office + ", Image: " + imagePath);
    }

    @Override
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(id + "," + name + "," + kind + "," + dateCreated + "," + office + "," + imagePath + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
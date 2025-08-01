public class Document {
    String name;
    String type;
    String dateCreated;
    String office;

    public Document(String name, String type, String dateCreated, String office) {
        this.name = name;
        this.type = type;
        this.dateCreated = dateCreated;
        this.office = office;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Date Created: " + dateCreated);
        System.out.println("Office: " + office);
    }
}

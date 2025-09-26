import java.io.*;
import java.util.Scanner;

public class NotesApp {
    private static final String FILE_NAME = "notes.txt";
    private Scanner scanner;

    public NotesApp() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\nNotes App");
            System.out.println("1. Create Note");
            System.out.println("2. Read Notes");
            System.out.println("3. Update Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left-over

            switch (choice) {
                case 1:
                    createNote();
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    updateNote();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void createNote() {
        System.out.print("Enter note: ");
        String note = scanner.nextLine();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(note);
            writer.newLine();
            System.out.println("Note created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating note: " + e.getMessage());
        }
    }

    private void readNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
    }

    private void updateNote() {
        System.out.print("Enter note number to update: ");
        int noteNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            StringBuilder notes = new StringBuilder();
            int count = 1;
            while ((line = reader.readLine()) != null) {
                if (count == noteNumber) {
                    System.out.print("Enter new note: ");
                    String newNote = scanner.nextLine();
                    notes.append(newNote).append("\n");
                } else {
                    notes.append(line).append("\n");
                }
                count++;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(notes.toString());
                System.out.println("Note updated successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error updating note: " + e.getMessage());
        }
    }

    private void deleteNote() {
        System.out.print("Enter note number to delete: ");
        int noteNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            StringBuilder notes = new StringBuilder();
            int count = 1;
            while ((line = reader.readLine()) != null) {
                if (count != noteNumber) {
                    notes.append(line).append("\n");
                }
                count++;
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                writer.write(notes.toString());
                System.out.println("Note deleted successfully!");
            }
        } catch (IOException e) {
            System.out.println("Error deleting note: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        NotesApp app = new NotesApp();
        app.run();
    }
}
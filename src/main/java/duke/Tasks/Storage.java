package duke.Tasks;

import duke.ui.Notgpt;
import duke.additionalParsers.DataParser;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Storage {
    private ArrayList<Task> store = new ArrayList<>();
    Path filePath = Paths.get(".", "data", "data.txt");
    public Storage() {
        try {
            Files.createDirectories(filePath.getParent());

            if (Files.exists(filePath)) {
                this.store = DataParser.readTasksFromFile(filePath);
                System.out.println("Data found and loaded from: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            } else {
                Files.createFile(filePath);
                System.out.println("New Data file successfully created at: " + filePath.toAbsolutePath());
                System.out.println("*the first word will always be read as the command*");
                Notgpt.lnDiv();
            }

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void writeToFile() {
        try{
            Files.write(filePath, new byte[0]);
            Files.writeString(filePath, this.toString());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void todo(String s){
        store.add(new Todo(s));
        this.writeToFile();
    }
    public void event(String s){
        store.add(new Event(s));
        this.writeToFile();
    }
    public void deadline(String s){
        store.add(new Deadline(s));
        this.writeToFile();
    }
    public String toString() {
        String thing = "";
        int j = 1;
        for(int i = 0; i<store.size(); i++){
            thing += j + ". " + store.get(i).toString();
            if (i != store.size()-1) {
                thing += "\n";
            }
                j++;
        }
        return thing;
    }
    public void mark(int i){
        store.get(i-1).complete();
        this.writeToFile();
    }
    public void unmark(int i){
        store.get(i-1).uncomplete();
        this.writeToFile();
    }
    public void delete(int i){
        store.remove(i-1);
        this.writeToFile();
    }
    public String find(String s) {
        String thing = "";
        int j = 1;
        for(int i = 0; i<store.size(); i++){
            if (store.get(i).task.contains(s)) {
                thing += j + ". " + store.get(i).toString() + "\n";
                j++;
            }
        }
        return thing.trim();
    }
    public int size() {
        return store.size();
    }
}

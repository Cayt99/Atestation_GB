package JS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    protected String name;
    protected LocalDate birthDate;
    protected List<String> commands = new ArrayList<>();

    public Animal(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public void addCommand(String command) {
        this.commands.add(command);
    }

    public List<String> getCommands() {
        return this.commands;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.birthDate + "): Commands - " + String.join(", ", this.commands);
    }
}

package JS;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class AnimalRegistry {
    private List<Animal> animals = new ArrayList<>();
    private static int animalCount = 0;

    public void addAnimal(Animal animal) {
        animals.add(animal);
        animalCount++;
        System.out.println(animal.name + " has been added to the registry.");
    }

    public void showAnimalCommands(String name) {
        animals.stream()
               .filter(animal -> animal.name.equalsIgnoreCase(name))
               .findFirst()
               .ifPresent(animal -> System.out.println("Commands for " + animal.name + ": " + String.join(", ", animal.getCommands())));
    }

    public void teachAnimalCommand(String name, String command) {
        animals.stream()
               .filter(animal -> animal.name.equalsIgnoreCase(name))
               .findFirst()
               .ifPresent(animal -> {
                   animal.addCommand(command);
                   System.out.println(name + " has learned to " + command);
               });
    }

    public void listAnimalsByBirthDate() {
        animals.stream()
               .sorted(Comparator.comparing(animal -> animal.birthDate))
               .forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnimalRegistry registry = new AnimalRegistry();
        Scanner scanner = new Scanner(System.in);

        // Simple command line interface
        while (true) {
            System.out.println("\n1. Add Animal\n2. Show Animal Commands\n3. Teach Animal Command\n4. List Animals by Birth Date\n5. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter animal type (Dog, Cat, Hamster): ");
                    String type = scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter birth date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    switch (type.toLowerCase()) {
                        case "dog":
                            registry.addAnimal(new Dog(name, date));
                            break;
                        case "cat":
                            registry.addAnimal(new Cat(name, date));
                            break;
                        case "hamster":
                            registry.addAnimal(new Hamster(name, date));
                            break;
                        default:
                            System.out.println("Unknown animal type.");
                    }
                    break;
                case 2:
                    System.out.print("Enter animal name: ");
                    name = scanner.nextLine();
                    registry.showAnimalCommands(name);
                    break;
                case 3:
                    System.out.print("Enter animal name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter command: ");
                    String command = scanner.nextLine();
                    registry.teachAnimalCommand(name, command);
                    break;
                case 4:
                    registry.listAnimalsByBirthDate();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
    }
}

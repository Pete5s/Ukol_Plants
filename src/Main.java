import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        try {
            PlantList plantList = new PlantList();
            plantList.loadFromFile("kvetiny.txt");

            plantList.printWateringInfo();


            plantList.addPlant(new Plant("Fialka", "Popis fialky - je fialová a hezká", LocalDate.of(2021, 5, 12), LocalDate.of(2021, 1, 1), 3));
            plantList.addPlant(new Plant("Vánoční hvězda", LocalDate.of(2021, 5, 10), 4));
            plantList.addPlant(new Plant("Bazalka v kuchyni"));


            plantList.removePlant(0);


            plantList.saveToFile("novy_seznam.txt");


            PlantList newPlantList = new PlantList();
            newPlantList.loadFromFile("novy_seznam.txt");
            newPlantList.printWateringInfo();


            newPlantList.sortByName();
            newPlantList.printWateringInfo();


            newPlantList.sortByWateringDate();
            newPlantList.printWateringInfo();

        } catch (PlantException e) {
            System.err.println("Chyba: " + e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlantList {
    private List<Plant> plants;

    public PlantList() {
        plants = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public Plant getPlant(int index) {
        return plants.get(index);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public void loadFromFile(String filename) throws PlantException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length != 5) {
                    throw new PlantException("Špatný formát souboru.");
                }
                String name = parts[0];
                String notes = parts[1];
                LocalDate planted = LocalDate.parse(parts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate watering = LocalDate.parse(parts[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                int frequencyOfWatering = Integer.parseInt(parts[4]);

                Plant plant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                plants.add(plant);
            }
        } catch (IOException | NumberFormatException e) {
            throw new PlantException("Chyba při načítání souboru: " + e.getMessage());
        }
    }

    public void saveToFile(String filename) throws PlantException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Plant plant : plants) {
                writer.write(String.format("%s\t%s\t%s\t%s\t%d\n",
                        plant.getName(),
                        plant.getNotes(),
                        plant.getPlanted().toString(),
                        plant.getWatering().toString(),
                        plant.getFrequencyOfWatering()));
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při ukládání do souboru: " + e.getMessage());
        }
    }

    public void sortByName() {
        Collections.sort(plants);
    }

    public void sortByWateringDate() {
        plants.sort((p1, p2) -> p1.getWatering().compareTo(p2.getWatering()));
    }

    public void printWateringInfo() {
        for (Plant plant : plants) {
            System.out.println(plant.getWateringInfo());
        }
    }
}

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PlantList {
    private final List<Plant> plants;

    public PlantList() {
        this.plants = new ArrayList<>();
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(int index) {
        plants.remove(index);
    }

    public void loadFromFile(String filePath) throws IOException, PlantException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        plants.clear();
        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length != 5) throw new IOException("Invalid file format.");
            String name = parts[0];
            String notes = parts[1];
            LocalDate planted = LocalDate.parse(parts[2]);
            LocalDate watering = LocalDate.parse(parts[3]);
            int frequencyOfWatering = Integer.parseInt(parts[4]);
            plants.add(new Plant(name, notes, planted, watering, frequencyOfWatering));
        }
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            for (Plant plant : plants) {
                writer.write(String.format("%s\t%s\t%s\t%s\t%d\n",
                        plant.getName(), plant.getNotes(), plant.getPlanted(), plant.getWatering(), plant.getFrequencyOfWatering()));
            }
        }
    }

    public void sortByName() {
        Collections.sort(plants);
    }

    public void sortByWateringDate() {
        plants.sort(Plant.byWateringDate());
    }

    public void printWateringInfo() {
    }
}
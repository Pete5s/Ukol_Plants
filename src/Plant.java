import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;


    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo.");
        }
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nesmí být starší než datum zasazení.");
        }
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }


    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this(name, "", planted, LocalDate.now(), frequencyOfWatering);
    }


    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public static Comparator<? super Plant> byWateringDate() {
        return null;
    }


    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void getName(String name) {
        this.name = name;
    }

    public void getNotes(String notes) {
        this.notes = notes;
    }

    public void getPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public void getWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nesmí být starší než datum zasazení.");
        }
        this.watering = watering;
    }

    public void getFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Frekvence zálivky musí být kladné číslo.");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo() {
        LocalDate nextWateringDate = watering.plusDays(frequencyOfWatering);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return String.format("Rostlina: %s, Poslední zálivka: %s, Další zálivka: %s",
                name, watering.format(formatter), nextWateringDate.format(formatter));
    }

    @Override
    public int compareTo(Plant other) {
        return this.name.compareTo(other.name);
    }
}

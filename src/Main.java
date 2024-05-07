import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Main {
    public static void main(String[] args) {
        List<City> cities = readCitiesFromFile("cities.txt");
        Map<String, Integer> regionCountMap = countCitiesByRegion(cities);

        for (Map.Entry<String, Integer> entry : regionCountMap.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    private static Map<String, Integer> countCitiesByRegion(List<City> cities) {
        Map<String, Integer> regionCountMap = new HashMap<>();

        for (City city : cities) {
            String region = city.getRegion();
            regionCountMap.put(region, regionCountMap.getOrDefault(region, 0) + 1);
        }

        return regionCountMap;
    }

    private static List<City> readCitiesFromFile(String filename) {
        List<City> cities = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length == 5) {
                    String name = parts[0].trim();
                    String region = parts[1].trim();
                    String district = parts[2].trim();
                    long population = Long.parseLong(parts[3].trim());
                    String foundation = parts[4].trim();

                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Некорректный формат строки: " + line);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
            e.printStackTrace();
        }
        return cities;
    }
}

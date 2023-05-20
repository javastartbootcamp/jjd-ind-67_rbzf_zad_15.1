package pl.javastart.task;

import pl.javastart.task.comparators.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {

    private static final int FIRST_NAME_SORTING = 1;
    private static final int LAST_NAME_SORTING = 2;
    private static final int SCORE_SORTING = 3;
    private static final int ASCENDING_ORDER = 1;
    private static final int DESCENDING_ORDER = 2;

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        List<Competitor> competitors = readCompetitors(scanner);

        if (competitors.isEmpty()) {
            System.out.println("Brak danych do sortowania.");
            return;
        }
        sortCompetitors(scanner, competitors);
        try {
            writeToFile(competitors);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
    }

    private static List<Competitor> readCompetitors(Scanner scanner) {
        List<Competitor> competitors = new ArrayList<>();
        String[] competitorData;
        String input;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            input = scanner.nextLine();
            if (input != null && !input.equals("")) {
                if (input.equalsIgnoreCase("STOP")) {
                    break;
                }
                competitorData = input.split(" ");
                if (competitorData.length == 3) {
                    competitors.add(new Competitor(competitorData[0], competitorData[1], Integer.parseInt(competitorData[2])));
                } else {
                    System.out.println("Nieprawidlowe dane zawodnika.");
                }
            } else {
                System.out.println("Nieprawidlowe dane zawodnika.");
            }
        } while (!input.equalsIgnoreCase("STOP"));
        return competitors;
    }

    private void sortCompetitors(Scanner scanner, List<Competitor> competitors) {
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n",
                FIRST_NAME_SORTING, LAST_NAME_SORTING, SCORE_SORTING);
        int sortingParameter = scanner.nextInt();
        scanner.nextLine();
        System.out.printf("Sortować rosnąco czy malejąco? (%d - rosnąco, %d - malejąco)\n",
                ASCENDING_ORDER, DESCENDING_ORDER);
        int sortingOrder = scanner.nextInt();
        scanner.nextLine();
        Comparator<Competitor> comparator = switch (sortingParameter) {
            case FIRST_NAME_SORTING -> new FirstNameComparator();
            case LAST_NAME_SORTING -> new LastNameComparator();
            case SCORE_SORTING -> new ScoreComparator();
            default -> null;
        };
        if (comparator == null) {
            System.out.println("opcja nieprawidlowa");
            return;
        }
        if (sortingOrder != ASCENDING_ORDER) {
            comparator = comparator.reversed();
        }
        competitors.sort(comparator);
    }

    private void writeToFile(List<Competitor> competitors) throws IOException {
        File file = new File("stats.csv");
        try (FileWriter fileWriter = new FileWriter("stats.csv")) {
            for (Competitor competitor : competitors) {
                fileWriter.write(competitor.getFirstName() + " " + competitor.getLastName() + ";" + competitor.getScore() + "\n");
            }
        }
    }
}


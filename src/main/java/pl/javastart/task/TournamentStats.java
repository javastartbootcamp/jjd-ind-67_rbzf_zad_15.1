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

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        List<Competitor> competitors = new ArrayList<>();
        readCompetitors(scanner, competitors);

        if (competitors.size() > 0) {
            sortCompetitors(scanner, competitors);

            try {
                writeToFile(competitors);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        }
    }

    private static void readCompetitors(Scanner scanner, List<Competitor> competitors) {
        String[] competitorData;
        String input;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            input = scanner.nextLine();
            if (input != null && !input.equals("")) {
                if (input.equals("stop")) {
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
        } while (!input.equals("stop"));
    }

    private static void sortCompetitors(Scanner scanner, List<Competitor> competitors) {
        System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
        int sortingParameter = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
        int sortingOrder = scanner.nextInt();
        scanner.nextLine();
        switch (sortingParameter) {
            case FIRST_NAME_SORTING -> {
                if (sortingOrder == 1) {
                    competitors.sort(new FirstNameComparatorAscendingOrder());
                } else {
                    competitors.sort(new FirstNameComparatorAscendingOrder().reversed());
                }
            }
            case LAST_NAME_SORTING -> {
                if (sortingOrder == 1) {
                    competitors.sort(new LastNameComparatorAscendingOrder());
                } else {
                    competitors.sort(new LastNameComparatorAscendingOrder().reversed());
                }
            }
            case SCORE_SORTING -> {
                if (sortingOrder == 1) {
                    competitors.sort(new ScoreComparatorAscendingOrder());
                } else {
                    competitors.sort(new ScoreComparatorAscendingOrder().reversed());
                }
            }
            default -> System.out.println("opcja nieprawidlowa");
        }
    }

    private void writeToFile(List<Competitor> competitors) throws IOException {
        File file = new File("stats.csv");
        try (FileWriter fileWriter = new FileWriter("stats.csv")) {
            for (Competitor competitor : competitors) {
                fileWriter.write(competitor.getFirstName() + " " + competitor.getLastName() + "; " + competitor.getScore() + "\n");
            }
        }
    }
}


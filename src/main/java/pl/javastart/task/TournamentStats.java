package pl.javastart.task;

import pl.javastart.task.comparators.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class TournamentStats {

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        List<Competitor> competitors = new ArrayList<>();
        String firstName;
        String lastName;
        int score;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop):");
            firstName = scanner.nextLine();
            if (firstName.equals("stop")) {
                break;
            }
            lastName = scanner.nextLine();
            score = scanner.nextInt();
            competitors.add(new Competitor(firstName, lastName, score));
        } while (!firstName.equals("stop"));

        if (competitors.size() != 0) {
            System.out.println("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");
            int sortingParameter = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");
            int sortingOrder = scanner.nextInt();
            scanner.nextLine();
            switch (sortingParameter) {
                case 1 -> {
                    if (sortingOrder == 1) {
                        competitors.sort(new FirstNameComparatorAscendingOrder());
                    } else {
                        competitors.sort(new FirstNameComparatorDescendingOrder());
                    }
                }
                case 2 -> {
                    if (sortingOrder == 1) {
                        competitors.sort(new LastNameComparatorAscendingOrder());
                    } else {
                        competitors.sort(new LastNameComparatorDescendingOrder());
                    }
                }
                case 3 -> {
                    if (sortingOrder == 1) {
                        competitors.sort(new ScoreComparatorAscendingOrder());
                    } else {
                        competitors.sort(new ScoreComparatorDescendingOrder());
                    }
                }
                default -> System.out.println("opcja nieprawidlowa");
            }

            try {
                writeToFile(competitors);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Dane posortowano i zapisano do pliku stats.csv.");
        }
    }

    private void writeToFile(List<Competitor> competitors) throws IOException {
        File file = new File("stats.csv");
        FileWriter fileWriter = new FileWriter("stats.csv");
        for (Competitor competitor : competitors) {
            fileWriter.write(competitor.getFirstName() + " " + competitor.getLastName() + "; " + competitor.getScore() + "\n");
        }
        fileWriter.close();
    }
}


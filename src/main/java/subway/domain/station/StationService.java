package subway.domain.station;

import java.util.Scanner;
import subway.domain.menu.MenuInputManager;
import subway.domain.menu.MenuItemsRepository;
import subway.common.ErrorMessage;
import subway.domain.menu.MenuOutputManager;

public class StationService {
    private final MenuInputManager menuInputManager;
    private final StationInputManager stationInputManager;

    public StationService(Scanner scanner, MenuInputManager menuInputManager) {
        this.menuInputManager = menuInputManager;
        stationInputManager = new StationInputManager(scanner);

    }

    public void run() {
        while (true) {
            MenuOutputManager.printMenu(MenuItemsRepository.getStationItems());
            String input = menuInputManager.getStationInput();
            if (input.equals("B")) {
                return;
            }
            runSystemByInput(input);
        }
    }

    private void runSystemByInput(String input) {
        if (input.equals("1")) {
            addStation();
        }
        if (input.equals("2")) {
            deleteStation();
        }
        if (input.equals("3")) {
            lookupStations();
        }
    }

    private void addStation() {
        String name = stationInputManager.getStationNameToAdd();
        if (name.contains(ErrorMessage.OUT)) {
            return;
        }
        Station station = new Station(name);
        StationRepository.addStation(station);
        StationOutputManager.printAddedInfo();

    }

    private void deleteStation() {
        String name = stationInputManager.getStationNameToDelete();
        if (name.contains(ErrorMessage.OUT)) {
            return;
        }
        StationRepository.deleteStation(name);
        StationOutputManager.printDeletedInfo();
    }

    private void lookupStations() {
        StationOutputManager.printStations();
    }

}

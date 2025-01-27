package w3_11_pokemongame_junho_0120;

import java.util.HashMap;
import java.util.Map;

public class PokeMap {
    private final Map<String, MapLocation> locations = new HashMap<>();

    public PokeMap() {
        // 풀숲과 도시
        locations.put("GRASSLAND", new Grassland());
        locations.put("CITY", new City());
    }

    public void enterLocation(String locationType, Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        MapLocation location = locations.get(locationType.toUpperCase());
        if (location != null) {
            location.enter(trainer, enemyTrainer, wildPokemon);
        } else {
            System.out.println("알 수 없는 지역입니다.");
        }
    }
}

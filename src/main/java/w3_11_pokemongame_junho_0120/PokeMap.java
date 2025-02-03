package w3_11_pokemongame_junho_0120;

import java.util.HashMap;
import java.util.Map;

public abstract class PokeMap implements MapLocation {
    private static final Map<String, PokeMap> locations = new HashMap<>();

    static {
        locations.put("GRASSLAND", new Grassland());
        locations.put("CITY", new City());
        locations.put("MOONHILL", MoonHill.getInstance());
    }

    public static void enterLocation(String locationType, Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        PokeMap location = locations.get(locationType.toUpperCase());
        if (location != null) {
            location.enter(trainer, enemyTrainer, wildPokemon);
        } else {
            System.out.println("알 수 없는 지역입니다.");
        }
    }

    @Override
    public abstract void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon);
}

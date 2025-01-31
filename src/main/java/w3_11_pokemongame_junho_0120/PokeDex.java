package w3_11_pokemongame_junho_0120;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PokeDex {
    static Map<String, Pokemon> pokemonByName = new HashMap<>();
    static Map<PokeCategory, Map<String, Pokemon>> pokemonByCategory = new HashMap<>();

    // Mystic 포켓몬이 교환될 때 수행할 특별한 액션
    private static final Map<String, Supplier<Pokemon>> mysticActionDex = new HashMap<>();

    static {
        // PokeCategory 초기화
        for (PokeCategory category : PokeCategory.values()) {
            pokemonByCategory.put(category, new HashMap<>());
        }

        // 포켓몬 더미 데이터
        Pokemon p1 = new Pokemon("butterfly", 60, 10, "sky");
        Pokemon p2 = new Pokemon("mu", 100, 20, "legendary");
        Pokemon p3 = new Pokemon("pikachu", 70, 12, "electric");
        Pokemon p4 = new Pokemon("eevee", 80, 12, "normal");
        Pokemon p5 = new Pokemon("machoke", 90, 12, "fighting");
        Pokemon p6 = new Pokemon("근육몬", 95, 15, "mystic");

        // 포켓몬 등록
        addPokemonToDex(p1, PokeCategory.SKY);
        addPokemonToDex(p2, PokeCategory.LEGENDARY);
        addPokemonToDex(p3, PokeCategory.ELECTRIC);
        addPokemonToDex(p4, PokeCategory.NORMAL);
        addPokemonToDex(p5, PokeCategory.FIGHTING);
        addPokemonToDex(p6, PokeCategory.MYSTIC);

        // Mystic 포켓몬 교환 시 발생할 액션 정의
        mysticActionDex.put("근육몬", () -> {
            System.out.println("근육몬의 Mystic 액션으로 진화가 일어납니다!");
            return new Pokemon("괴력몬", 110, 20, "mystic");
        });

        mysticActionDex.put("라프라스", () -> {
            System.out.println("라프라스의 Mystic 액션으로 특별한 변형이 발생합니다!");
            return new Pokemon("슈퍼 라프라스", 150, 25, "mystic");
        });
    }

    // 포켓몬 등록을 위한 헬퍼 메서드
    private static void addPokemonToDex(Pokemon pokemon, PokeCategory category) {
        pokemonByName.put(pokemon.getPokemonName().toLowerCase(), pokemon);
        pokemonByCategory.get(category).put(pokemon.getPokemonName().toLowerCase(), pokemon);
    }

    public static Pokemon searchPokemon(String name) {
        return pokemonByName.get(name.toLowerCase());
    }

    public static Map<String, Pokemon> searchPokemon(PokeCategory category) {
        return pokemonByCategory.get(category);
    }

    public static Pokemon applyMysticTradeEffect(Pokemon pokemon) {
        Supplier<Pokemon> action = mysticActionDex.get(pokemon.getPokemonName().toLowerCase());
        if (action != null) {
            return action.get(); // Mystic 액션 실행 후 새로운 포켓몬 반환
        }
        return null; // 특별한 변화 없음
    }

    public static void showTradeablePokemons() {
        System.out.println("\n📜 교환 가능한 포켓몬 목록:");
        for (String name : pokemonByName.keySet()) {
            System.out.println("- " + name);
        }
    }

    public enum PokeCategory {
        WATER, FIRE, EARTH, SKY, LEGENDARY, MYSTIC, NORMAL, ELECTRIC, FIGHTING
    }
}

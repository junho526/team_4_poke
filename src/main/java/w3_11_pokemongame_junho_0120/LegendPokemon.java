package w3_11_pokemongame_junho_0120;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class LegendPokemon extends Pokemon {
    //전설의 포켓몬 숨김
    private static final Map<PokeDex.PokeCategory, LegendPokemon> legendPokemonByCategory = new HashMap<>();


    private LegendPokemon(String pokemonName, int HP, int level, String type) {
        super(pokemonName, HP, level, type);
    }

    public static LegendPokemon createLegendPokemon(String pokemonName, int HP, int level, PokeDex.PokeCategory category) {
        // LEGENDARY 타입 검사
        if (category != PokeDex.PokeCategory.LEGENDARY) {
            throw new IllegalArgumentException("전설의 포켓몬은 반드시 LEGENDARY 타입이어야 합니다.");
        }

        if (legendPokemonByCategory.containsKey(category)) {
            System.out.println("이미 존재하는 전설의 포켓몬: " + legendPokemonByCategory.get(category).getPokemonName());
            return legendPokemonByCategory.get(category);
        }

        LegendPokemon newLegendPokemon = new LegendPokemon(pokemonName, HP, level, category.name());
        legendPokemonByCategory.put(category, newLegendPokemon);
        System.out.println(category + " 타입 전설의 포켓몬 " + pokemonName + "이(가) 생성되었습니다.");
        return newLegendPokemon;
    }

    public static LegendPokemon getLegendPokemonByCategory(PokeDex.PokeCategory category) {
        if (category != PokeDex.PokeCategory.LEGENDARY) {
            throw new IllegalArgumentException("전설의 포켓몬은 LEGENDARY 타입만 조회 가능합니다.");
        }
        return legendPokemonByCategory.get(category);
    }

    public static Map<PokeDex.PokeCategory, LegendPokemon> getAllLegendPokemons() {
        return legendPokemonByCategory;
    }
}

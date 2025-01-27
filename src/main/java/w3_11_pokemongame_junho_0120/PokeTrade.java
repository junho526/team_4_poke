package w3_11_pokemongame_junho_0120;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PokeTrade {
    @Getter
    private final String name;
    private final Map<String, Pokemon> pokemonCollection;

    public PokeTrade(String name) {
        this.name = name;
        this.pokemonCollection = new HashMap<>();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemonCollection.put(pokemon.getPokemonName(), pokemon);
    }

    public Pokemon removePokemon(String pokemonName) {
        return pokemonCollection.remove(pokemonName);
    }

    public static void tradePokemon(PokeTrade trainer1, String chosenPokemon1, PokeTrade trainer2, String chosenPokemon2) {
        System.out.println("포켓몬 교환 시작");

        Pokemon pokemon1 = trainer1.removePokemon(chosenPokemon1);
        Pokemon pokemon2 = trainer2.removePokemon(chosenPokemon2);

        if (pokemon1 == null || pokemon2 == null) {
            System.out.println("교환 실패! 선택한 포켓몬이 존재하지 않습니다.");
            if (pokemon1 != null) trainer1.addPokemon(pokemon1);
            if (pokemon2 != null) trainer2.addPokemon(pokemon2);
            return;
        }

        trainer1.addPokemon(pokemon2);
        trainer2.addPokemon(pokemon1);

        System.out.println(trainer1.name + "와 " + trainer2.name + "가 포켓몬을 교환했습니다!");
        System.out.println(trainer1.name + "는 " + pokemon2.getPokemonName() + "을 받았습니다.");
        System.out.println(trainer2.name + "는 " + pokemon1.getPokemonName() + "을 받았습니다.");

        // 특수 동작
        SpecialTradePokemon(pokemon1);
        SpecialTradePokemon(pokemon2);

        System.out.println("포켓몬 교환 종료");
    }

    // 트레이드 시 득별 동작 발생 포켓몬
    private static void SpecialTradePokemon(Pokemon pokemon) {
        switch (pokemon.getPokemonName().toLowerCase()) {
            case "machoke":
                // 근육몬은 교환 시 마챔프로 변경
                System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 괴력몬으로 변했습니다!");
                pokemon.evolve();
                break;
            case "eevee":
                // 이브이는 교환 시 속성 변경
                String[] attributes = {"Fire", "Water", "Electric", "Psychic"};
                String newAttribute = attributes[new Random().nextInt(attributes.length)];
                System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 새로운 속성 " + newAttribute + "을(를) 얻었습니다!");
                break;
            default:
                System.out.println(pokemon.getPokemonName() + "은(는) 특별한 변화를 겪지 않았습니다.");
                break;
        }
    }
}

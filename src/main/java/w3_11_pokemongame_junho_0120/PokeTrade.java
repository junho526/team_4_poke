package w3_11_pokemongame_junho_0120;

import java.util.*;

public class PokeTrade {

    private static final Map<String, TradeEffect> tradeEffects = new HashMap<>();

    static {
        tradeEffects.put("machoke", pokemon -> {
            System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 괴력몬으로 진화했습니다.");
            pokemon.evolve();
        });

        tradeEffects.put("eevee", pokemon -> {
            String[] attributes = {"Fire", "Water", "Electric"};
            String newAttribute = attributes[new Random().nextInt(attributes.length)];
            pokemon.setType(newAttribute);
            System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 " + newAttribute + " 속성으로 변경되었습니다.");
        });

        tradeEffects.put("kadabra", pokemon -> {
            System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 후딘으로 진화했습니다.");
            pokemon.evolve();
        });

        tradeEffects.put("graveler", pokemon -> {
            System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 딱딱구리로 진화했습니다.");
            pokemon.evolve();
        });
    }

    public static void trade(Trainer trainer1, Trainer trainer2, Scanner scanner) {
        PokeDex.showTradeablePokemons();

        int choice1 = selectPokemon(trainer1, scanner, trainer1.getTrainerName() + "의 포켓몬 선택:");
        int choice2 = selectPokemon(trainer2, scanner, trainer2.getTrainerName() + "의 포켓몬 선택:");

        if (choice1 == -1 || choice2 == -1) {
            System.out.println("교환이 취소되었습니다.");
            return;
        }

        List<Pokemon> list1 = trainer1.getCapturedPokemons();
        List<Pokemon> list2 = trainer2.getCapturedPokemons();

        Pokemon temp = list1.get(choice1);
        list1.set(choice1, list2.get(choice2));
        list2.set(choice2, temp);

        System.out.println(trainer1.getTrainerName() + "와 " + trainer2.getTrainerName() + "가 포켓몬을 교환했습니다.");
        System.out.println(trainer1.getTrainerName() + "는 " + list1.get(choice1).getPokemonName() + "을 받았습니다.");
        System.out.println(trainer2.getTrainerName() + "는 " + list2.get(choice2).getPokemonName() + "을 받았습니다.");

        applyTradeEffect(list1.get(choice1));
        applyTradeEffect(list2.get(choice2));

        applyMysticTradeEffect(list1, choice1);
        applyMysticTradeEffect(list2, choice2);
    }

    private static int selectPokemon(Trainer trainer, Scanner scanner, String prompt) {
        List<Pokemon> pokemons = trainer.getCapturedPokemons();
        if (pokemons.isEmpty()) {
            System.out.println(trainer.getTrainerName() + "의 포켓몬이 없습니다.");
            return -1;
        }

        System.out.println("\n" + prompt);
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println((i + 1) + ". " + pokemons.get(i).getPokemonName());
        }
        System.out.print("선택 (취소: 0): ");

        int choice = scanner.nextInt() - 1;
        if (choice < -1 || choice >= pokemons.size()) {
            System.out.println("잘못된 선택입니다. 다시 선택하세요.");
            return selectPokemon(trainer, scanner, prompt);
        }
        return choice;
    }

    private static void applyTradeEffect(Pokemon pokemon) {
        tradeEffects.getOrDefault(pokemon.getPokemonName().toLowerCase(), p -> {
            System.out.println(p.getPokemonName() + "은(는) 특별한 변화를 겪지 않았습니다.");
        }).applyEffect(pokemon);
    }

    private static void applyMysticTradeEffect(List<Pokemon> pokemons, int index) {
        Pokemon pokemon = pokemons.get(index);
        Pokemon evolvedPokemon = PokeDex.applyMysticTradeEffect(pokemon);

        if (evolvedPokemon != null) {
            System.out.println(pokemon.getPokemonName() + "이(가) Mystic 교환 효과로 인해 " + evolvedPokemon.getPokemonName() + "으로 변했습니다!");
            pokemons.set(index, evolvedPokemon);
        }
    }

    @FunctionalInterface
    interface TradeEffect {
        void applyEffect(Pokemon pokemon);
    }
}

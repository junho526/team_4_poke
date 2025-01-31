package w3_11_pokemongame_junho_0120;

import java.util.List;

public class MoonHill extends PokeMap {

    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        System.out.println("달맞이 동산으로 이동을 시도합니다.");

        // Fly 또는 Surf 없으면 이동 불가
        if (!trainer.canFly() && !trainer.canSurf()) {
            System.out.println("달맞이 동산에는 Fly 또는 Surf로만 이동 가능합니다!");
            return;
        }

        System.out.println("이동 가능한 포켓몬을 소유하고 있습니다. 달맞이 동산으로 이동합니다.");
        System.out.println("달맞이 동산에 도착했습니다.");

        // 포켓몬 진화 로직
        boolean evolved = false;
        List<Pokemon> capturedPokemons = trainer.getCapturedPokemons();
        for (int i = 0; i < capturedPokemons.size(); i++) {
            Pokemon pokemon = capturedPokemons.get(i);
            if (isMoonPokemon(pokemon)) {
                Pokemon evolvedPokemon = pokemon.evolve();
                System.out.println(pokemon.getPokemonName() + "이(가) 진화하여 " + evolvedPokemon.getPokemonName() + "이(가) 되었습니다!");
                capturedPokemons.set(i, evolvedPokemon);
                evolved = true;
            }
        }

        // 진화한 포켓몬이 없을 경우 메시지 출력
        if (!evolved) {
            System.out.println("진화할 포켓몬이 없습니다.");
        }
    }

    private boolean isMoonPokemon(Pokemon pokemon) {
        return pokemon.getPokemonName().equalsIgnoreCase("purin") ||
                pokemon.getPokemonName().equalsIgnoreCase("bbibbi");
    }
}

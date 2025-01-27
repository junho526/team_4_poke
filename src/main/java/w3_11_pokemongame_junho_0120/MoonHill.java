package w3_11_pokemongame_junho_0120;

import java.util.List;

public class MoonHill implements MapLocation {

    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        //매개변수 중 trainer만 사용
        System.out.println("달맞이 동산으로 이동합니다");

        // Fly와 Surf 조건 확인 필요
        if (trainer.canFly() || trainer.canSurf()) {
            System.out.println("달맞이 동산에 도착했습니다! 포켓몬이 진화합니다.");
            return;
        }
        System.out.println("달맞이 동산에는 Fly 또는 Surf로만 이동 가능합니다!");


        // 포켓몬 진화 로직
        List<Pokemon> capturedPokemons = trainer.getCapturedPokemons();
        for (Pokemon pokemon : capturedPokemons) {
            if (isMoonPokemon(pokemon)) {
                Pokemon evolved = pokemon.evolve();
                System.out.println(pokemon.getPokemonName() + "이(가) 진화하여 " + evolved.getPokemonName() + "이(가) 되었습니다!");
            }
        }
    }

    private boolean isMoonPokemon(Pokemon pokemon) {
        // 특정 달 속성 포켓몬 체크 (푸린, 삐삐 등)
        return pokemon.getPokemonName().equalsIgnoreCase("purin") ||
                pokemon.getPokemonName().equalsIgnoreCase("bbibbi");
    }
}

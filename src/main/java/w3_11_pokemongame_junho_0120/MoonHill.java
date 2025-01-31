package w3_11_pokemongame_junho_0120;

import java.util.List;

public class MoonHill implements MapLocation {

    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        // 매개변수 중 trainer만 사용
        System.out.println("달맞이 동산으로 이동을 시도합니다.");

        // Fly 또는 Surf가 없으면 이동 불가
        if (!trainer.canFly() && !trainer.canSurf()) {
            System.out.println("달맞이 동산에는 Fly 또는 Surf로만 이동 가능합니다!");
            return; // 이동 불가능하면 메서드 종료
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
                capturedPokemons.set(i, evolvedPokemon); // 리스트에서 진화한 포켓몬으로 교체
                evolved = true;
            }
        }

        // 진화한 포켓몬이 없을 경우 메시지 출력
        if (!evolved) {
            System.out.println("진화할 포켓몬이 없습니다.");
        }
    }

    private boolean isMoonPokemon(Pokemon pokemon) {
        // 특정 달 속성 포켓몬 체크 (푸린, 삐삐 등)
        return pokemon.getPokemonName().equalsIgnoreCase("purin") ||
                pokemon.getPokemonName().equalsIgnoreCase("bbibbi");
    }
}

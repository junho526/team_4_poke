package w3_11_pokemongame_junho_0120;

import java.util.Random;

public class Grassland extends PokeMap {
    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        System.out.println("풀숲에 들어섰습니다. 야생 포켓몬과 만날 확률이 있습니다.");
        if (encounterWildPokemon()) {
            System.out.println("야생 포켓몬과 만났습니다!");
            trainer.hunt(wildPokemon);
        } else {
            System.out.println("아무 일도 일어나지 않았습니다.");
        }
    }

    private boolean encounterWildPokemon() {
        return new Random().nextInt(100) < 50; // 50% 확률로 야생 포켓몬과 만남
    }
}

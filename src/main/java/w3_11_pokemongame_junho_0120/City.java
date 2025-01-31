package w3_11_pokemongame_junho_0120;

public class City extends PokeMap {
    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        System.out.println("도시로 이동했습니다. 상대 트레이너를 만났습니다!");
        trainer.battle(enemyTrainer);
    }
}

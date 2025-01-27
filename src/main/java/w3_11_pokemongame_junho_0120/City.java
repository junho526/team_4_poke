package w3_11_pokemongame_junho_0120;

public class City implements MapLocation {
    @Override
    public void enter(Trainer trainer, Trainer enemyTrainer, Pokemon wildPokemon) {
        System.out.println("도시에 들어섰습니다. 상대 트레이너와 배틀할 준비를 하세요.");
        if (enemyTrainer != null) {
            trainer.battle(enemyTrainer); // 상대 트레이너와 배틀
        } else {
            System.out.println("현재 도시에 상대 트레이너가 없습니다.");
        }
    }
}

package w3_11_pokemongame_junho_0120;

// 몬스터볼
class MonsterBall extends PokeItem {
    private final double catchRate;

    public MonsterBall(String itemName, String description, double catchRate, int count) {
        super(itemName, description, count);
        this.catchRate = catchRate;
    }

    @Override
    public void useItem(Pokemon pokemon, Trainer trainer) {
        if (!isAvailable()) {
            System.out.println(getItemName() + "이(가) 부족합니다!");
            return;
        }

        decreaseCount(1); // 사용 시 개수 감소
        double successChance = Math.random();
        if (successChance <= catchRate) {
            trainer.getCapturedPokemons().add(pokemon);
            System.out.println(getItemName() + " 사용! " + pokemon.getPokemonName() + " 포획 성공!");
        } else {
            System.out.println(getItemName() + " 사용! " + pokemon.getPokemonName() + " 포획 실패!");
        }
    }
}


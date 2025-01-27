package w3_11_pokemongame_junho_0120;

// 힐 아이템
class HealItem extends PokeItem {
    private final int healAmount;

    public HealItem(String itemName, String description, int healAmount, int count) {
        super(itemName, description, count);
        this.healAmount = healAmount;
    }

    @Override
    public void useItem(Pokemon pokemon, Trainer trainer) {
        if (!isAvailable()) {
            System.out.println(getItemName() + "이(가) 부족합니다!");
            return;
        }

        decreaseCount(1); // 사용 시 개수 감소
        int newHP = Math.min(pokemon.getHP() + healAmount, 100); // 최대 HP: 100
        pokemon.setHP(newHP);
        System.out.println(getItemName() + " 사용! " + pokemon.getPokemonName() +
                "의 체력이 " + healAmount + "만큼 회복되었습니다. 현재 HP: " + pokemon.getHP());
    }
}


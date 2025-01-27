package w3_11_pokemongame_junho_0120;

public class MysticSummonItem extends PokeItem {

    public MysticSummonItem(String itemName, String description,int count) {
        super(itemName, description, count);
    }

    @Override
    public void useItem(Pokemon pokemon, Trainer trainer) {
        // 신비 포켓몬 소환 정보
        String pokemonName = "Celebi"; // 소환할 포켓몬 이름
        String type = "Mystic";
        int HP = 150;
        int level = 30;

        // 신비 포켓몬 소환
        MysticPokemon summonedPokemon = MysticPokemon.summonMysticPokemon(pokemonName, HP, level, type);
        System.out.println(getItemName() + " 사용! 신비 포켓몬 " + summonedPokemon.getPokemonName() + "이(가) 소환되었습니다.");

        // 트레이너가 소환된 포켓몬 추가
        trainer.getCapturedPokemonList().add(summonedPokemon);
    }
}

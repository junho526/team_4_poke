package w3_11_pokemongame_junho_0120;

public class EvolutionItem extends PokeItem {

    public EvolutionItem(String itemName, String description, int count) {
        super(itemName, description, count); // count 포함
    }

    @Override
    public void useItem(Pokemon pokemon, Trainer trainer) {
        // 진화 단계 계산 및 이름 업데이트
        int nextEvolutionStage = 1;
        String newName = pokemon.getPokemonName();

        if (pokemon instanceof EvolvedPokemon) {
            EvolvedPokemon evolvedPokemon = (EvolvedPokemon) pokemon;
            nextEvolutionStage = evolvedPokemon.getEvolutionStage() + 1;
            newName = evolvedPokemon.getPokemonName().split(" v")[0];
        }

        // 새로운 진화된 포켓몬 생성
        EvolvedPokemon nextEvolution = new EvolvedPokemon(
                newName + " v" + nextEvolutionStage,
                pokemon.getHP(),
                pokemon.getLevel(),
                nextEvolutionStage,
                pokemon.getType()
        );

        // 기존 포켓몬을 제거하고 진화된 포켓몬 추가
        trainer.getCapturedPokemons().remove(pokemon);
        trainer.getCapturedPokemons().add(nextEvolution);

        System.out.println(pokemon.getPokemonName() + "이(가) 진화하여 " + nextEvolution.getPokemonName() + "이(가) 되었습니다!");
    }
}

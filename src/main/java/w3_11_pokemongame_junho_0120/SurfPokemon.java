package w3_11_pokemongame_junho_0120;

import lombok.Getter;
//

@Getter
public class SurfPokemon extends Pokemon implements ISurfable {

    public SurfPokemon(String pokemonName, int HP, int level, String type) {
        super(pokemonName, HP, level, type);
    }
    // FlyPokemon과 동일하게 수정
    public static SurfPokemon createSurfPokemon(String pokemonName, int HP, int level, String type) {
        if (!type.equalsIgnoreCase("water")) {
            throw new IllegalArgumentException("물타입만 파도타기 가능합니다.");
        }
        return new SurfPokemon(pokemonName, HP, level, type);
    }

    @Override
    public void surf(String tgCity) {
        if (!this.getType().equalsIgnoreCase("Water")) {
            System.out.println(this.getPokemonName() + " 수영할 수 없습니다!");
            return;
        }
        System.out.println(this.getPokemonName() + "는(은)" + tgCity + "로 이동가능하다!");
    }

    @Override
    public void crossOcean(String tgCity) {
        System.out.println(this.getPokemonName() + "는(은)" + tgCity + "로 이동했다!");
    }
}

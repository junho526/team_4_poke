package w3_11_pokemongame_junho_0120;

import lombok.Getter;

@Getter
public class FlyPokemon extends Pokemon implements IFlyable {
    private FlyPokemon(String pokemonName, int HP, int level, String type) {
        super(pokemonName, HP, level, type);
    }

    // 생성자 호출을 정해진 방법으로만 약속해서 호출하기!
    public static FlyPokemon createFlyPokemon(String pokemonName, int HP, int level, String type) {
        if (!type.equalsIgnoreCase("sky")) {
            throw new IllegalArgumentException("비행타입만 비행가능합니다.");
        }
        return new FlyPokemon(pokemonName, HP, level, type);
    }

    //
    @Override
    public void fly(String tgCity) {
        if (!this.getType().equalsIgnoreCase("sky")) {
            System.out.println(this.getPokemonName() + "날 수 없습니다");
            return;
        }
        System.out.println(this.getPokemonName() + "는(은)" + tgCity + "로 이동가능하다!");
    }

    @Override
    public void crossOcean(String tgCity) {

        System.out.println(this.getPokemonName() + " 는(은) " + tgCity + "로 이동했다!");
    }
}
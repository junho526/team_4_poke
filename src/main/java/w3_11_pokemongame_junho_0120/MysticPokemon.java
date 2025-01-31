package w3_11_pokemongame_junho_0120;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Getter
public class MysticPokemon extends Pokemon {

    private final int mysticfactor= new Random().nextInt(1,7);
    private static final Map<String, MysticPokemon> mysticPokemons = new HashMap<>();
    private boolean isHidden; // 숨겨진 상태

    // private 생성자: 외부에서 직접 호출 불가
    MysticPokemon(String pokemonName, int HP, int level, String type) {
        super(pokemonName, HP, level, type);
        this.isHidden = true; // 기본적으로 숨겨진 상태로 설정
    }

    protected static MysticPokemon summonMysticPokemon(String pokemonName, int HP, int level, String type) {
        MysticPokemon pokemon = mysticPokemons.get(pokemonName);
        if (pokemon != null) {
            System.out.println("이미 존재하는 신비 포켓몬: " + pokemonName);
            pokemon.isHidden = false; // 소환 시 드러난 상태로 설정
            return pokemon;
        }

        MysticPokemon newMysticPokemon = new MysticPokemon(pokemonName, HP, level, type);
        newMysticPokemon.isHidden = false; // 소환 즉시 드러난 상태로 설정
        mysticPokemons.put(pokemonName, newMysticPokemon);
        System.out.println("새로운 신비 포켓몬 " + pokemonName + "이(가) 생성되었습니다.");
        return newMysticPokemon;
    }

    /**
     * 신비 포켓몬 전체 반환.
     * @return 이름별 MysticPokemon 맵
     */
    public static Map<String, MysticPokemon> getAllMysticPokemons() {
        return mysticPokemons;
    }
}

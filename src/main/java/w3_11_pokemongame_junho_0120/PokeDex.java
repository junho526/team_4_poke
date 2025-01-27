package w3_11_pokemongame_junho_0120;


import java.util.HashMap;
import java.util.Map;

public class PokeDex {
    // 포켓몬 정보 관리할 데이터 타입 필드 추가
    static Map<String, Pokemon> pokemonByName = new HashMap<>();
    static Map<PokeCategory, Map<String, Pokemon>> pokemonByCategory = new HashMap<>();

    static {
        // 3. 포켓몬 사전 더미데이터 생성
        // 모든 PokeCategory 값을 키로 하여 내부 Map 을 초기화
        for (PokeCategory category : PokeCategory.values()) {
            pokemonByCategory.put(category, new HashMap<>());
        }
        // 아래에 더미데이터 생성 (매 객체 생성 시 해당 객체로 pokemonByName, pokemonByCategory 둘 다 업데이트 해야 함)
        Pokemon p1 = new Pokemon("butterfly",60,10,"sky");
        Pokemon p2 = new Pokemon("mu",100,20,"legendary");
        Pokemon p3 = new Pokemon("picachu",70,12,"electric");
        Pokemon p4 = new Pokemon("eevee",80,12,"normal");
        // 위 변수들은 리스트로 변경,
        // 아래 구문들을 반복문으로 변경
        pokemonByName.put("1", p1);
        pokemonByCategory.get(PokeCategory.NORMAL).put(p1.getPokemonName(), p1);
        pokemonByName.put("2", p2);
        pokemonByCategory.get(PokeCategory.NORMAL).put(p2.getPokemonName(), p2);
        pokemonByName.put("3", p3);
        pokemonByCategory.get(PokeCategory.ELECTRIC).put(p3.getPokemonName(), p3);
        pokemonByName.put("4", p4);
        pokemonByCategory.get(PokeCategory.NORMAL).put(p4.getPokemonName(), p4);
    }

    //
    public enum PokeCategory {
        WATER, FIRE, EARTH, SKY, LEGENDARY, MYSTIC, NORMAL, ELECTRIC
    }

    public static Pokemon searchPokemon(String name) {
        return pokemonByName.get(name);
    }
    public static Map<String, Pokemon> searchPokemon(PokeCategory category) {
        return pokemonByCategory.get(category);
    }
    // 아래 기능 필요 없음
//    public static Pokemon searchPokemon(PokeCategory category, String name) {
//        return searchPokemon(category).get(name);
//    };
}

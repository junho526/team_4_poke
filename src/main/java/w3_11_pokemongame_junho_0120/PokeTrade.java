package w3_11_pokemongame_junho_0120;

import java.util.Scanner;
import java.util.Random;

public class PokeTrade {

    public static void trade(Trainer self, Trainer opponentTrainer) {
        // 서로의 인벤토리 확인
        System.out.println("내 포켓몬: " + self.getCapturedPokemonList());
        System.out.println("상대 포켓몬: " + opponentTrainer.getCapturedPokemonList());

        //각자 포켓몬 선택
        // 자신의 포켓몬 선택
        System.out.println("교환할 자신의 포켓몬을 골라주세요.");
        Scanner scannerMy = new Scanner(System.in); // Scanner 객체 생성
        int myTradePokemon = scannerMy.nextInt(); // 숫자를 입력받아 저장
        System.out.println("선택한 자신의 포켓몬 번호: " + myTradePokemon);
        scannerMy.close();

        // 상대의 포켓몬 선택
        System.out.println("교환할 상대의 포켓몬을 골라주세요.");
        Scanner scannerOp = new Scanner(System.in); // Scanner 객체 생성
        int opponentTrainerTradePokemon = scannerOp.nextInt(); // 숫자를 입력받아 저장
        System.out.println("선택한 상대의 포켓몬 번호: " + opponentTrainerTradePokemon);
        scannerOp.close();

        // 포켓몬 교환
        Pokemon temp = self.getCapturedPokemonList().get(myTradePokemon); //변경을 위한 임시 변수 temp 생성하고 자신의 포켓몬 저장
        self.getCapturedPokemonList().set(myTradePokemon,
                opponentTrainer.getCapturedPokemonList().get(opponentTrainerTradePokemon)); // 상대 포켓몬을 자신의 리스트에 저장
        opponentTrainer.getCapturedPokemonList().set(opponentTrainerTradePokemon, temp); // 자신의 포켓몬을 상대 리스트에 저장

        // 이벤트 발생
        SpecialTradePokemon(self.getCapturedPokemonList().get(myTradePokemon));
        SpecialTradePokemon(opponentTrainer.getCapturedPokemonList().get(opponentTrainerTradePokemon));

        System.out.println(self + "와 " + opponentTrainer + "가 포켓몬을 교환했습니다!");
        System.out.println(self + "는 " + opponentTrainer.getCapturedPokemonList().get(opponentTrainerTradePokemon) + "을 받았습니다.");
        System.out.println(opponentTrainer + "는 " + self.getCapturedPokemonList().get(myTradePokemon) + "을 받았습니다.");

        //교환 결과 출력
        System.out.println("교환완료");
    }

    // 트레이드 시 특별 동작 발생 포켓몬
    private static void SpecialTradePokemon(Pokemon pokemon) {
        switch (pokemon.getPokemonName().toLowerCase()) {
            case "machoke":
                // 근육몬은 교환 시 마챔프로 변경
                System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 괴력몬으로 변했습니다!");
                pokemon.evolve();
                break;
            case "eevee":
                // 이브이는 교환 시 속성 변경
                String[] attributes = {"Fire", "Water", "Electric", "Psychic"};
                String newAttribute = attributes[new Random().nextInt(attributes.length)];
                System.out.println(pokemon.getPokemonName() + "이(가) 교환으로 인해 새로운 속성 " + newAttribute + "을(를) 얻었습니다!");
                break;
            default:
                System.out.println(pokemon.getPokemonName() + "은(는) 특별한 변화를 겪지 않았습니다.");
                break;
        }
    }
}



package w3_11_pokemongame_junho_0120;

import java.util.Scanner;

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

            // 상대의 포켓몬 선택

            System.out.println("교환할 상대의 포켓몬을 골라주세요.");

            Scanner scannerOp = new Scanner(System.in); // Scanner 객체 생성
            int opponentTrainerTradePokemon = scannerOp.nextInt(); // 숫자를 입력받아 저장
            System.out.println("선택한 상대의 포켓몬 번호: " + opponentTrainerTradePokemon);

            // 포켓몬 교환

            Pokemon tamp = self.getCapturedPokemonList().get(myTradePokemon);


        }
    }



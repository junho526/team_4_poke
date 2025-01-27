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


            //교환 결과 출력
            System.out.println("교환완료");
            System.out.println("결과: 내 포켓몬: " + self.getCapturedPokemonList());
            System.out.println("결과: 상대 포켓몬: " + opponentTrainer.getCapturedPokemonList());




            //


           }
    }



package w3_11_pokemongame_junho_0120;

import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 플레이어 트레이너 생성
        Trainer jiwoo = new PlayerTrainer("Jiwoo");
        Trainer trainer2 = new PlayerTrainer("trainer2");
        // 기본 포켓몬 추가
        Pokemon pikachu = new Pokemon("Pikachu", 100, 10, "Electric");
        jiwoo.getCapturedPokemons().add(pikachu);



        Pokemon pikachu2 = new Pokemon("Pikachu", 100, 10, "Electric");
        trainer2.getCapturedPokemons().add(pikachu2);


        // 기본 아이템 추가
        jiwoo.addItem(new MonsterBall("Monster Ball", "포켓몬을 포획할 수 있는 몬스터볼", 100,5));
        jiwoo.addItem(new HealItem("Potion", "포켓몬의 체력을 회복시켜주는 포션", 50,5));

        boolean playing = true;

        // 게임
        while (playing) {
            System.out.println("\n========== 메뉴 ==========");
            System.out.println("1: 이동하기");
            System.out.println("2: 포켓몬 확인");
            System.out.println("3: 아이템 확인");
            System.out.println("4: 게임 종료");
            System.out.println("5: 트레이드");
            System.out.print("선택: ");
            int choice = input.nextInt();

            switch (choice) {
                case 1:
                    jiwoo.move(); // 맵 이동
                    break;
                case 2:
                    System.out.println("Jiwoo가 소유한 포켓몬:");
                    jiwoo.getCapturedPokemons().forEach(pokemon -> {
                        System.out.println(pokemon.getPokemonName() +
                                " (Level: " + pokemon.getLevel() + ", HP: " + pokemon.getHP() + ")");
                    });
                    break;
                case 3:

                    jiwoo.showInventory();

                    break;
                case 4:
                    System.out.println("게임을 종료합니다.");
                    playing = false;
                    break;
                case 5:
                    jiwoo.trade(trainer2);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }

        input.close();
    }
}

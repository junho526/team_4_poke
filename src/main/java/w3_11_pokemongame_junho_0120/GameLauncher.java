package w3_11_pokemongame_junho_0120;

import java.lang.reflect.Field;
import java.util.Scanner;

public class GameLauncher {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // 플레이어 트레이너 생성
        Trainer trainer1 = new PlayerTrainer("Jiwoo");
        Trainer trainer2 = new PlayerTrainer("trainer2");
        // 기본 포켓몬 추가

        MysticPokemon myMystic = new MysticPokemon("근육몬",100,10,"fighting");
        Class<?> mysticClass= myMystic.getClass();
        try {
            Field hiddenField = mysticClass.getDeclaredField("mysticfactor");
            hiddenField.setAccessible(true);
            int hiddenValue=(int)hiddenField.get(myMystic);
            System.out.println("신비숫자"+hiddenValue);
        }catch (NoSuchFieldException |IllegalAccessException e) {
            System.err.println(e.getMessage());
        }



        Pokemon pikachu = new Pokemon("Pikachu", 100, 10, "Electric");
        Pokemon purin = new Pokemon("purin", 100, 10, "NORMAL");
        //FlyPokemon butterfly = FlyPokemon.createFlyPokemon("butterfly", 100, 10, "sky");
        SurfPokemon star = SurfPokemon.createSurfPokemon("star", 100, 10, "water");
        trainer1.getCapturedPokemons().add(pikachu);
        trainer1.getCapturedPokemons().add(purin);
        //jiwoo.getCapturedPokemons().add(butterfly);
        trainer1.getCapturedPokemons().add(star);



        Pokemon eevee = new Pokemon("Eevee", 80, 12, "Normal");
        Pokemon machoke = new Pokemon("machoke", 80, 12, "fighting");
        trainer2.getCapturedPokemons().add(eevee);
        trainer2.getCapturedPokemons().add(machoke);


        // 기본 아이템 추가
        trainer1.addItem(new MonsterBall("Monster Ball", "포켓몬을 포획할 수 있는 몬스터볼", 100,5));
        trainer1.addItem(new HealItem("Potion", "포켓몬의 체력을 회복시켜주는 포션", 50,5));

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
                    trainer1.move(); // 맵 이동
                    break;
                case 2:
                    System.out.println("Jiwoo가 소유한 포켓몬:");
                    trainer1.getCapturedPokemons().forEach(pokemon -> {
                        System.out.println(pokemon.getPokemonName() +
                                " (Level: " + pokemon.getLevel() + ", HP: " + pokemon.getHP() + ")");
                    });
                    break;
                case 3:

                    trainer1.showInventory();

                    break;
                case 4:
                    System.out.println("게임을 종료합니다.");
                    playing = false;
                    break;
                case 5:
                    trainer1.tradeWith(trainer2,input);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            }
        }

        input.close();
    }
}

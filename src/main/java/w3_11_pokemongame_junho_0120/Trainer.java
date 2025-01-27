package w3_11_pokemongame_junho_0120;

import java.util.*;

public abstract class Trainer implements ITrainer {
    List<Pokemon> capturedPokemonList = new ArrayList<>();
    Map<String, Pokemon> capturedPokemonByName = new HashMap<>();
    private final List<PokeItem> inventory = new ArrayList<>();
    private final String trainerName;
    Scanner inputReader = new Scanner(System.in);

    public Trainer(String trainerName) {
        this.trainerName = trainerName;
    }

    public void trade(Trainer opponentTrainer) {
        // 서로의 인벤토리 확인

        System.out.println("내 포켓몬: " + capturedPokemonList);
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

        Pokemon tamp = capturedPokemonList.get(myTradePokemon);



    }


    @Override
    public void hunt(Pokemon wildPokemon) {
        System.out.println("야생의 포켓몬 " + wildPokemon.getPokemonName() + "을(를) 만났습니다!");
        System.out.println("1: battle / 2: capture / else: pass");
        int battleOrCapture = inputReader.nextInt();
        switch (battleOrCapture) {
            case 1:
                battle(wildPokemon);
                break;
            case 2:
                Pokemon capturedPokemon = capture(wildPokemon);
                if (capturedPokemon != null) {
                    capturedPokemonList.add(capturedPokemon);
                    capturedPokemonByName.put(capturedPokemon.getPokemonName(), capturedPokemon);
                    System.out.println(wildPokemon.getPokemonName() + "을(를) 포획했습니다!");
                } else {
                    System.out.println(wildPokemon.getPokemonName() + " 포획에 실패했습니다.");
                }
                break;
            default:
                System.out.println("무사히 도망쳤다.");
                break;
        }
    }

    @Override
    public void addItem(PokeItem item) {
        boolean itemExists = false;

        // 이미 존재하는 아이템인지 확인
        for (PokeItem inventoryItem : inventory) {
            if (inventoryItem.getItemName().equalsIgnoreCase(item.getItemName())) {
                inventoryItem.increaseCount(item.getCount()); // 기존 아이템 수량 증가
                itemExists = true;
                break;
            }
        }

        if (!itemExists) {
            inventory.add(item); // 새로운 아이템 추가
        }

        System.out.println(item.getItemName() + "이(가) " + item.getCount() + "개 추가되었습니다.");
    }


    @Override
    public Pokemon capture(Pokemon wildPokemon) {
        // 60% 확률로 포획 성공
        if (Math.random() < 0.6) {
            return wildPokemon;
        }
        return null; // 포획 실패
    }

    @Override
    public void battle(Pokemon wildPokemon) {
        while (!capturedPokemonList.isEmpty() && wildPokemon.getHP() > 0) {
            Pokemon currentPokemon = chooseNextPokemon();
            if (currentPokemon == null) {
                System.out.println(trainerName + "의 모든 포켓몬이 기절했습니다. 게임 종료.");
                return;
            }

            System.out.println(currentPokemon.getPokemonName() + "을(를) 전투에 소환했습니다!");
            System.out.println("현재 포켓몬 상태: " + currentPokemon); // 레벨, HP 포함 출력

            while (currentPokemon.getHP() > 0 && wildPokemon.getHP() > 0) {
                int damageDealt = currentPokemon.attack(wildPokemon);
                System.out.println(currentPokemon.getPokemonName() + "이(가) " + wildPokemon.getPokemonName() + "에게 " + damageDealt + "의 데미지를 입혔습니다!");

                if (wildPokemon.getHP() == 0) {
                    System.out.println("야생 포켓몬 " + wildPokemon.getPokemonName() + "을(를) 쓰러뜨렸습니다!");
                    currentPokemon.levelUp();
                    System.out.println("레벨 상승! " + currentPokemon);
                    return;
                }

                int damageReceived = wildPokemon.attack(currentPokemon);
                System.out.println(wildPokemon.getPokemonName() + "이(가) " + currentPokemon.getPokemonName() + "에게 " + damageReceived + "의 데미지를 입혔습니다!");

                if (currentPokemon.getHP() == 0) {
                    System.out.println(currentPokemon.getPokemonName() + "이(가) 기절했습니다!");
                    capturedPokemonList.remove(currentPokemon);
                } else {
                    System.out.println("현재 포켓몬 상태: " + currentPokemon); // 상태 출력
                }
            }
        }

        if (wildPokemon.getHP() > 0) {
            System.out.println("야생 포켓몬 " + wildPokemon.getPokemonName() + "에게 패배했습니다.");
        }
    }


    @Override
    public void battle(ITrainer enemyTrainer) {
        System.out.println(this.getTrainerName() + "과(와) " + enemyTrainer.getTrainerName() + "의 배틀 시작!");

        while (!this.capturedPokemonList.isEmpty() && !enemyTrainer.getCapturedPokemons().isEmpty()) {
            // 현재 트레이너와 상대 트레이너의 포켓몬 선택
            Pokemon myPokemon = chooseNextPokemon();
            Pokemon enemyPokemon = chooseEnemyPokemon(enemyTrainer);

            if (myPokemon == null || enemyPokemon == null) {
                System.out.println("배틀을 진행할 포켓몬이 없습니다.");
                break;
            }

            System.out.println(this.getTrainerName() + "의 " + myPokemon.getPokemonName() + " (Level: " + myPokemon.getLevel() + ", HP: " + myPokemon.getHP() + ")");
            System.out.println(enemyTrainer.getTrainerName() + "의 " + enemyPokemon.getPokemonName() + " (Level: " + enemyPokemon.getLevel() + ", HP: " + enemyPokemon.getHP() + ")");

            // 두 포켓몬 간 배틀
            while (myPokemon.getHP() > 0 && enemyPokemon.getHP() > 0) {
                int myDamage = myPokemon.attack(enemyPokemon);
                System.out.println(myPokemon.getPokemonName() + "이(가) " + enemyPokemon.getPokemonName() + "에게 " + myDamage + "의 데미지를 입혔습니다!");
                System.out.println(enemyPokemon.getPokemonName() + "의 남은 HP: " + enemyPokemon.getHP());

                if (enemyPokemon.getHP() == 0) {
                    System.out.println(enemyTrainer.getTrainerName() + "의 " + enemyPokemon.getPokemonName() + "이(가) 기절했습니다!");
                    enemyTrainer.getCapturedPokemons().remove(enemyPokemon);
                    break;
                }

                int enemyDamage = enemyPokemon.attack(myPokemon);
                System.out.println(enemyPokemon.getPokemonName() + "이(가) " + myPokemon.getPokemonName() + "에게 " + enemyDamage + "의 데미지를 입혔습니다!");
                System.out.println(myPokemon.getPokemonName() + "의 남은 HP: " + myPokemon.getHP());

                if (myPokemon.getHP() == 0) {
                    System.out.println(this.getTrainerName() + "의 " + myPokemon.getPokemonName() + "이(가) 기절했습니다!");
                    this.capturedPokemonList.remove(myPokemon);
                    break;
                }
            }
        }

        if (this.capturedPokemonList.isEmpty()) {
            System.out.println(this.getTrainerName() + "의 모든 포켓몬이 기절했습니다. " + enemyTrainer.getTrainerName() + "의 승리!");
        } else if (enemyTrainer.getCapturedPokemons().isEmpty()) {
            System.out.println(enemyTrainer.getTrainerName() + "의 모든 포켓몬이 기절했습니다. " + this.getTrainerName() + "의 승리!");
            // 승리 시 보상: 진화 아이템과 몬스터볼 제공
            EvolutionItem evolutionItem = new EvolutionItem("Evolution Stone", "포켓몬을 진화시킬 수 있는 돌",1);
            MonsterBall monsterBall = new MonsterBall("Monster Ball", "포켓몬을 포획할 수 있는 몬스터볼", 100,5);
            this.addItem(evolutionItem);
            this.addItem(monsterBall);
            System.out.println("보상으로 진화 아이템과 몬스터볼을 획득했습니다!");
        }
    }


    public void move() {
        System.out.println("어디로 이동하시겠습니까?");
        System.out.println("1: 풀숲 (야생 포켓몬과 만남)\n2: 도시 (상대 트레이너와 만남)");
        int choice = inputReader.nextInt();

        switch (choice) {
            case 1:
                System.out.println("풀숲으로 이동했습니다.");
                hunt(generateWildPokemon());
                break;
            case 2:
                System.out.println("도시로 이동했습니다.");
                battle(generateRandomTrainer());
                break;
            default:
                System.out.println("이동을 취소했습니다.");
        }
    }

    private Pokemon generateWildPokemon() {
        return new Pokemon("야생 포켓몬", 50, 5, "Normal"); // 임의의 야생 포켓몬 생성
    }

    private Trainer generateRandomTrainer() {
        Trainer randomTrainer = new AITrainer("랜덤 트레이너");
        randomTrainer.getCapturedPokemons().add(new Pokemon("랜덤 포켓몬", 70, 10, "Fire"));
        return randomTrainer;
    }


    private Pokemon chooseEnemyPokemon(ITrainer enemyTrainer) {
        List<Pokemon> enemyPokemons = enemyTrainer.getCapturedPokemons();
        if (!enemyPokemons.isEmpty()) {
            return enemyPokemons.get(0); // 상대 포켓몬의 첫 번째 포켓몬 선택
        }
        return null;
    }

    @Override
    public Pokemon searchDex(String pokemonName) {
        return PokeDex.searchPokemon(pokemonName);
    }

    @Override
    public Map<String, Pokemon> searchDex(PokeDex.PokeCategory category) {
        return PokeDex.searchPokemon(category);
    }

    @Override
    public List<Pokemon> getCapturedPokemons() {
        return capturedPokemonList;
    }

    @Override
    public List<PokeItem> getInventory() {
        return inventory;
    }

    @Override
    public void useItem(String itemName, Pokemon targetPokemon) {
        PokeItem itemToUse = null;

        for (PokeItem item : inventory) {
            if (item.getItemName().equalsIgnoreCase(itemName)) {
                itemToUse = item;
                break;
            }
        }

        if (itemToUse != null) {
            if (itemToUse.getCount() > 0) {
                itemToUse.useItem(targetPokemon, this);
                if (itemToUse.getCount() <= 0) {
                    inventory.remove(itemToUse); // 수량이 0이 되면 제거
                }
            } else {
                System.out.println(itemToUse.getItemName() + "의 수량이 부족합니다.");
            }
        } else {
            System.out.println("인벤토리에 " + itemName + "이(가) 없습니다.");
        }
    }
    public void showInventory() {
        System.out.println("현재 인벤토리:");
        for (PokeItem item : inventory) {
            System.out.println(item.getItemName() + " - " + item.getDescription() + " (수량: " + item.getCount() + ")");
        }
    }

    public Pokemon chooseNextPokemon() {
        if (capturedPokemonList.isEmpty()) {
            System.out.println(trainerName + "의 모든 포켓몬이 기절했습니다.");
            return null;
        }

        System.out.println("사용 가능한 포켓몬:");
        for (int i = 0; i < capturedPokemonList.size(); i++) {
            Pokemon pokemon = capturedPokemonList.get(i);
            System.out.println((i + 1) + ". " + pokemon.getPokemonName() + " (HP: " + pokemon.getHP() + ")");
        }

        System.out.print("소환할 포켓몬 번호를 입력하세요: ");
        int choice = inputReader.nextInt() - 1;

        if (choice >= 0 && choice < capturedPokemonList.size()) {
            return capturedPokemonList.get(choice);
        } else {
            System.out.println("잘못된 선택입니다. 다시 시도하세요.");
            return chooseNextPokemon();
        }
    }

    public List<Pokemon> getCapturedPokemonList() {
        return capturedPokemonList;
    }

}

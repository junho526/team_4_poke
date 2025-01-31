package w3_11_pokemongame_junho_0120;

import lombok.Getter;

@Getter
public class Pokemon implements IPokemon {
    private String pokemonName;
    private String customName;
    private int HP;
    private int level;
    private String type;

    public Pokemon(String pokemonName , int HP, int level, String type) {
        this.pokemonName = pokemonName;
        this.customName = pokemonName;
        this.HP = HP;
        this.level = level;
        this.type = type;
    }

    @Override
    public int attack(Pokemon tgPokemon) {
        int evolutionStage = (this instanceof EvolvedPokemon) ? ((EvolvedPokemon) this).getEvolutionStage() : 0;

        // 공격력: (10 + 진화 단계) * 레벨
        int damage = (10 + evolutionStage) * this.getLevel();
        tgPokemon.setHP(Math.max(0, tgPokemon.getHP() - damage));
        System.out.println(this.getPokemonName() + "이(가) " + tgPokemon.getPokemonName() + "에게 " + damage + "의 데미지를 입혔습니다.");
        System.out.println(tgPokemon.getPokemonName() + "의 남은 HP: " + tgPokemon.getHP());

        return damage; // 공격 데미지 반환
    }

    public int counterAttack(Pokemon attacker) {
        int evolutionStage = (this instanceof EvolvedPokemon) ? ((EvolvedPokemon) this).getEvolutionStage() : 0;

        // 반격 공격력: (10 + 진화 단계) * 레벨
        int counterDamage = (10 + evolutionStage) * this.getLevel();
        attacker.setHP(Math.max(0, attacker.getHP() - counterDamage));
        System.out.println(this.getPokemonName() + "이(가) " + attacker.getPokemonName() + "에게 반격으로 " + counterDamage + "의 데미지를 입혔습니다.");
        System.out.println(attacker.getPokemonName() + "의 남은 HP: " + attacker.getHP());

        return counterDamage; // 반격 데미지 반환
    }


    protected void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public void flee(int enemyLv) {
        if (this.level < enemyLv) {
            System.out.println(this.pokemonName + " 도망칠수가 없다!");
        } else {
            System.out.println(this.pokemonName + " 무사히 도망쳤다!");
        }
    }

    @Override
    public Pokemon evolve() {
        System.out.println(this.pokemonName + " 진화했다!");
        return new EvolvedPokemon(this.pokemonName + " Evo", this.HP + 20, this.level + 1, 1, this.type);
    }

    public void levelUp() {
        this.level += 1; // 레벨 증가
        this.HP += this.level; // 체력 회복
        System.out.println(this.pokemonName + "의 레벨이 " + this.level + "로 상승했습니다! 체력이 " + this.level + "만큼 회복되었습니다.");
    }

    public void heal(int healAmount) {
        this.HP += healAmount;
        System.out.println(this.pokemonName + "의 체력이 " + healAmount + "만큼 회복되었습니다! 현재 HP: " + this.HP);
    }

    @Override
    public String toString() {
        return getPokemonName() + " (Level: " + getLevel() + ", HP: " + getHP() + ")";
    }

    public void setType(String newAttribute) {
    }
}

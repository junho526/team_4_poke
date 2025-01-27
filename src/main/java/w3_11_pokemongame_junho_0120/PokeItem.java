package w3_11_pokemongame_junho_0120;

public abstract class PokeItem {
    private String itemName;
    private String description;
    private int count;

    public PokeItem(String itemName, String description, int count) {
        this.itemName = itemName;
        this.description = description;
        this.count = count;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void increaseCount(int amount) {
        count += amount;
    }

    public void decreaseCount(int amount) {
        if (count >= amount) {
            count -= amount;
        } else {
            System.out.println(itemName + "의 수량이 부족합니다!");
        }
    }

    public boolean isAvailable() {
        return count > 0;
    }

    public abstract void useItem(Pokemon pokemon, Trainer trainer);
}

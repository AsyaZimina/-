package lesson_05;

public class Bowl {
	private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int amount) {
        if (amount <= 0) {
            System.out.println("Количество еды должно быть больше!");
            return false;
        }
        if (food >= amount) {
            food -= amount;
            return true;
        }
        System.out.println("Мало еды в миске. Осталось " + food + " гр.");
        return false;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("Добавлено " + amount + " гр. еды в миску");
        } else {
            System.out.println("Количество добавляемой еды должно быть больше!");
        }
    }

    public int getFood() {
        return food;
    }
}

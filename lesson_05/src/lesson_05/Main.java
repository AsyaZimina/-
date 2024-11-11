package lesson_05;

public class Main {

	public static void main(String[] args) {
	        Dog dog1 = new Dog("Бобик");
	        Dog dog2 = new Dog("Шарик");

	        Cat cat1 = new Cat("Мурзик");
	        Cat cat2 = new Cat("Матроскин");

	        System.out.println("Всего животных: " + Animal.getAnimalCount());
	        System.out.println("Всего собак: " + Dog.getDogCount());
	        System.out.println("Всего котов: " + Cat.getCatCount());

	        dog1.run(300);
	        dog1.swim(5);

	        dog2.run(600);
	        dog2.swim(15);

	        cat1.run(150);
	        cat1.swim(10);

	        Bowl bowl = new Bowl(20);
	        Cat[] cats = {cat1, cat2};

	        for (Cat cat : cats) {
	            cat.eat(bowl, 10);
	            System.out.println(cat.getName() + " сыт: " + cat.isFull());
	        }
	        System.out.println("Остаток еды в миске: " + bowl.getFood() + " гр.");

	        bowl.addFood(15);
	        System.out.println("Добавили еды. Теперь в миске: " + bowl.getFood() + " гр.");

	        for (Cat cat : cats) {
	            if (!cat.isFull()) {
	                cat.eat(bowl, 10);
	                System.out.println(cat.getName() + " сыт: " + cat.isFull());
	            }
	        }
	        System.out.println("Остаток еды в миске: " + bowl.getFood() + " гр.");
	        
	        System.out.println("=============================================================");
	        
			System.out.println("Фигуры");
	        Shape circle = new Circle(7, "Розовый", "Фиолетовый");
	        Shape rectangle = new Rectangle(2, 8, "Голубой", "Синий");
	        Shape triangle = new Triangle(4, 5, 6, "Серый", "Черный");

	        System.out.println("\nКруг:");
	        circle.printCharacteristics();

	        System.out.println("\nПрямоугольник:");
	        rectangle.printCharacteristics();

	        System.out.println("\nТреугольник:");
	        triangle.printCharacteristics();
	        
	        

	}

}

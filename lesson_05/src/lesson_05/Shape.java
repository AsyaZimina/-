package lesson_05;

public interface Shape {
	double getPerimeter();
    double getArea();
    String getFillColor();
    String getBorderColor();

    default void printCharacteristics() {
        System.out.println("Периметр: " + getPerimeter());
        System.out.println("Площадь: " + getArea());
        System.out.println("Цвет фона: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
    }
}

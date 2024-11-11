package org.example;

public class Park {
    public String name;

    public Park(String name) {
        this.name = name;
    }

    public class Attraction {
        public String attractionName;
        public String workTime;
        public double price;

        public Attraction(String attractionName, String workTime, double price) {
            this.attractionName = attractionName;
            this.workTime = workTime;
            this.price = price;
        }

        public void printInfo() {
            System.out.println("Название: " + attractionName);
            System.out.println("Время работы: " + workTime);
            System.out.println("Стоимость: " + price + "руб");
        }
    }
}

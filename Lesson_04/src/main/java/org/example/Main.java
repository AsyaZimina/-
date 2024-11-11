package org.example;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Бовина Марина Викторовна", "Учитель", "bov@gmail.com", "89990000000", 45000, 50);
        employees[1] = new Employee("Кузнецова Юлия Васильевна", "Повар", "kuz@gmail.com", "89991111111", 30000, 45);
        employees[2] = new Employee("Соболева Светлана Вячеславовна", "Медицинская сестра", "sob@gmail.com", "89992222222", 30000, 28);
        employees[3] = new Employee("Корнеева Ирина Николаевна", "Завуч по УВР", "kor@gmail.com", "89993333333", 50000, 67);
        employees[4] = new Employee("Плаксин Сергей Михайлович", "Охранник", "plak@gmail.com", "89994444444", 20000, 30000);
        employees[0].printInfo();
        employees[1].printInfo();
        employees[2].printInfo();
        employees[3].printInfo();
        employees[4].printInfo();

        System.out.println("=====================================================================");

        Park park =new Park("Park");
        Park.Attraction attractionOne = park.new Attraction("Attraction1", "8.00-20.00", 100);
        attractionOne.printInfo();
    }

}
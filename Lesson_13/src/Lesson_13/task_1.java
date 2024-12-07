package Lesson_13;
import java.util.HashMap;

public class task_1 {
    private static String analyzedData = "Мама" + " Папа" + " Сестра" + " Сестра" + " Брат" + " Брат" + " Бабушка" +
" Бабушка" + " Дедушка" + " Тётя" + " Дядя" + " Праздники" + " Праздники" + " Традиции" + " Выходные" + " Любовь";
    private static final String format = "|%1$-40.38s|%2$-30.28s|\n";
    private static final HashMap<String, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) {

        String[] regularArray = analyzedData.split(" ");  
        System.out.println("task_1");
        System.out.println("Всего " + regularArray.length + " слов.");
        for (String s : regularArray) {         
            hashMap.putIfAbsent(s, 0);        
            hashMap.put(s, hashMap.get(s) + 1);    
        }  
        hashMap.entrySet().stream().sorted(HashMap.Entry.<String, Integer>comparingByValue().reversed()).forEach(stringIntegerEntry -> {
            String k = stringIntegerEntry.getKey();
            Integer v = stringIntegerEntry.getValue();
            System.out.format(format, " " + k + " ", "   " + +v + " раз.");
        });
        
        System.out.println("_________________________________________________________________________________________");

        Phone_book pB = new Phone_book();
        System.out.println("task_2");
        pB.add("Авдеев", "+7 (999) 111-11-11");
        pB.add("Бубнов", "+7 (999) 222-22-22");
        pB.add("Волков", "+7 (999) 333-33-33");
        pB.add("Гнездилов", "+7 (999) 444-44-44");
        pB.add("Дроздов", "+7 (999) 555-55-55");
        pB.add("Западалов", "+7 (999) 666-66-66");
        pB.add("Кузнецов", "+7 (999) 777-77-77");
        pB.add("Левыкин", "+7 (999) 888-88-88");
        pB.get("Левыкин");
        pB.get("Гнездилов");
        pB.get("Волков");
    }
}


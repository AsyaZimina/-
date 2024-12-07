package Lesson_13;
import java.util.ArrayList;
import java.util.HashMap;

public class Phone_book {
	private final HashMap<String, ArrayList<String>> items = new HashMap<>();

    public void add(String surname, String phoneNumber) {
        if (items.get(surname) != null) {
            if (!items.get(surname).contains(phoneNumber)) {
                items.get(surname).add(phoneNumber);
            }
        } else {
            ArrayList<String> arr = new ArrayList<>();
            arr.add(phoneNumber);
            items.put(surname, arr);
        }
    }

    public void get(String surname) {
        String format = "|%1$-20.18s| контактный телефон(ы): ";
        System.out.format(format, surname);
        System.out.println(items.get(surname));
    }
}

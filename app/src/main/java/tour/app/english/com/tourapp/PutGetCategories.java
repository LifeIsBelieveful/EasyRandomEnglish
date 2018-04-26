package tour.app.english.com.tourapp;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class PutGetCategories {

    List<String> categories = new ArrayList<>();

    public void putCategories() {
        categories.add(0, "Airport");
        categories.add(1, "Accomodation");
        categories.add(2, "Restaurant");
    }

    public String getCategory(int index) {
        return categories.get(index);
    }
}

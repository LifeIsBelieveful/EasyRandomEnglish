package tour.app.english.com.tourapp;

import android.icu.util.ULocale;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionData {

    Map<String,List<Map<String, String>>> question = new HashMap<String, List<Map<String,String>>>();

    List<Map<String, String>> questionDataList = new ArrayList<>();
    Map<String, String> questionDataMap = new HashMap<>();

    List<String> Category = new ArrayList<String>();
    Map<String, String> QuestionEnglish = new HashMap<>();
    Map<String, String> QuestionKorean = new HashMap<>();

    public void putCategory(){
        Category.add("Airport");
        Category.add("Restaurant");
        Category.add("Accomodation");

    }

    public void putQuestionEnglish(){
        QuestionEnglish.put(Category.get(0),"I'd/like/to book/airplane/to NewYork");
    }

    public void putQuestionKorean(){
        QuestionKorean.put(Category.get(0),"뉴욕으로 가는 비행기를 예약하고 싶습니다");
    }


    public List<Map<String, String>> putQuestiondata(String number, String questiondata ) {
        questionDataMap = new HashMap<>();
        questionDataMap.put(number, questiondata);
        questionDataList.add(questionDataMap);

        return questionDataList;
    }

    public Map<String, List<Map<String, String>>> putQuestion (String Category, List<Map<String, String>> questionData){
        question = new HashMap<>();
        question.put(Category, questionDataList);

        return question;
    }




}

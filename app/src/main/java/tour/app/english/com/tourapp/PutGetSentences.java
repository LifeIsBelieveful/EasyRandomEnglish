package tour.app.english.com.tourapp;

import java.util.ArrayList;
import java.util.List;

public class PutGetSentences {

    List<String> englishquestions = new ArrayList<>();
    List<String> Koreanquestions = new ArrayList<>();
    List<String> AnswerCheck = new ArrayList<>();

    public void putsentece(String category) {

        if (category.equals("Airport")) {
            englishquestions.add("I'd /like /to book /a flight /to NewYork ");
            englishquestions.add("I'd /like /to change /my reservation ");
            englishquestions.add("What /is the /arrival /time? ");

            Koreanquestions.add("뉴욕행 비행기를 예약하고 싶습니다");
            Koreanquestions.add("예약을 변경하고 싶어요.");
            Koreanquestions.add("도착 시간은 언제입니까?");

            AnswerCheck.add("I'd like to book a flight to NewYork ");
            AnswerCheck.add("I'd like to change my reservation ");
            AnswerCheck.add("What is the arrival time? ");
        } else if (category.equals("Accomodation")) {
            englishquestions.add("I'd /like /to make /a reservation ");
            englishquestions.add("Is /there /a room /available? ");
            englishquestions.add("How /much /is /it /per /night? ");

            Koreanquestions.add("예약을 하고 싶습니다.");
            Koreanquestions.add("빈 방 있나요?");
            Koreanquestions.add("1박에 얼마인가요?");

            AnswerCheck.add("I'd like to make a reservation ");
            AnswerCheck.add("Is there a room available? ");
            AnswerCheck.add("How much is it per night? ");
        } else if (category.equals("Restaurant")) {
            englishquestions.add("I'd /like /to book /a table /for /three /at /seven ");
            englishquestions.add("What /do /you /recommend? ");
            englishquestions.add("What /kind /of /dish /is /it? ");

            Koreanquestions.add("7시에 3명 예약하고 싶습니다.");
            Koreanquestions.add("무엇을 추천하시나요?");
            Koreanquestions.add("어떤 요리인가요?");

            AnswerCheck.add("I'd like to book a table for three at seven ");
            AnswerCheck.add("What do you recommend? ");
            AnswerCheck.add("What kind of dish is it? ");
        }
    }

    public String getEnglishSentence(String category, int index) {

        if (category.equals("Airport")) {
            return englishquestions.get(index);
        } else if (category.equals("Accomodation")) {
            return englishquestions.get(index);
        } else
            return englishquestions.get(index);
    }

    public String getKoreanSentence(String category, int index) {

        if (category.equals("Airport")) {
            return Koreanquestions.get(index);
        } else if (category.equals("Accomodation")) {
            return Koreanquestions.get(index);
        } else
            return Koreanquestions.get(index);
    }

    public String getAnserCheck(String category, int index) {

        if (category.equals("Airport")) {
            return AnswerCheck.get(index);
        } else if (category.equals("Accomodation")) {
            return AnswerCheck.get(index);
        } else
            return AnswerCheck.get(index);
    }
}
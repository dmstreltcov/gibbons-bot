import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TelegramBot bot = new TelegramBot("757914104:AAFHwpIBUNrTMkTmTshkPoYezitynvcVXKM");
        bot.setUpdatesListener(new UpdatesListener() {
            public int process(List<Update> list) {
                System.out.println(list.get(1));
                return UpdatesListener.CONFIRMED_UPDATES_ALL;
            }
        });
    }
}

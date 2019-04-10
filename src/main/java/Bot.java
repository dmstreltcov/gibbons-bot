import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Bot extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {

    }

    public String getBotUsername() {
        return "Gibbons";
    }

    public String getBotToken() {
        return "757914104:AAFHwpIBUNrTMkTmTshkPoYezitynvcVXKM";
    }
}

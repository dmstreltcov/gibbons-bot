import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
public class Bot extends TelegramLongPollingBot {
    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        switch (message){
            case "/menu":
                System.out.println("Menu");
                sendMessage(update.getMessage().getChatId().toString(),"You have choosed Menu");
                break;
            case "/start":
                System.out.println("Start");
                sendMessage(update.getMessage().getChatId().toString(),"Hello Beatch");
            default:
        }
    }
    public synchronized void sendMessage(String chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return System.getenv("bot_name");
    }
    public String getBotToken() {
        return System.getenv("bot_token");

    }
}

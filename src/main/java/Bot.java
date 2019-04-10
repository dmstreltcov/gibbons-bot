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
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    public String getBotUsername() {
        return "Gibbons";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    public String getBotToken() {
        return "757914104:AAFHwpIBUNrTMkTmTshkPoYezitynvcVXKM";
    }
}

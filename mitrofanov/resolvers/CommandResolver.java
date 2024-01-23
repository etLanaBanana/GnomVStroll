package mitrofanov.resolvers;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

public interface CommandResolver {

    public void resolveCommand(TelegramLongPollingBot tg_bot, String text, Long chat_id) throws SQLException, TelegramApiException;

    public String getCommandName();

}
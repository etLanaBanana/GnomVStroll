package mitrofanov.resolvers.impl;

import mitrofanov.keyboards.FermaKeyboard;
import mitrofanov.resolvers.CommandResolver;
import mitrofanov.service.FermaService;
import mitrofanov.service.RegistrationService;
import mitrofanov.session.State;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;

import static mitrofanov.resolvers.impl.StartNicknameResolver.setSessionStateForThisUser;

public class FermaResolver implements CommandResolver {
    private final String COMMAND_NAME = "/farm";




    public void FermaResolver() {

    }
    @Override
    public void resolveCommand(TelegramLongPollingBot tg_bot, String text, Long chatId) throws SQLException, TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        setSessionStateForThisUser(chatId, State.HOURS);
        sendMessage.setText("Выберите на сколько часов отправитесь на ферму");
        sendMessage.setReplyMarkup(FermaKeyboard.hoursKeyboard(tg_bot, chatId));
        sendMessage.setChatId(chatId);
        tg_bot.execute(sendMessage);

    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}

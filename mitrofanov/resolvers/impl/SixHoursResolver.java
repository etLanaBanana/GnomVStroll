package mitrofanov.resolvers.impl;

import mitrofanov.model.repository.FermaRepository;
import mitrofanov.resolvers.CommandResolver;
import mitrofanov.session.State;
import mitrofanov.session.StateButton;
import mitrofanov.utils.TelegramBotUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.SQLException;
import java.time.LocalDateTime;



public class SixHoursResolver implements CommandResolver {
    private final String COMMAND_NAME = "/sixHours";

    public SixHoursResolver() {
    }

    @Override
    public void resolveCommand(TelegramLongPollingBot tg_bot, String text, Long chatId) throws SQLException, TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Вы ушли на ферму на 6 часов");
        sendMessage.setChatId(chatId);
        tg_bot.execute(sendMessage);

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime hours = currentTime.plusHours(6);
        FermaRepository.updateUserTime(chatId.toString(), hours);

    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}

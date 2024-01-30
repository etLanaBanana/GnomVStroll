package mitrofanov.resolvers.impl;


import lombok.SneakyThrows;
import mitrofanov.keyboards.FermaKeyboard;
import mitrofanov.model.repository.FermaRepository;
import mitrofanov.resolvers.CommandResolver;
import mitrofanov.service.FermaService;
import mitrofanov.session.State;
import mitrofanov.utils.TelegramBotUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalDateTime;

import static mitrofanov.resolvers.impl.StartNicknameResolver.setSessionStateForThisUser;

public class FermaResolver implements CommandResolver {
    private final String COMMAND_NAME = "/farm";
    FermaService fermaService = new FermaService();

    @SneakyThrows
    @Override
    public void resolveCommand(TelegramLongPollingBot tg_bot, String text, Long chatId)  {
        SendMessage sendMessage = new SendMessage();
        if (text.startsWith("/farm")) {
            if (!fermaService.isRunOutTimeOfUser(chatId)) {

                TelegramBotUtils.sendMessage(tg_bot, "Вы уже находитесь на ферме. \nПовторное посещение будет доступно после окончания времени.\n" + fermaService.getRemainingTime(chatId), chatId);
                setSessionStateForThisUser(chatId, State.IDLE);
                return;
            }

            sendMessage.setText("Выберите на сколько часов отправитесь на ферму");
            sendMessage.setReplyMarkup(FermaKeyboard.hoursKeyboard(tg_bot, chatId));
            sendMessage.setChatId(chatId);
            tg_bot.execute(sendMessage);

        } if (text.startsWith("/threeHours")) {
            if (!fermaService.isRunOutTimeOfUser(chatId)) {

                setSessionStateForThisUser(chatId, State.IDLE);
                return;
            }
            sendMessage.setText("Вы ушли на ферму на 3 часа.\nВам будет начислено 30 золота");
            sendMessage.setChatId(chatId);
            tg_bot.execute(sendMessage);

            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime hours = currentTime.plusHours(1);

            fermaService.updateUserDateLastFarm(chatId, hours);
            fermaService.addGoldForUserByFarm(chatId, 30L);
            fermaService.updateFarmHours(chatId, 3);

            setSessionStateForThisUser(chatId, State.IDLE);
        }

         else if (text.startsWith("/sixHours")) {
            if (!fermaService.isRunOutTimeOfUser(chatId)) {

                setSessionStateForThisUser(chatId, State.IDLE);
                return;
            }

                sendMessage.setText("Вы ушли на ферму на 6 часов.\nВам будет начислено 60 золота");
                sendMessage.setChatId(chatId);
                tg_bot.execute(sendMessage);

                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime hours = currentTime.plusHours(6);

                fermaService.updateUserDateLastFarm(chatId, hours);
                fermaService.addGoldForUserByFarm(chatId, 60L);
                fermaService.updateFarmHours(chatId, 6);

                setSessionStateForThisUser(chatId, State.IDLE);

        } else if (text.startsWith("/twelveHours")) {
            if (!fermaService.isRunOutTimeOfUser(chatId)) {

                setSessionStateForThisUser(chatId, State.IDLE);
                return;
            }

                sendMessage.setText("Вы ушли на ферму на 12 часов.\nВам будет начислено 120 золота");
                sendMessage.setChatId(chatId);
                tg_bot.execute(sendMessage);

                LocalDateTime currentTime = LocalDateTime.now();
                LocalDateTime hours = currentTime.plusHours(12);

                fermaService.updateUserDateLastFarm(chatId, hours);
                fermaService.addGoldForUserByFarm(chatId, 120L);
                fermaService.updateFarmHours(chatId, 12);

                setSessionStateForThisUser(chatId, State.IDLE);


        } else if (text.startsWith("/leaveFarm")) {
            TelegramBotUtils.sendMessage(tg_bot,"Вы ушли с фермы", chatId);

            setSessionStateForThisUser(chatId, State.IDLE);
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}
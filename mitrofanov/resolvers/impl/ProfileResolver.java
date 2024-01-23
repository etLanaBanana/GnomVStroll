package mitrofanov.resolvers.impl;

import mitrofanov.resolvers.CommandResolver;
import mitrofanov.service.ProfileService;
import mitrofanov.service.RegistrationService;
import mitrofanov.session.State;
import mitrofanov.utils.TelegramBotUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.sql.SQLException;

import static mitrofanov.resolvers.impl.StartNicknameResolver.setSessionStateForThisUser;


public class ProfileResolver implements CommandResolver {
    private final String COMMAND_NAME = "/profile";
    RegistrationService registrationService;
    ProfileService profileService;

    public void ProfileService() {
        this.registrationService = new RegistrationService();
        this.profileService = new ProfileService();
    }


    @Override
    public void resolveCommand(TelegramLongPollingBot tg_bot, String text, Long chatId) throws SQLException, TelegramApiException {
        registrationService.hasChatId(chatId);
        setSessionStateForThisUser(chatId, State.IDLE);
        String userProfile = profileService.generateUserProfile(chatId);
        TelegramBotUtils.sendMessage(tg_bot, userProfile, chatId);
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }
}

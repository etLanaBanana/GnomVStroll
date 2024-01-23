package mitrofanov.service;

import mitrofanov.model.entity.User;
import mitrofanov.model.repository.FermaRepository;

import java.time.LocalDateTime;

public class FermaService {
    public FermaService() {
        FermaRepository fermaRepository = new FermaRepository();
    }
    public void isRunOutTimeOfUser(String chatId) {
        LocalDateTime userTime = FermaRepository.getThisUserTime(chatId); //время из табл + сколько-то часов
        if (LocalDateTime.now().isBefore(userTime)) {
            //время еще не кончилось делать ничего нельзя
        } else if (LocalDateTime.now().isAfter(userTime)) {
            //можно выполнять дальнейшие действия
        }
    }
}

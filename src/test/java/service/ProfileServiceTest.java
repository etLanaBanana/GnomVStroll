package service;

import mitrofanov.model.entity.User;
import mitrofanov.model.repository.ProfileRepository;
import mitrofanov.service.ProfileService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProfileServiceTest {

    @Test
    void generateUserProfile() throws SQLException {
        // Setup
        Long chatId = 123456789L;
        User user = User.builder()
                .nickname("TestUser")
                .race("Gnom")
                .gold(100L)
                .power(5)
                .agility(5)
                .mastery(5)
                .weight(5)
                .fightingPower(33L)
                .build();

        ProfileRepository mockProfileRepository = mock(ProfileRepository.class);
        when(mockProfileRepository.getUserProfile(chatId)).thenReturn(user);

        ProfileService profileService = new ProfileService();
        profileService.profileRepository = mockProfileRepository;

        String userProfile = profileService.generateUserProfile(chatId);

        String expectedProfile =
                "------------------------------\n" +
                        "| Профиль персонажа:\n" +
                        "------------------------------\n" +
                        "| Никнейм: TestUser\n" +
                        "| Раса: Gnom\n" +
                        "| 💰: 100\n" +
                        "| Сила: 5\n" +
                        "| Ловкость: 5\n" +
                        "| Мастерство: 5\n" +
                        "| Вес: 5\n" +
                        "| Боевая сила: 33\n" +
                        "------------------------------\n";

        assertEquals(expectedProfile, userProfile);
        verify(mockProfileRepository, times(1)).getUserProfile(chatId);
    }
}
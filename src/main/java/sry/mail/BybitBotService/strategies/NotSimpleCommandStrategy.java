package sry.mail.BybitBotService.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.NoCommandAnswer;
import sry.mail.BybitBotService.client.UserFeignClient;
import sry.mail.BybitBotService.dto.ChangeUserSettingsDto;
import sry.mail.BybitBotService.dto.CreateUserRequestDto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NotSimpleCommandStrategy extends NoCommandAnswer {

    private static final String ERROR_MESSAGE = "Произошла ошибка";

    private final UserFeignClient userFeignClient;

    @Override
    public String answer(Message message) {
        var messageFromUser = message.getText();
        var chatId = message.getChatId();

        if (messageFromUser.startsWith("/register")) {
            return registerUser(messageFromUser, chatId);
        } else if (messageFromUser.startsWith("/change_settings")) {
            return changeUserSettings(messageFromUser, chatId);
        } else {
            return "Неизвестная команда";
        }
    }

    private String registerUser(String message, Long chatId) {
        try {
            var apiKey = message.split(" ")[1];

            var request = CreateUserRequestDto.builder()
                    .apiKey(apiKey)
                    .tgId(chatId.toString())
                    .build();
            return userFeignClient.createUser(request);
        } catch (Exception ex) {
            return ERROR_MESSAGE;
        }
    }

    public String changeUserSettings(String message, Long chatId) {
        try {
            var settingsMap = Arrays.stream(message.split(" ")[1].split("&"))
                    .map(str -> str.split("="))
                    .collect(Collectors.toMap(setting -> setting[0], setting -> setting[1]));

            var requestDto = ChangeUserSettingsDto.builder()
                    .tgId(chatId.toString())
                    .longPercent(settingsMap.containsKey("lp") ? new BigDecimal(settingsMap.get("lp")) : null)
                    .shortPercent(settingsMap.containsKey("sp") ? new BigDecimal(settingsMap.get("sp")) : null)
                    .dumpPercent(settingsMap.containsKey("dp") ? new BigDecimal(settingsMap.get("dp")) : null)
                    .longMinutes(settingsMap.containsKey("lm") ? Integer.parseInt(settingsMap.get("lm")) : null)
                    .shortMinutes(settingsMap.containsKey("sm") ? Integer.parseInt(settingsMap.get("sm")) : null)
                    .dumpMinutes(settingsMap.containsKey("dm") ? Integer.parseInt(settingsMap.get("dm")) : null)
                    .build();
            return userFeignClient.changeUserSettings(requestDto);
        } catch (Exception ex) {
            return ERROR_MESSAGE;
        }
    }
}

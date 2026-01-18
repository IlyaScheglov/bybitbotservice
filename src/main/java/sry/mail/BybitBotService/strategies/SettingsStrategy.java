package sry.mail.BybitBotService.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.AnswerStrategy;
import sry.mail.BybitBotService.client.UserFeignClient;

@Component
@RequiredArgsConstructor
public class SettingsStrategy implements AnswerStrategy {

    private final UserFeignClient userFeignClient;

    @Override
    public String command() {
        return "/settings";
    }

    @Override
    public String answer(Message message) {
        return userFeignClient.getUserSettings(message.getChatId().toString());
    }
}

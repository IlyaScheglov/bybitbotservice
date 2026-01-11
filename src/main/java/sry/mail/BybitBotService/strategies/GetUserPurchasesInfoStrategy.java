package sry.mail.BybitBotService.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.AnswerStrategy;
import sry.mail.BybitBotService.client.PurchaseFeignClient;

@Component
@RequiredArgsConstructor
public class GetUserPurchasesInfoStrategy implements AnswerStrategy {

    private final PurchaseFeignClient purchaseFeignClient;

    @Override
    public String command() {
        return "/purchases";
    }

    @Override
    public String answer(Message message) {
        return purchaseFeignClient.getUserPurchasesInfo(message.getChatId().toString());
    }
}

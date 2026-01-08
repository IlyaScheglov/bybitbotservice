package sry.mail.BybitBotService.strategies;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.AnswerStrategy;

@Component
public class StartMessageStrategy implements AnswerStrategy {

    @Override
    public String command() {
        return "/start";
    }

    @Override
    public String answer(Message message) {
        return "Привет, я бот, который помогает инвестировать благодаря отслеживанию пампов, чтобы узнать про меня подробнее, введи команду /help";
    }
}

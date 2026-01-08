package sry.mail.BybitBotService.strategies;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.AnswerStrategy;

@Component
public class HelpMessageStrategy implements AnswerStrategy {

    @Override
    public String command() {
        return "/help";
    }

    @Override
    public String answer(Message message) {
        return """
                Список команд:
                /start - Приветствие
                /help - Информация о работе бота
                /register {apiKey}/{minPercentOfPump} - Зарегистрировать пользователя, За место {apiKey} подставить ключ который вам дал владелец бота, заместо {minPercentOfPump} поставить минимальный процент роста за 5 минут при котором я буду слать уведомления
                /active - Активировать поиск (Должен быть включен для отслеживания как пампов, отслеживание идеального времени продажи происходит даже в выключенном состоянии)
                /inactive - Деактивировать поиск (При регистрации он по дефолту inactive)
                /buy {spot} - Обозначить боту что ты купил спот, заместо {spot} проставить символ, например BTCUSDT
                /sell {spot} - Обозначить боту что ты продал спот, аналогично проставить символ вместо {spot} по примеру BTCUSDT
                
                Я буду присылать сообщения с символом спота и ссылкой на него, обозначая что его пора покупать или продавать
                """;
    }
}

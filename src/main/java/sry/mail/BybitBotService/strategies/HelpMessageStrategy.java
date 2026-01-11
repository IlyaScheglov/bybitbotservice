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
                
                /register {apiKey} - Зарегистрировать пользователя, За место {apiKey} подставить ключ который вам дал владелец бота
                
                /change_settings {minPercentOfDump}/{minPercentOfIncome} - Изменить настрйки пользователя, minPercentOfDump - процент дампа при котором можно покупать, minPercentOfIncome - минимальный процент дохода при котором стоит продавать, если не хочеться менять, то вместо значения поставить ? Пример изменения 10/5.18, Пример без изменения одного значения ?/5.18
                
                /purchases - Узнать информацию о купленных активах
                
                /active - Активировать поиск (Должен быть включен для отслеживания как пампов, отслеживание идеального времени продажи происходит даже в выключенном состоянии)
                
                /inactive - Деактивировать поиск (При регистрации он по дефолту inactive)
                
                /buy {spot} - Обозначить боту что ты купил спот, заместо {spot} проставить символ, например BTCUSDT
                
                /sell {spot} - Обозначить боту что ты продал спот, аналогично проставить символ вместо {spot} по примеру BTCUSDT
                
                Я буду присылать сообщения с символом спота и ссылкой на него, обозначая что его пора покупать или продавать
                """;
    }
}

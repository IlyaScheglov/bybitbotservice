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
                
                /active - Активировать поиск (Должен быть включен для отслеживания как пампов, отслеживание идеального времени продажи происходит даже в выключенном состоянии)
                
                /inactive - Деактивировать поиск (При регистрации он по дефолту inactive)
                
                /settings - Узнать свои текущие настройки
                
                /change_settings - изменить настройки Пример: /change_settings lp=10&lm=10&sp=10&sm=10&dp=10&dm=10 l - Лонговые настройки, s - шортовые настройки, d - Дамповые настройки, p - проценты, m - минуты, если что-то менять не надо просто не указывайте, порядок не важен
                
                Я буду присылать сообщения с символом фьючерса и ссылкой на него, обозначая что с ним происходит
                """;
    }
}

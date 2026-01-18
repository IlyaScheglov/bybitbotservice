package sry.mail.BybitBotService.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.ilyxxxa.server.telegrambotstarter.components.TelegramBotExecutor;
import sry.mail.BybitBotService.kafka.dto.UserNotificationEventDto;
import sry.mail.BybitBotService.util.GetBybitUrlBySymbolUtils;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserNotificationEventConsumer {

    private static final String LONG_MASSAGE_FORMAT = "Привет, лонг фьючерса %s, вот ссылка %s";
    private static final String SHORT_MASSAGE_FORMAT = "Привет, шорт фьючерса %s, вот ссылка %s";
    private static final String DUMP_MASSAGE_FORMAT = "Привет, дамп фьючерса %s, вот ссылка %s";

    private final ObjectMapper objectMapper;
    private final TelegramBotExecutor telegramBotExecutor;

    @KafkaListener(topics = UserNotificationEventDto.TOPIC_NAME, concurrency = "4")
    public void processUserNotificationEvent(String message) {
        try {
            var payload = objectMapper.readValue(message, UserNotificationEventDto.class);
            log.info("Receive user notification event with payload {}", payload);

            var symbol = payload.getSymbol();
            var userMessage = "";
            switch (payload.getType()) {
                case LONG -> userMessage = String.format(LONG_MASSAGE_FORMAT, symbol,
                        GetBybitUrlBySymbolUtils.getBybitLinearFuturesUrlBySymbol(symbol));
                case SHORT -> userMessage = String.format(SHORT_MASSAGE_FORMAT, symbol,
                        GetBybitUrlBySymbolUtils.getBybitLinearFuturesUrlBySymbol(symbol));
                case DUMP -> userMessage = String.format(DUMP_MASSAGE_FORMAT, symbol,
                        GetBybitUrlBySymbolUtils.getBybitLinearFuturesUrlBySymbol(symbol));
            }

            var sendMessage = SendMessage.builder()
                    .chatId(Long.valueOf(payload.getTgId()))
                    .text(userMessage)
                    .build();
            telegramBotExecutor.executeSendMessage(sendMessage);
            log.info("Successfully sent notification to user");
        } catch (Exception ex) {
            log.error("Error process user notification message", ex);
        }
    }
}

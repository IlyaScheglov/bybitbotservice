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

    private static final String BUY_MESSAGE_TEMPLATE = "Привет, начался дамп спота %s, пора покупать, вот ссылка %s";
    private static final String SELL_MESSAGE_TEMPLATE = "Привет, пора продавать спот %s вот ссылка %s";

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
                case BUY -> userMessage = String.format(BUY_MESSAGE_TEMPLATE, symbol,
                        GetBybitUrlBySymbolUtils.getBybitSpotUrlBySymbol(symbol));
                case SELL -> userMessage = String.format(SELL_MESSAGE_TEMPLATE, symbol,
                        GetBybitUrlBySymbolUtils.getBybitSpotUrlBySymbol(symbol));
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

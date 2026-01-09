package sry.mail.BybitBotService.strategies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.ilyxxxa.server.telegrambotstarter.strategy.NoCommandAnswer;
import sry.mail.BybitBotService.client.PurchaseFeignClient;
import sry.mail.BybitBotService.client.UserFeignClient;
import sry.mail.BybitBotService.dto.ChangeUserSettingsDto;
import sry.mail.BybitBotService.dto.CreateUserRequestDto;
import sry.mail.BybitBotService.dto.PurchaseRequestDto;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class NotSimpleCommandStrategy extends NoCommandAnswer {

    private static final String ERROR_MESSAGE = "Произошла ошибка";

    private final UserFeignClient userFeignClient;
    private final PurchaseFeignClient purchaseFeignClient;

    @Override
    public String answer(Message message) {
        var messageFromUser = message.getText();
        var chatId = message.getChatId();

        if (messageFromUser.startsWith("/register")) {
            return registerUser(messageFromUser, chatId);
        } else if (messageFromUser.startsWith("/change-setting")) {
            return changeUserSettings(messageFromUser, chatId);
        } else if (messageFromUser.startsWith("/buy")) {
            return buySpot(messageFromUser, chatId);
        } else if (messageFromUser.startsWith("/sell")) {
            return sellSpot(messageFromUser, chatId);
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
            var changeSetting = message.split(" ")[1].split("/");
            var minPercentOfDump = new BigDecimal(changeSetting[0]);
            var minPercentOfIncome = new BigDecimal(changeSetting[1]);

            var requestDto = ChangeUserSettingsDto.builder()
                    .tgId(chatId.toString())
                    .minPercentOfDump(minPercentOfDump)
                    .minPercentOfIncome(minPercentOfIncome)
                    .build();
            return userFeignClient.changeUserSettings(requestDto);
        } catch (Exception ex) {
            return ERROR_MESSAGE;
        }
    }

    private String buySpot(String message, Long chatId) {
        try {
            return purchaseFeignClient.createPurchase(createPurchaseRequestDto(message, chatId));
        } catch (Exception ex) {
            return ERROR_MESSAGE;
        }
    }

    private String sellSpot(String message, Long chatId) {
        try {
            return purchaseFeignClient.deletePurchase(createPurchaseRequestDto(message, chatId));
        } catch (Exception ex) {
            return ERROR_MESSAGE;
        }
    }

    private PurchaseRequestDto createPurchaseRequestDto(String message, Long chatId) {
        var symbol = message.split(" ")[1];
        return PurchaseRequestDto.builder()
                .symbol(symbol)
                .tgId(chatId.toString())
                .build();
    }
}

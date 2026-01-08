package sry.mail.BybitBotService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sry.mail.BybitBotService.dto.PurchaseRequestDto;

@FeignClient(
        url = "${bybit-calculator.url}",
        name = "purchase-client",
        contextId = "purchase-client",
        path = "/api/v1/purchase"
)
public interface PurchaseFeignClient {

    @PostMapping
    String createPurchase(@RequestBody PurchaseRequestDto requestDto);

    @DeleteMapping
    String deletePurchase(@RequestBody PurchaseRequestDto requestDto);
}

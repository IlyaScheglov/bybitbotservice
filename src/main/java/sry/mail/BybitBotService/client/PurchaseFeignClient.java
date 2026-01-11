package sry.mail.BybitBotService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import sry.mail.BybitBotService.dto.PurchaseRequestDto;

@FeignClient(
        url = "${bybit-calculator.url}",
        name = "purchase-client",
        contextId = "purchase-client",
        path = "/api/v1/purchase"
)
public interface PurchaseFeignClient {

    @GetMapping
    String getUserPurchasesInfo(@RequestParam("tgId") String tgId);

    @PostMapping
    String createPurchase(@RequestBody PurchaseRequestDto requestDto);

    @DeleteMapping
    String deletePurchase(@RequestBody PurchaseRequestDto requestDto);
}

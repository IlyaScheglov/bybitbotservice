package sry.mail.BybitBotService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import sry.mail.BybitBotService.dto.CreateUserRequestDto;

@FeignClient(
        url = "${bybit-calculator.url}",
        name = "user-client",
        contextId = "user-client",
        path = "/api/v1/user"
)
public interface UserFeignClient {

    @PostMapping
    String createUser(@RequestBody CreateUserRequestDto requestDto);

    @PatchMapping("/make-active")
    String makeUserActive(@RequestParam("tgId") String tgId);

    @PatchMapping("/make-inactive")
    String makeUserInactive(@RequestParam("tgId") String tgId);
}

package sry.mail.BybitBotService.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Value
@Builder
@Jacksonized
public class ChangeUserSettingsDto {

    String tgId;
    BigDecimal minPercentOfDump;
    BigDecimal minPercentOfIncome;
}

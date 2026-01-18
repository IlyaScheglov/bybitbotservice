package sry.mail.BybitBotService.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GetBybitUrlBySymbolUtils {

    private static final String BYBIT_LINEAR_FUTURES_URL_FORMAT = "https://www.bybit.com/trade/usdt/%s";

    public static String getBybitLinearFuturesUrlBySymbol(String symbol) {
        return String.format(BYBIT_LINEAR_FUTURES_URL_FORMAT, symbol);
    }
}

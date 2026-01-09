package sry.mail.BybitBotService.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GetBybitUrlBySymbolUtils {

    private static final String BYBIT_SPOT_URL_FORMAT = "https://www.bybit.com/ru-RU/trade/spot/%s/%s";

    public static String getBybitSpotUrlBySymbol(String symbol) {
        if (symbol.startsWith("USD")) {
            return String.format(BYBIT_SPOT_URL_FORMAT, symbol.substring(0, 3), symbol.substring(4));
        } else if (symbol.contains("USD")) {
            return String.format(BYBIT_SPOT_URL_FORMAT, symbol.substring(0, symbol.length() - 4), symbol.substring(symbol.length() - 4));
        } else {
            return String.format(BYBIT_SPOT_URL_FORMAT, symbol.substring(0, symbol.length() - 3), symbol.substring(symbol.length() - 3));
        }
    }
}

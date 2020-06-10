package cz.interview.task.domains.enums;

public enum  CurrencyCode {
    CZK,
    USD,
    EUR,
    JPY;


    public static CurrencyCode convertToCategory(String currencyCode) {
        if (currencyCode != null) {
            String upperCaseWord = currencyCode.toUpperCase();
            for (CurrencyCode code : values()) {
                if (code.name().equals(upperCaseWord)) {
                    return code;
                }
            }

        }
        throw new RuntimeException("The currency code is not valid: " + currencyCode);
    }
}

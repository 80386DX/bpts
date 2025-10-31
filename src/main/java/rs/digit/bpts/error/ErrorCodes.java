package rs.digit.bpts.error;


//@AllArgsConstructor
//@Getter
public enum ErrorCodes {

    ERROR_001("404", "Not found"),
    ERROR_002("403", "Forbidden"),
    ERROR_003("400", "Insufficient Funds"),
    ;

    private final String errorCode;
    private final String errorText;

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorText() {
        return errorText;
    }

    ErrorCodes(String errorCode, String errorText) {
        this.errorCode = errorCode;
        this.errorText = errorText;
    }
}

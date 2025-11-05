package rs.digit.bpts.common;

public enum Status {

    PROCESSING("PROCESSING","In process"),
    REJECT("REJECT","Rejected"),
    SUCCESS("SUCCESS","Success");

    private final String code;
    private final String message;

    Status(String code,String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}

package hanteo.solution.question2.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    INVALID_SUM("합계는 1이상이어야 합니다."),
    INVALID_COINS("동전은 0보다 커야 합니다."),
    INVALID_MIN_COIN("최소 동전 값이 합계보다 큽니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}


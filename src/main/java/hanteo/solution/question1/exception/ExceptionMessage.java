package hanteo.solution.question1.exception;

import lombok.Getter;

@Getter
public enum ExceptionMessage {
    CATEGORY_NOT_FOUND("해당 카테고리가 존재하지 않습니다."),
    PARENT_CATEGORY_NOT_FOUND("부모 카테고리를 찾을 수 없습니다."),
    INVALID_CATEGORY_ID_FORMAT("ID는 숫자만 입력할 수 있습니다.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }
}


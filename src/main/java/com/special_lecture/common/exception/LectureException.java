package com.special_lecture.common.exception;

import com.special_lecture.common.type.GlobalResponseCode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LectureException extends RuntimeException {
    private GlobalResponseCode globalResponseCode;
    private String description;

    public LectureException(GlobalResponseCode globalResponseCode) {
        this.globalResponseCode = globalResponseCode;
        this.description = globalResponseCode.getDescription();
    }


    public LectureException(GlobalResponseCode globalResponseCode, String message) {
        super(message);
        this.globalResponseCode = globalResponseCode;
        this.description = message != null ? message : globalResponseCode.getDescription();
    }

    public LectureException(GlobalResponseCode globalResponseCode, String message, Throwable cause) {
        super(message, cause);
        this.globalResponseCode = globalResponseCode;
        this.description = message != null ? message : globalResponseCode.getDescription();
    }

    public LectureException(GlobalResponseCode globalResponseCode, Throwable cause) {
        super(cause);
        this.globalResponseCode = globalResponseCode;
        this.description = globalResponseCode.getDescription();
    }

    @Override
    public String getMessage() {
        return description != null ? description : super.getMessage();
    }



}
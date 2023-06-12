package com.coolway.common.enums;

public enum ExcelEnum {

    STUDENT_ENUM(
            new String[]{"name", "age"},
            new String[]{"姓名", "年龄"}
            );
    private String[] colEnglishName;
    private String[] colChineseName;

    ExcelEnum(String[] colEnglishName, String[] colChineseName) {
        this.colEnglishName = colEnglishName;
        this.colChineseName = colChineseName;
    }

    public String[] getColEnglishName() {
        return colEnglishName;
    }

    public String[] getColChineseName() {
        return colChineseName;
    }
}

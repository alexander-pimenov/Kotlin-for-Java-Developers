package _12enums;

import java.util.Objects;

//public enum ReportFileName {
//
//    //Отчеты
//    INFORMATION_ON_STATE_OF_INFORMATION_EXCHANGE(
//            1, "Сведения о состоянии информационного взаимодействия"),
//    INFORMATION_ABOUT_USERS_WHO_HAVE_ACCESS(
//            2, "Сведения о пользователях, имеющих доступ");
//
//    private final Integer typeNumber;
//    private final String value;
//
//    ReportFileName(int typeNumber, String value) {
//        this.typeNumber = typeNumber;
//        this.value = value;
//    }
//
//    public String getValue() {
//        return this.value;
//    }
//
//    public static ReportFileName fromTypeNumber(Integer typeNumber) {
//        for (ReportFileName type : ReportFileName.values()) {
//            if (Objects.equals(type.typeNumber, typeNumber)) {
//                return type;
//            }
//        }
//        return null;
//    }
//}

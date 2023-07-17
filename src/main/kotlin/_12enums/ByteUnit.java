package _12enums;

import java.text.DecimalFormat;

//для примера, он же есть на котлин

//public enum ByteUnit {
//    B(1),
//    KB(1024),
//    MB(KB.size * KB.size),
//    GB(MB.size * KB.size),
//    TB(GB.size * KB.size),
//    ;
//
//    private final long size;
//
//    ByteUnit(long size) {
//        this.size = size;
//    }
//
//    public Long fromBytes(Long value) {
//        if (value == null) {
//            return 0L;
//        }
//        return value / size;
//    }
//
//    public Long toBytes(Long value) {
//        if (value == null) {
//            return 0L;
//        }
//        return size * value;
//    }
//
//    public static String formatBytes(final long value) {
//        if (value < 0) {
//            throw new IllegalArgumentException("Invalid file size: " + value);
//        }
//        if (value == 0) { //если размер равен 0 B
//            return "0";
//        }
//        ByteUnit[] units = ByteUnit.values();
//        for (int i = units.length-1; i>=0; i--) {
//            ByteUnit unit = units[i];
//            if (value >= unit.size) {
//                final double result = unit.size > 1 ? (double) value / (double) unit.size : (double) value;
//                return new DecimalFormat("#,##0.##").format(result) + " " + unit.name();
//            }
//        }
//        return null;
//    }
//}

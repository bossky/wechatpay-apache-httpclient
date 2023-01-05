package com.wechat.pay.service.bill;

/**
 */
public class BillData {

    /**
     * 解义帐单字段
     *
     * @param org 原数据
     * @return 解义后数据
     */
    public static String unEscape(String org) {
        //        账单处理过程会对商品名称、商户数据包、设备号等商户自定义字段进行特殊字符转义，具体规则包括：
        //        '转换成为\'
        //        "转换成为\"
        //`转换成为\`
        //,转换成为\ （\+空格）
        //\r转换成为\\r
        //\t转换成为\\t
        StringBuilder stringBuilder = new StringBuilder();
        if (null == org) {
            return null;
        }
        int len = org.length();
        for (int i = 0; i < len; i++) {
            char ch = org.charAt(i);
            if (ch == '\\' && i + 1 != len) {
                char next = org.charAt(i + 1);
                if (next == '\'') {
                    stringBuilder.append('\'');
                    i++;
                    continue;
                } else if (next == '"') {
                    stringBuilder.append('"');
                    i++;
                    continue;
                } else if (next == '`') {
                    stringBuilder.append('`');
                    i++;
                    continue;
                } else if (next == ' ') {
                    stringBuilder.append(',');
                    i++;
                    continue;
                } else if (next == '\\') {
                    char last = org.charAt(i + 1);
                    if (i + 2 != len) {
                        if (last == 'r') {
                            stringBuilder.append('\r');
                            i += 2;
                            continue;
                        } else if (last == 't') {
                            stringBuilder.append('\t');
                            i += 2;
                            continue;
                        }
                    }
                }
            }
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}

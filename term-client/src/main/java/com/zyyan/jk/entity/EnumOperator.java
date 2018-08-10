/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zyyan.jk.entity;

/**
 * 定义运算符枚举
 *
 * @author win 10
 */
public enum EnumOperator {
    ADD("+", "加", 1),
    MINUS("-", "减", 1),
    MULTI("*", "乘", 2),
    DEVIDE("/", "除", 2),
    GRATER(">", "大于", 0),
    LESS("<", "小于", 0),
    EQUAL("=", "等于", 0),
    GRATER_EQUAL(">=", "大于等于", 0),
    LESS_EQUAL("<=", "小于等于", 0),
    PARENTHESES_LEFT("(", "左边括号", 3),
    PARENTHESES_RIGHT(")", "右边括号", 3);

    private String symbol;
    private String desc;
    private Integer priority;

    public static boolean isOperator(String s) {
        for (EnumOperator oper : EnumOperator.values()) {
            if (oper.getSymbol().equals(s)) {
                return true;
            }
        }
        return false;
    }

    private EnumOperator(String symbol, String desc, Integer priority) {
        this.symbol = symbol;
        this.desc = desc;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hongdian.jk;

import com.hongdian.jk.entity.EnumOperator;
import java.util.List;

/**
 *
 * @author win 10
 */
public class FormulaParser {

    /**
     * 将公式拆分成表达式
     *
     * @param formula
     * @return
     */
    public static String[] parseFormula(String formula) {
        formula = formula.trim().replace(" ", "");//去除空格
        String judgeSymbol = "";//先获取到判断符  > 、< 、 =
        if (formula.indexOf(EnumOperator.EQUAL.getSymbol()) > 0) {
            judgeSymbol = EnumOperator.EQUAL.getSymbol();
        }

        if (formula.indexOf(EnumOperator.GRATER.getSymbol()) > 0) {
            if ("".equals(judgeSymbol)) {
                return null;
            } else {
                judgeSymbol = EnumOperator.GRATER.getSymbol();
            }
        }

        if (formula.indexOf(EnumOperator.LESS.getSymbol()) > 0) {
            if (!"".equals(judgeSymbol)) {
                return null;
            } else {
                judgeSymbol = EnumOperator.LESS.getSymbol();
            }
        }
        if ("".equals(judgeSymbol)) {
            return null;
        } else {
            int index = formula.indexOf(judgeSymbol);
            return new String[]{ formula.substring(0, index), formula.substring(index)};
        }
    }

    /**
     * 解析表达式
     *
     * @param expression
     */
    public static double computeExpression(String expression) {
        int i = 0;
        while (i < expression.length()) {
            String str = String.valueOf(expression.charAt(i));
            if (EnumOperator.isOperator(str)) {
                if (EnumOperator.PARENTHESES_LEFT.getSymbol().equals(str)) {//如果为括号
                    String subExpress = includeParentHeses(expression);//获取到括号里面的内容
                }

                if (EnumOperator.ADD.getSymbol().equals(str)) {//如果是加号
                    String exp1 = expression.substring(0, i);
                }
            }
        }
        return 0;
    }

    public static String includeParentHeses(String expression) {
        int i = 0;
        int count = 0;//左右括号对应计数标识
        int start = 0;
        int end = 0;
        while (i < expression.length()) {
            String str = String.valueOf(expression.charAt(i));
            if (EnumOperator.PARENTHESES_LEFT.getSymbol().equals(str)) {//如果为左括号
                count++;
                start = i;
            }

            if (EnumOperator.PARENTHESES_RIGHT.getSymbol().equals(str)) {
                count--;
                end = i;
                if (count == 0) {
                    return expression.substring(start, end);
                }
            }
        }
        return expression.substring(start, end);
    }

    public Double getItemValue(String itemId) {
        return null;
    }
}

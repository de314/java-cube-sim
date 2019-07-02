package com.fart.cubes.sim;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum CubeMoves {
    RIGHT("R"),
    RIGHT_P("R'"),
    LEFT("L"),
    LEFT_P("L'"),
    UP("U"),
    UP_P("U'"),
    DOWN("D"),
    DOWN_P("D'"),
    FRONT("F"),
    FRONT_P("F'"),
    BACK("B"),
    BACK_P("B'");

    private static Pattern PARSE_PATTERN = Pattern.compile("([RLUDFB](?:2|')?)");

    public static CubeMoves[] parse(String s) {
        ArrayList<CubeMoves> list = Lists.newArrayList();
        Matcher m = PARSE_PATTERN.matcher(s.toUpperCase());
        while (m.find()) {
            String match = m.group();
            boolean isPrime = match.length() == 2 && match.charAt(1) == '\'';
            boolean isDouble = match.length() == 2 && match.charAt(1) == '2';
            CubeMoves move = null;
            switch (match.charAt(0)) {
                case 'R': move = isPrime ? RIGHT_P : RIGHT; break;
                case 'L': move = isPrime ? LEFT_P : LEFT; break;
                case 'U': move = isPrime ? UP_P : UP; break;
                case 'D': move = isPrime ? DOWN_P : DOWN; break;
                case 'F': move = isPrime ? FRONT_P : FRONT; break;
                case 'B': move = isPrime ? BACK_P : BACK; break;
            }
            if (move != null) {
                list.add(move);
                if (isDouble) {
                    list.add(move);
                }
            }
        }
        return list.toArray(new CubeMoves[list.size()]);
    }

    private final String displayValue;

    CubeMoves(String displayValue) {
        this.displayValue = displayValue;
    }
}

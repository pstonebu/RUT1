/*
 * Adapted from https://stackoverflow.com/questions/11383070/pretty-print-2d-array-in-java
 */
package com.stoneburner.rut1;

import java.io.PrintStream;

import static java.lang.Double.valueOf;
import static java.lang.String.format;

public final class Printer {

    private static final char BORDER_KNOT = '+';
    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\033[1;96m";
    private static final String ANSI_RED = "\033[1;91m";
    private static final String ANSI_GREEN = "\033[1;92m";
    private static final String ANSI_YELLOW = "\033[1;93m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private final PrintStream out;
    private final String asNull;

    public Printer() {
        this.out = System.out;
        this.asNull = "(NULL)";
    }

    public void print(String[][] table) {
        if ( table == null ) {
            throw new IllegalArgumentException("No tabular data provided");
        }
        if ( table.length == 0 ) {
            return;
        }
        final int[] widths = new int[getMaxColumns(table)];
        adjustColumnWidths(table, widths);
        printPreparedTable(table, widths, getHorizontalBorder(widths));
    }

    private void printPreparedTable(String[][] table, int widths[], String horizontalBorder) {
        final int lineLength = horizontalBorder.length();
        out.println(horizontalBorder);
        for ( final String[] row : table ) {
            if ( row != null ) {
                out.println(getRow(row, widths, lineLength));
                out.println(horizontalBorder);
            }
        }
    }

    private String getRow(String[] row, int[] widths, int lineLength) {
        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
        final int maxWidths = widths.length;
        for ( int i = 0; i < maxWidths; i++ ) {
            builder.append(padRight(getCellValue(safeGet(row, i, null)), widths[i])).append(VERTICAL_BORDER);
        }
        return builder.toString();
    }

    private String getHorizontalBorder(int[] widths) {
        final StringBuilder builder = new StringBuilder(256);
        builder.append(BORDER_KNOT);
        for ( final int w : widths ) {
            for ( int i = 0; i < w; i++ ) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        return builder.toString();
    }

    private int getMaxColumns(String[][] rows) {
        int max = 0;
        for ( final String[] row : rows ) {
            if ( row != null && row.length > max ) {
                max = row.length;
            }
        }
        return max;
    }

    private void adjustColumnWidths(String[][] rows, int[] widths) {
        for ( final String[] row : rows ) {
            if ( row != null ) {
                for ( int c = 0; c < widths.length; c++ ) {
                    final String cv = getCellValue(safeGet(row, c, asNull));
                    final int l = cv.length();
                    if ( widths[c] < l ) {
                        widths[c] = l;
                    }
                }
            }
        }
    }

    private static String padRight(String s, int n) {
        StringBuilder padded = new StringBuilder(format("%1$-" + n + "s", s));

        if (s.equals("X")) {
            padded.insert(0, ANSI_RED);
        } else if (s.contains("%")) {
            double percent = valueOf(s.substring(0, s.indexOf("%")));
            if (percent < 10.0) {
                padded.insert(0, ANSI_WHITE);
            } else if (percent < 20.0) {
                padded.insert(0, ANSI_CYAN);    
            } else if (percent < 35.0) {
                padded.insert(0, ANSI_YELLOW);
            } else {
                padded.insert(0, ANSI_GREEN);
            }
        } else {
            return padded.toString();
        }
        return padded.append(ANSI_RESET).toString();
    }

    private static String safeGet(String[] array, int index, String defaultValue) {
        return index < array.length ? array[index] : defaultValue;
    }

    private String getCellValue(Object value) {
        return value == null ? asNull : value.toString();
    }
}
package com.shankar.springstore.util;

import jakarta.servlet.http.HttpServletResponse;

//import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvUtils {
    public static void writeCsvResponse(HttpServletResponse response, String filename, List<String[]> rows) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try (PrintWriter pw = response.getWriter()) {
            for (String[] row : rows) {
                pw.println(String.join(",", escapeRow(row)));
            }
            pw.flush();
        }
    }

    private static String[] escapeRow(String[] row) {
        String[] out = new String[row.length];
        for (int i = 0; i < row.length; i++) {
            String cell = row[i] == null ? "" : row[i];
            // escape comma and quotes
            if (cell.contains(",") || cell.contains("\"") || cell.contains("\n")) {
                cell = cell.replace("\"", "\"\"");
                cell = "\"" + cell + "\"";
            }
            out[i] = cell;
        }
        return out;
    }
}

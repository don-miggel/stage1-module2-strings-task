package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringBuilder sb = new StringBuilder(source);

        ArrayList<String> parsedTokens = new ArrayList<>();

        for (String delim : delimiters){
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == delim.charAt(0))
                    sb.replace(i, i + 1, " ");
            }
        }
        StringTokenizer strt = new StringTokenizer(sb.toString());
        while (strt.hasMoreTokens()){
            parsedTokens.add(strt.nextToken().trim());
        }

        return parsedTokens;


    }

    public static void main(String[] args) {
        StringSplitter sp = new StringSplitter();
        System.out.println(sp.splitByDelimiters("qw3e1rt4yu2i3opa1sd1fg2hj4kl", List.of("1", "2", "3")));
    }
}

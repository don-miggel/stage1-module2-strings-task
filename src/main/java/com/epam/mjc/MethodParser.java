package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringBuilder sb = new StringBuilder(signatureString);
        int posOpenBracket = signatureString.indexOf('(')+1;
        int posClosingBracket = signatureString.indexOf(')');

        String extractedArgs = signatureString.substring(posOpenBracket,posClosingBracket);
        StringTokenizer argsToken = new StringTokenizer(extractedArgs, ",");
        System.out.println(extractedArgs);
        sb.delete(posOpenBracket-1, posClosingBracket+1);
        System.out.println(sb);
        StringTokenizer st = new StringTokenizer(sb.toString());
        String[] parsedMethodPArts = parseMethodSignature(st);
        System.out.println(Arrays.toString(parsedMethodPArts));
        int countMethodTokens = parsedMethodPArts.length;
        System.out.println(countMethodTokens);
        MethodSignature msgt;
        if (countMethodTokens > 2){
            if (argsToken.countTokens() > 0)
                msgt = new MethodSignature(parsedMethodPArts[2], parseArgs(argsToken));
            else
                msgt = new MethodSignature(parsedMethodPArts[2]);
            msgt.setAccessModifier(parsedMethodPArts[0]);
            msgt.setReturnType(parsedMethodPArts[1]);
        }else {
            if (argsToken.countTokens() > 0)
                msgt = new MethodSignature(parsedMethodPArts[1], parseArgs(argsToken));
            else
                msgt = new MethodSignature(parsedMethodPArts[1]);
            msgt.setReturnType(parsedMethodPArts[0]);
        }

        return msgt;
    }

    public static MethodSignature parseMthd1(String method){
        StringBuilder sb = new StringBuilder(method);
        int posOpenBracket = method.indexOf('(')+1;
        int posClosingBracket = method.indexOf(')');

        String extractedArgs = method.substring(posOpenBracket,posClosingBracket);
        StringTokenizer argsToken = new StringTokenizer(extractedArgs, ",");
        System.out.println(extractedArgs);
        sb.delete(posOpenBracket-1, posClosingBracket+1);
        System.out.println(sb);
        StringTokenizer st = new StringTokenizer(sb.toString());
        String[] parsedMethodPArts = parseMethodSignature(st);
        System.out.println(Arrays.toString(parsedMethodPArts));
        int countMethodTokens = parsedMethodPArts.length;
        System.out.println(countMethodTokens);
        MethodSignature msgt;
        if (countMethodTokens > 2){
            if (argsToken.countTokens() > 0)
                msgt = new MethodSignature(parsedMethodPArts[2], parseArgs(argsToken));
            else
                msgt = new MethodSignature(parsedMethodPArts[2]);
            msgt.setAccessModifier(parsedMethodPArts[0]);
            msgt.setReturnType(parsedMethodPArts[1]);
        }else {
            if (argsToken.countTokens() > 0)
                msgt = new MethodSignature(parsedMethodPArts[1], parseArgs(argsToken));
            else
                msgt = new MethodSignature(parsedMethodPArts[1]);
            msgt.setReturnType(parsedMethodPArts[0]);
        }

        return msgt;
        /*
        System.out.println(countTokens+"countToken");
        while (st.hasMoreTokens()) {
            // Print all tokens
            String parsed = st.nextToken();
            System.out.println(parsed.trim());
        }


        if (countTokens > 3){
            String[] methodParts = new String[countTokens];

            MethodSignature mp = new MethodSignature(methodParts[0], null);
        }

         */

    }
    /*
    private static MethodSignature.Argument parseArgs1(String args){
        StringTokenizer argsToken = new StringTokenizer(args);
        String[] parsed = new String[2];
        while (argsToken.hasMoreTokens()) {
            // Print all tokens
            String ar = argsToken.nextToken();
            System.out.println(parsed.trim());
        }

        return null;
    }

     */

    private static ArrayList<MethodSignature.Argument> parseArgs(StringTokenizer args) {
        ArrayList<MethodSignature.Argument> argsList = new ArrayList<>();

        while (args.hasMoreTokens()) {

            String tmp = args.nextToken();
            StringTokenizer singeArgPair = new StringTokenizer(tmp.trim());
            argsList.add(new MethodSignature.Argument(singeArgPair.nextToken(), singeArgPair.nextToken()));
        }

        return argsList;
    }

    private static String[] parseMethodSignature(StringTokenizer methodTokens){
        int countTokens = methodTokens.countTokens();
        String[] methodParts = new String[countTokens];
        int counter = 0;

        while (methodTokens.hasMoreTokens()) {

            String parsed = methodTokens.nextToken();
            methodParts[counter++] = parsed.trim();
        }

        return methodParts;
    }

        public static void main(String[] args) {
     //       MethodSignature ms = MethodParser.parseMthd1("accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)");
            MethodSignature ms1 = MethodParser.parseMthd1("private void log(String value)");
            MethodSignature ms = MethodParser.parseMthd1("public DateTime getCurrentDateTime()");
            System.out.println(ms.getAccessModifier());
            System.out.println(ms.getMethodName());
            System.out.println(ms.getArguments());
            System.out.println(ms.getReturnType());
    }
}

package com.jary.interview.leetcode;

import javafx.util.Pair;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Solution_2 {

    public int numUniqueEmails(String[] emails) {


        if (emails == null || emails.length == 0) return 0;

        String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

        Set<String> set = new HashSet<>();
        String emailName,emailDomain;
        for (String email : emails){
            if (Pattern.matches(REGEX_EMAIL,email)) throw new IllegalArgumentException("error email!");
            String[] emailSpilt = email.split("@");
            emailName = emailSpilt[0].split("\\+")[0].replaceAll("\\.","");
            emailDomain = emailSpilt[1];
            set.add(emailName + "@" + emailDomain);
        }

        return set.size();
    }

    public static void main(String[] args){
        Solution_2 solution = new Solution_2();
        String[] emails = {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"};
        System.out.println("num = " + solution.numUniqueEmails(emails));
    }

}

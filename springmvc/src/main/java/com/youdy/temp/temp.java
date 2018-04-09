package com.youdy.temp;

public class temp {

    public static void main(String[] args) {
        // (201410, 201503) 与 201412, 201506
        int[] params = {201410, 201503, 201412, 201506};
        int p0 = params[0];
        int p1 = params[1];
        int p2 = params[2];
        int p3 = params[3];

        int diff1 = diff(p0, p1);
        int diff2 = diff(p2, p3);

        int dupCnt = 0;

        for (int i = 1; i <= diff1; i++) {
            int month1 = p0 + i;
            for (int j = 1; j <= diff2; j++) {
                int month2 = p2 + j;
                if (month1 == month2) {
                    dupCnt ++;
                }
            }
        }
        System.out.println(dupCnt);
    }

    static int diff(int p, int q) {
        String ps = String.valueOf(p);
        String qs = String.valueOf(q);
        int psYear = Integer.parseInt(ps.substring(0, 4));
        int qsYear = Integer.parseInt(qs.substring(0, 4));
        int psMonth = Integer.parseInt(ps.substring(4, ps.length()));
        int qsMonth = Integer.parseInt(qs.substring(4, qs.length()));

        int monthDiff = (qsYear - psYear) * 12 - Math.abs(psMonth - qsMonth) + 1;
        return monthDiff;
    }

    static int dupCnt(int p, int q, int periodP, int periodQ) {
        int dupCnt = 0;

        for (int i = 1; i <= periodP; i++) {
            for (int j = 1; j <= periodQ; j++) {

            }
        }

        return 0;
    }
}

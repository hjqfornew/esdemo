package com.hjq.algorithm.manacher;

/**
 * 最长回文问题
 *  @author: hjq
 *  @date: 2025/4/21
 */
public class ManacherBase {
    //https://leetcode.com/problems/longest-palindromic-substring/
    public static String longestPalindrome(String s) {
        char[] sArr =  getPreStr(s);
        //prr 为回文半径数组
        int[] prr = new int[sArr.length];
        int C = 0;///回文中心
        int R = 0;//回文到达的最右侧
        int i = 1;
        prr[0] = 1;
        while(i<prr.length){
            int num = 1;
            if(i>=R){// i比最右侧的R还要靠右，处理最新进数据
                int[] rc = getRC(sArr,i,num);
                if(rc[0]>R){
                    R = rc[0];
                    C = R - rc[1] + 1;
                }
                num = rc[1];

            }else{
                //找出i对称点 I
                int I = C - (i-C);
                int Cleft = C - prr[C];//此时最右侧对应左侧的回文点
                int Ileft = I - prr[I];//对称点的左侧回文点
                if(Ileft>Cleft){
                    num = prr[I];
                }else{
                    int notCompareNum = R - i + 1;
                    int[] rc = getRC(sArr, i, notCompareNum);
                    if(rc[0]>R){
                        R = rc[0];
                        C = R - rc[1] + 1;
                    }
                    num = rc[1];
                }
            }
            prr[i] = num;
            i++;
        }

        int maxValue = Integer.MIN_VALUE;
        int index  = 0;
        for (int i1 = 0;i1<prr.length;i1++) {
            if(prr[i1]>maxValue){
                index = i1;
                maxValue = prr[i1];
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i2 = index-maxValue + 1;i2< index +maxValue - 1;i2++){
            if(i2%2!=0){
                result.append(sArr[i2]);
            }
        }
        return result.toString();
    }

    private static int[] getRC(char[] prr, int i,int num) {
        int left = i - num;int right = i + num;
        while (left>=0 && right <prr.length){
            if(prr[left]==prr[right]){
                num++;
                left--;
                right++;
            }else {
                break;
            }
        }
        return new int[]{right-1,num};

    }

    private static char[] getPreStr(String s) {
        char[] chars = s.toCharArray();
        char[] result = new char[chars.length*2+1];
        for (int i = 0; i<chars.length;i++) {
            result[i*2] = '#';
            result[i*2+1] = chars[i];
        }
        result[chars.length*2] = '#';
        return result;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }
}

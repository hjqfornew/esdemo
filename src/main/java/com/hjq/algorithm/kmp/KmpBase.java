package com.hjq.algorithm.kmp;
/**
 * 查询子串问题
 *  @author: hjq
 *  @date: 2025/4/21
 */
public class KmpBase {
    // https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
    /**
     * 返回字符串 haystack 中子串 needle 中第一个位置，无返回-1
     * @param
     * @return
     * @author hjq
     * @date 2025/4/17
     */
    public static int strStr(String haystack, String needle) {
        if(haystack.length()<needle.length()){
            return -1;
        }
        char[] haystackArr = haystack.toCharArray();
        char[] needleArr = needle.toCharArray();
        int[] nextArr = findNext(needle);
        int x = 0;
        int y = 0;
        while (x<haystack.length() && y<needle.length()){
            if(haystackArr[x]==needleArr[y]){
                x++;
                y++;
            }else if(y==0){
                x++;
            }else{
                y=nextArr[y];
            }
        }
        return y==needleArr.length?x-y:-1;
    }

    /**
     * 返回每个位置i上，0到 i-1 前缀 和 后缀 最长相等的长度
     * 如 abcfabcg ,g这个位置 的值为3
     * @param
     * @return
     * @author hjq
     * @date 2025/4/17
     */
    private static int[] findNext(String needle) {
        int[] next = new int[needle.length()];
        next[0] = -1;
        if(needle.length()==1){
            return next;
        }
        next[1] = 0;
        int ch = 0;//此时比较的下标
        int i = 2;
        char[] chars = needle.toCharArray();
        while (i<needle.length()){
            if(chars[i-1] == chars[ch]){
                next[i] = ch +1;
                ch = next[i];
                i++;
            }else if(ch==0){
                next[i++] = 0;
            }else{
                ch = next[ch];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int i = strStr("mississippi", "issip");
    }
}

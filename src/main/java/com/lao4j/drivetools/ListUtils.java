package com.lao4j.drivetools;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
  * @description 集合操作类
  * @author yongxuan.chen
  * @date 2021/4/21 15:45
  */
public class ListUtils {

    /**
     * 拆分集合
     * @date 2020/5/11 10:37
     * @param resList 要拆分的集合
     * @param count   每个集合的元素个数
     */
    public static <T> List<List<T>> split(List<T> resList, int count) {

        List<List<T>> ret = new ArrayList<List<T>>();
        if (resList == null || resList.size() == 0 || count < 1) {
            return ret;
        }
        int size = resList.size();
        if (size <= count) { //数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            //前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            //last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;

    }

    /**
      * 将字符串数组转换成整型数组
      * @date 2020/5/11 10:37
      * @param resList
      */
    public static List<Integer> string2Int(List<String> resList) {
        List<Integer> result = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(resList)){
            for (String v : resList) {
                result.add(Integer.valueOf(v));
            }
        }
        return result;
    }

    /**
     * 集合去重
     * @date 2020/5/11 10:37
     * @param list
     */
    public static List<String> duplicatRemoval(List<String> list) {
        Set set = new HashSet(list);
        return new ArrayList(set);
    }

    /**
     * 给集合的所有元素增加前缀和后缀
     * @date 2020/5/11 10:37
     * @param list
     */
    public static List<String> surroundSymbol(List<String> list, String symbol) {
        List<String> result = new ArrayList<>();
        for(String s : list) {
            result.add(symbol + s + symbol);
        }
        return result;
    }

    /**
     * 根据分隔符生成list数组
     * @date 2020/5/11 10:37
     * @param s
     * @param sp
     */
    public static List<String> split(String s, String sp){
        if(StringUtils.isBlank(s)){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        if(StringUtils.isBlank(sp)) {
            result.add(s);
            return result;
        }
        String[] split = s.split(sp);
        List<String> temp = Arrays.asList(split);
        result.addAll(temp);
        return result;
    }

    /**
     * 判断是否包含
     * @date 2020/5/11 10:37
     * @param list
     * @param s
     */
    public static boolean contains(List<String> list, String s){
        if(CollectionUtils.isEmpty(list) || null == s){
            return false;
        }
        for (String ls : list){
            if(ls.equals(s)){
                return true;
            }
        }
        return false;
    }

    /**
     * 按字母和匹配度排序，匹配度优选,字母不区分大小写
     * @date 2020/5/11 10:37
     * @param list
     * @param keyword
     */
    public static List<String> sortByMatchingDegree(List<String> list,String keyword)
    {
        if(CollectionUtils.isEmpty(list)) {
            return list;
        }
        List<String> newlist=new ArrayList<>();
        for (String string :list) {
            if (string.contains(keyword)) {
                newlist.add(string);
            }
        }
        Collections.sort(newlist, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()<o2.length()) {
                    return -1;
                }
                else if (o1.length()>o2.length()) {
                    return 1;
                }
                return o1.compareToIgnoreCase(o2);
            }
        });
        return newlist;
    }

}

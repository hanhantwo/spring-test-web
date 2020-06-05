package com.cn.strategy;

import java.util.Comparator;

/**
 * @ClassName CatWeightCompartor
 * @Description TODO
 * @Author zhanghongjun
 * @Date 2020-06-05 21:49
 * @Version 1.0
 */
public class CatWeightCompartor implements Comparator<Cat>{
    @Override
    public int compare(Cat o1, Cat o2) {
        if(o1.weight<o2.weight){
            return -1;
        }else if(o1.weight>o2.weight){
            return 1;
        }else{
            return  0;
        }
    }
}

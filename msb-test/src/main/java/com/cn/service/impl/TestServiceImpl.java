package com.cn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.dao.TestMapper;
import com.cn.entity.AssessTrackVo;
import com.cn.entity.TestEntity;
import com.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName TestServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:43
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,TestEntity>
        implements TestService {
    @Autowired
    TestMapper testMapper;

    /**
     * 该方法实现了树结构根据最后的节点颜色，标记父亲节点以及祖父节点的颜色
     * 也实现了逆向统计值，逆向标颜色
     * @param targetId
     * @return
     */
    @Override
    public AssessTrackVo selAssessTrackVo(Long targetId) {
        //先查询最顶级对象信息
        AssessTrackVo assessTrackVo =  testMapper.selAssessTrackVo(targetId);
        assessTrackVo.setWeight(0);
        //通过目标id查询第一层级的指标
        List<AssessTrackVo> firstList =testMapper.selATVFirstListByTargetId(targetId,0L);
        if(firstList!=null&&firstList.size()>0){
            //封装权重总分
            for (AssessTrackVo a: firstList) {
                assessTrackVo.setWeight(a.getWeight()+assessTrackVo.getWeight());
            }
        }
        if(firstList!=null&&firstList.size()>0){
            recursiveList(firstList,assessTrackVo);
            assessTrackVo.setChildren(firstList);
            //先封装得分
            for (AssessTrackVo a: firstList) {
                setActualScore(a,a.getChildren());
                setParentActualScore(assessTrackVo,a);
            }

            //再封装色标
            for (AssessTrackVo a: firstList) {
                setColorCode(a,a.getChildren());
                if(assessTrackVo.getColorCode()==null&&a.getColorCode()!=null){
                    colorOneSet(assessTrackVo,a);
                }
                if(assessTrackVo.getColorCode()!=null&& a.getColorCode()!=null){
                    //比大小
                    colorSetExchange(assessTrackVo,a);
                }
            }
        }
        return assessTrackVo;
    }
    private void setActualScore(AssessTrackVo assessTrackVo,List<AssessTrackVo> list){
        if(list!=null){
            for (AssessTrackVo e:list) {
                if(e.getActualScore()==null&& assessTrackVo.getActualScore()==null){
                    setActualScore(e,e.getChildren());
                }
                if(assessTrackVo.getActualScore()!=null&& e.getActualScore()==null){
                    setActualScore(e,e.getChildren());
                }
                if(assessTrackVo.getActualScore()==null&&e.getActualScore()!=null){
                    setParentActualScore(assessTrackVo,e);
                    continue;
                }
                if(assessTrackVo.getActualScore()!=null&&e.getActualScore()!=null){
                    setParentActualScore(assessTrackVo,e);
                }
            }
        }
    }
    private void setParentActualScore(AssessTrackVo parent,AssessTrackVo children){
        if(parent.getActualScore()==null){
            parent.setActualScore(0);
        }
        parent.setActualScore(children.getActualScore()+parent.getActualScore());
    }
    private void setColorCode(AssessTrackVo assessTrackVo,List<AssessTrackVo> list) {
        if(list!=null){
            for (AssessTrackVo e:list) {
                if(e.getColorCode()==null&& assessTrackVo.getColorCode()==null){
                    setColorCode(e,e.getChildren());
                }
                if(assessTrackVo.getColorCode()!=null&& e.getColorCode()==null){
                    setColorCode(e,e.getChildren());
                }
                if(assessTrackVo.getColorCode()==null&&e.getColorCode()!=null){
                    colorOneSet(assessTrackVo,e);
                }
                if(assessTrackVo.getColorCode()!=null&&e.getColorCode()!=null){
                    //比大小
                    colorSetExchange(assessTrackVo,e);
                }
            }
        }
    }
    private void  colorOneSet(AssessTrackVo parent,AssessTrackVo children){
        //向上封装色标
        if (children.getColorCode()>=1) {
            parent.setColorCode(1);
        }else if (children.getColorCode() ==0){
            parent.setColorCode(0);
        }

    }
    //比较颜色指标大小
    private void  colorSetExchange(AssessTrackVo parent,AssessTrackVo children){
        if (parent.getColorCode()<children.getColorCode()) {
            parent.setColorCode(1);
        }
    }
    private  synchronized void recursiveList(List<AssessTrackVo> firstList,AssessTrackVo assessTrackVo){
        firstList.stream().forEach(atv ->{
            List<AssessTrackVo> list=testMapper.selATVFirstListByTargetId(null,atv.getId());
            if(list!=null&&list.size()>0){
                recursiveList(list,atv);
                atv.setChildren(list);
            }else{
                //说明是最后一个指标，拿出自己的权重数
                assessTrackVo.setWeight(assessTrackVo.getWeight()+atv.getWeight());
                //设置色标 0表示蓝色 、 1：黄色 、2：红色
                int num =20;
                if((float)num/atv.getWeight()<0.7){
                    atv.setColorCode(2);
                    atv.setActualScore((int)(Math.round(Math.random() * 100)));
                    //计算实际得分
                }else if((float)num/atv.getWeight()>=0.7){
                    atv.setColorCode(0);
                    atv.setActualScore((int)(Math.round(Math.random() * 100)));
                }
            }
        });
    }

}

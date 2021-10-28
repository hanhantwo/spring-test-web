package com.cn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.dao.TestMapper;
import com.cn.entity.AssessTrackVo;
import com.cn.entity.TestEntity;
import com.cn.service.TestService;

/**
 * @ClassName TestServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:43
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {
	@Resource
	TestMapper testMapper;

	/**
	 * 该方法实现了树结构根据最后的节点颜色，标记父亲节点以及祖父节点的颜色 也实现了逆向倒推统计值，逆向标记颜色
	 * 
	 * @param targetId
	 * @return
	 */
	@Override
	public AssessTrackVo selAssessTrackVo(Long targetId) {
		// 先查询最顶级对象信息
		AssessTrackVo assessTrackVo = testMapper.selAssessTrackVo(targetId);// 数据库读取数据
		assessTrackVo.setWeight(0);
		// 通过目标id查询第一层级的指标
		List<AssessTrackVo> firstList = testMapper.selATVFirstListByTargetId(targetId, 0L);// 数据库读取数据
		if (firstList != null && firstList.size() > 0) {
			// 封装权重总分
			for (AssessTrackVo a : firstList) {
				assessTrackVo.setWeight(a.getWeight() + assessTrackVo.getWeight());
			}
		}
		if (firstList != null && firstList.size() > 0) {
			this.recursiveList(firstList, assessTrackVo);
			assessTrackVo.setChildren(firstList);
			// 封装色标
			for (AssessTrackVo a : firstList) {
				this.setColorCode(a, a.getChildren());
				if (assessTrackVo.getColorCode() == null && a.getColorCode() != null) {
					this.colorOneSet(assessTrackVo, a);
				}
				if (assessTrackVo.getColorCode() != null && a.getColorCode() != null) {
					// 比大小
					this.colorSetExchange(assessTrackVo, a);
				}
			}
		}
		return assessTrackVo;
	}

	// 递归遍历
	private void setColorCode(AssessTrackVo assessTrackVo, List<AssessTrackVo> list) {
		if (list != null) {
			for (AssessTrackVo e : list) {
				if (e.getColorCode() == null && assessTrackVo.getColorCode() == null) {
					// 递归遍历
					this.setColorCode(e, e.getChildren());
				}
				if (assessTrackVo.getColorCode() != null && e.getColorCode() == null) {
					// 递归遍历
					this.setColorCode(e, e.getChildren());
				}
				if (assessTrackVo.getColorCode() == null && e.getColorCode() != null) {
					// 根据子节点大小给父节点赋值（此时父节点为空）
					this.colorOneSet(assessTrackVo, e);
				}
				if (assessTrackVo.getColorCode() != null && e.getColorCode() != null) {
					// 比大小颜色值大小，然后修改父节点的值（此时父节点已有值）
					this.colorSetExchange(assessTrackVo, e);
				}
			}
		}
	}

	private void colorOneSet(AssessTrackVo parent, AssessTrackVo children) {
		// 向父节点标记色标
		if (children.getColorCode() >= 1) {
			parent.setColorCode(1);
		} else if (children.getColorCode() == 0) {
			parent.setColorCode(0);
		}

	}

	private void colorSetExchange(AssessTrackVo parent, AssessTrackVo children) {
		if (parent.getColorCode() < children.getColorCode()) {
			parent.setColorCode(1);
		}
	}

	private void recursiveList(List<AssessTrackVo> firstList, AssessTrackVo assessTrackVo) {
		firstList.stream().forEach(atv -> {
			List<AssessTrackVo> list = testMapper.selATVFirstListByTargetId(null, atv.getId());
			if (list != null && list.size() > 0) {
				this.recursiveList(list, atv);
				atv.setChildren(list);
			} else {
				// 说明是最后一个指标，拿出自己的权重数
				assessTrackVo.setWeight(assessTrackVo.getWeight() + atv.getWeight());
				// 设置色标 0表示蓝色 、 1：黄色 、2：红色
				int num = 20;
				if ((float) num / atv.getWeight() < 0.7) {
					atv.setColorCode(2);
					// 计算实际得分
				} else if ((float) num / atv.getWeight() >= 0.7) {
					atv.setColorCode(0);
				}
			}
		});
	}

	// todo:快排算法案例
	public static int[] kuaipai(int[] arr) {
		int k = (int) (Math.random() * arr.length) + 1;

//		this.process(arr, k);
		return null;
	}

	public static void process(int[] arr, int k) {

	}
}

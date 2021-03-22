package com.cn;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: GlobalException
 * @Description:  全局异常处理类
 * @Author: zhanghongjun
 * @Date: 2021/3/16 08:56
 */
@Slf4j
@RestControllerAdvice // 可指定包前缀，比如：(basePackages = "com.pj.admin")
public class GlobalException {
    // 在当前类每个方法进入之前触发的操作
    @ModelAttribute
    public void get(HttpServletRequest request) throws IOException {
    log.info("拦截请求进入");
    log.info("权限验证，权限精确到按钮级的意思就是指：权限范围可以控制到页面上的每一个按钮是否显示\n" +
            "\n" +
            "思路：如此精确的范围控制只依赖后端已经难以完成，此时需要前端进行一定的逻辑判断\n" +
            "\n" +
            "在登录时，把当前账号拥有的所有权限码一次性返回给前端\n" +
            "前端将权限码集合保存在localStorage或其它全局状态管理对象中\n" +
            "在需要权限控制的按钮上，使用js进行逻辑判断，例如在vue框架中我们可以使用如下写法：\n" +
            "<button v-if=\"arr.indexOf('user:delete') > -1\">删除按钮</button>\n" +
            "复制到剪贴板错误复制成功\n" +
            "1\n" +
            "其中：arr是当前用户拥有的权限码数组，user:delete是显示按钮需要拥有的权限码，删除按钮是用户拥有权限码才可以看到的内容\n" +
            "注意：以上写法只为提供一个参考示例，不同框架有不同写法，开发者可根据项目技术栈灵活封装进行调用\n" +
            "\n" +
            "前端有了鉴权后端还需要鉴权吗？\n" +
            "需要！前端的鉴权只是一个辅助功能，对于专业人员这些限制都是可以轻松绕过的，为保证服务器安全，无论前端是否进行了权限校验，后端接口都需要对会话请求再次进行权限校验！");

    }
    // 全局异常拦截（拦截项目中的所有异常）
    @ExceptionHandler
    public String handlerException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // 打印堆栈，以供调试
        e.printStackTrace();

        // 不同异常返回不同状态码
//        AjaxJson aj = null;
//        if (e instanceof NotLoginException) {	// 如果是未登录异常
//            NotLoginException ee = (NotLoginException) e;
//            aj = AjaxJson.getNotLogin().setMsg(ee.getMessage());
//        } else if(e instanceof NotRoleException) {		// 如果是角色异常
//            NotRoleException ee = (NotRoleException) e;
//            aj = AjaxJson.getNotJur("无此角色：" + ee.getRole());
//        } else if(e instanceof NotPermissionException) {	// 如果是权限异常
//            NotPermissionException ee = (NotPermissionException) e;
//            aj = AjaxJson.getNotJur("无此权限：" + ee.getCode());
//        } else {	// 普通异常, 输出：500 + 异常信息
//            aj = AjaxJson.getError(e.getMessage());
//        }

        // 返回给前端
        return "-1";

        // 输出到客户端
//		response.setContentType("application/json; charset=utf-8"); // http说明，我要返回JSON对象
//		response.getWriter().print(new ObjectMapper().writeValueAsString(aj));
    }



    // 全局异常拦截（拦截项目中的NotLoginException异常）
//	@ExceptionHandler(NotLoginException.class)
//	public AjaxJson handlerNotLoginException(NotLoginException nle, HttpServletRequest request, HttpServletResponse response)
//			throws Exception {
//
//		// 打印堆栈，以供调试
//		nle.printStackTrace();
//
//		// 判断场景值，定制化异常信息
//		String message = "";
//		if(nle.getType().equals(NotLoginException.NOT_TOKEN)) {
//			message = "未提供token";
//		}
//		else if(nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
//			message = "token无效";
//		}
//		else if(nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
//			message = "token已过期";
//		}
//		else if(nle.getType().equals(NotLoginException.BE_REPLACED)) {
//			message = "token已被顶下线";
//		}
//		else if(nle.getType().equals(NotLoginException.KICK_OUT)) {
//			message = "token已被踢下线";
//		}
//		else {
//			message = "当前会话未登录";
//		}
//
//		// 返回给前端
//		return AjaxJson.getError(message);
//	}


}

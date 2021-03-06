package com.cn.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cn.entity.TestValida;

import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName TestController
 * @Description TODO
 * @Author
 * @Date 2021/2/25 9:27
 */
@Slf4j
@RestController
public class TestController {
	private String savePath = "/Users/zhanghongjun/Desktop/图片/pdf/";

	/**
	 * 文件上传 方法1
	 *
	 * @param file
	 */
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		// 要上传的文件路径：一般是服务器文件夹地址
		String upAddress = "F:\\workSpace\\spring-test-web\\doc";
		if (file == null) {
			return null;
		}
		// 获取文件名称
		String fileName = file.getOriginalFilename();
		File dir = new File(upAddress);
		// 先判断路径地址是否存在，不存在就创建
		if (!dir.exists()) {
			dir.mkdir();
		}
		// 生成中很可能重新生成文件名称，就需要截取后缀名
		// 截取前缀名
		String prefixFileName = fileName.substring(0, fileName.indexOf("."));

		// 截取后缀名
		String suffixFileName = fileName.substring(fileName.lastIndexOf("."));

		// 这里可以随机生成一个文件名+后缀
		String newFileName = "文件名称" + suffixFileName;

		// 保存文件
		File saveFile = new File(upAddress + newFileName);
		try {
			file.transferTo(saveFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("保存文件失败");
		}

		return newFileName;
	}

	/**
	 * 上传附件
	 */
	public Integer uploadFile(MultipartFile file, HttpServletRequest request) {
		try {
			// 获取原始文件名
			String fileName = file.getOriginalFilename();
			// 截取文件后缀
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			// 文件名
			fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + suffix;
			// 保存到服务器的路径
			String path = request.getSession().getServletContext().getRealPath("/")
					+ "WEB-INF\\classes\\static\\approval\\";
			// 文件上传到服务器
			SaveFileFromInputStream(file.getInputStream(), path, fileName);
		} catch (IOException e) {
			System.out.println(e);
		}
		return 1;
	}

	/**
	 * @param stream   文件输入流
	 * @param path     保存的文件地址
	 * @param savefile 文件名
	 * @throws IOException
	 */
	public static void SaveFileFromInputStream(InputStream stream, String path, String savefile) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + savefile);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	/**
	 * @param res
	 * @param fileName 文件名称
	 * @param filePath 文件地址
	 * @Description 文件下载
	 */
	public static void download(HttpServletResponse res, String fileName, String filePath) {
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			res.setHeader("content-type", "application/octet-stream");
			res.setContentType("application/octet-stream");
			res.setCharacterEncoding("utf-8");
			// todo 设置响应头，返回文件名称
			res.setHeader("Content-Disposition",
					"attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			byte[] buff = new byte[1024];
			os = res.getOutputStream();
			// todo: 通过文件地址加名称拿取文件
			bis = new BufferedInputStream(new FileInputStream(new File(filePath + fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("success");
	}

	/**
	 * 删除文件
	 *
	 * @param filePath 文件完整路径（包括文件名称）
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		boolean delete_flag = false;
		File file = new File(filePath);
		if (file.exists() && file.isFile() && file.delete()) {
			delete_flag = true;
		} else {
			delete_flag = false;
		}
		return delete_flag;
	}

	/**
	 * 新增实体类，并校验字段
	 *
	 * @param testValida
	 */
	public void addTestEntiy(@RequestBody @Validated TestValida testValida) {
//调用校验字段方法
//		ValidatorUtils.setValidator(testValida, AddGroup.class);
		log.info(testValida.toString());
	}

	/**
	 * @param filePath 文件地址
	 * @Description 文件下载返回Base64给前端渲染
	 *
	 */
	@PreAuthorize("@ss.hasPermi('assessment:redline:list')")
	@GetMapping("/downloadFile")
	@ApiOperation("历史记录中--文件下载")
	public String downloadFile(@Param("filePath") String filePath) {

		BufferedInputStream bis = null;
		byte[] data = null;
		try {
			// todo: 通过文件地址加名称拿取文件
			bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
			data = new byte[bis.available()];
			bis.read(data);
		} catch (Exception e) {
			log.error("图片渲染失败：", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception e) {
					log.error("图片渲染关闭流失败：", e);
				}
			}
		}
		String str = new String(Base64.encodeBase64(data));
		return str;
	}

	/**
	 * 获取图片 页面可以直接访问的
	 */
	@GetMapping("/preview")
	@ApiOperation("获取图片")
	public int images(HttpServletRequest request, HttpServletResponse response, @Param("fileName") String fileName)
			throws IOException {
		String filePath = savePath + fileName;
		File file = new File(filePath);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		response.setHeader("Content-Type", "image/jpeg");
		byte b[] = new byte[1024];
		int read;
		try {
			while ((read = bis.read(b)) != -1) {
				bos.write(b, 0, read);
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (bos != null) {
				bos.close();
			}
			if (bis != null) {
				bis.close();
			}
		}
		return 1;
	}

	public static int process(int prc, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (prc > rest) {
			return 0;
		}
		int ways = 0;
		for (int i = prc; i <= rest; i++) {
			ways += process(i, rest - i);
		}
		return ways;
	}

	public static int process1(int pre, int rest) {
		if (rest == 0) {
			return 1;
		}
		if (pre > rest) {
			return 0;
		}
		int ways = 0;
		for (int first = pre; first <= rest; first++) {
			ways += process1(first, rest - first);
		}
		return ways;
	}

	public static List<String> dp2(String s) {
		char[] str = s.toCharArray();
		String path = "";
		List<String> ans = new ArrayList<>();
		process2(str, 0, path, ans);
		return ans;
	}

	public static void process2(char[] chars, int pre, String path, List<String> ans) {
		if (pre == chars.length) {
			ans.add(path);
			return;
		}
		// 不要当前位置
		process2(chars, pre + 1, path, ans);
		// 要当前位置
		process2(chars, pre + 1, path + chars[pre], ans);
	}

	@Getter
	@Setter
	class Node {
		/**
		 * 电梯名称
		 */
		private String name;
		/**
		 * 是否满员
		 */
		private boolean status = false;
		/**
		 * 所在楼层
		 */
		private Integer number;
		/**
		 * 1 朝上 -1朝下 0停顿
		 */
		private Integer toward;

	}

	/**
	 * 
	 * @param list   所有电梯
	 * @param number 所在楼层
	 * @param toward 朝向 1 朝上 -1朝下
	 * @param floors 全部楼层
	 */
	public static Node dp3(List<Node> list, Integer number, Integer toward, List<Integer> floors) {
		if (list == null || number <= 0 || toward == 0 || floors == null) {
			return null;
		}
		List<Node> list1 = new ArrayList<>();
		for (Node node : list) {
			Node n = process3(node, number, toward);
			if (n != null) {
//说明复合要求的电梯
				list1.add(n);
			}
		}
//		list1.stream()
		return process3(null, number, toward);
	}

	public static Node process3(Node node, Integer number, Integer toward) {
		// 说明同向,行驶中
		if (node.getToward() == toward || node.getToward() == 0) {
			if (node.getNumber() - number > 1 && node.getNumber() - number <= 2 && node.status == false) {
				// 可以停
				return node;
			}
		}
		return null;
	}

	public static void main(String[] args) {
//		int num = process1(1, 5);
//		System.out.println(num);
//		int num1 = process(1, 5);
//		System.out.println(num1);
//		System.out.println(dp2("abc").toString());

		int[] arr = { 1, 2, 3 };

		List list = Arrays.asList(arr);

		System.out.println(list.toString());
		arr[1] = 5;
		System.out.println(list.toString());
//		list.add(4);
	}
}

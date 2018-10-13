package com.recive.sbus.file.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.recive.sbus.common.BaseVo;
import com.recive.sbus.common.config.SystemConfig;
import com.recive.sbus.common.util.FileUtil;
import com.recive.sbus.common.util.HttpUtil;
import com.recive.sbus.common.util.StringUtil;
import com.recive.sbus.file.FileConstants;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private SystemConfig systemConfig;

	@GetMapping("uploadgreet")
	public String upload() {
		return "file/uploadgreet";
	}

	// 文件上传相关代码
	@ResponseBody
	@RequestMapping(value = "/upload", produces = { "application/json;charset=UTF-8" })
	public BaseVo<Object> upload(MultipartFile file) {
		BaseVo<Object> vo = new BaseVo<Object>();
		if (file.isEmpty()) {
			vo.setFailed("文件为空！");
			return vo;
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String uploadPath = systemConfig.getFileUpload();
		if (StringUtil.isEmpty(uploadPath)) {
			vo.setFailed("请设置文件上传存放位置！");
		}
		// 解决中文问题，liunx下中文路径，图片显示问题
		// fileName = UUID.randomUUID() + suffixName;
		fileName = FileUtil.getTransPath(suffixName);
		String filePath = uploadPath + File.separator + fileName;
		File dest = new File(filePath);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			// 上传后的文件名需要返回给前端
			Map<String, String> map = new HashMap<String, String>();
			map.put("filename", fileName.substring(FileConstants.FILEPATHLENTH_DEFAULT, fileName.length()));
			vo.setDatas(map);
			return vo;
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		vo.setFailed("上传失败！");
		return vo;
	}

	// 文件下载相关代码
	@RequestMapping("/download")
	public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = "FileUploadTests.java";
		if (fileName != null) {
			// 当前是从该工程的WEB-INF//File//下获取文件(该目录可以在下面一行代码配置)然后下载到C:\\users\\downloads即本机的默认下载的目录
			String realPath = request.getServletContext().getRealPath("//WEB-INF//");
			File file = new File(realPath, fileName);
			if (file.exists()) {
				response.setContentType("application/force-download");// 设置强制下载不打开
				response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try {
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1) {
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
					System.out.println("success");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (bis != null) {
						try {
							bis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (fis != null) {
						try {
							fis.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return null;
	}

	// 多文件上传
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					stream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}

	@ResponseBody
	@GetMapping("/getFilePath")
	public BaseVo<Object> getFilePath(String filename, HttpServletRequest request) {
		BaseVo<Object> vo = new BaseVo<Object>();
		if (StringUtil.isEmpty(filename)) {
			vo.setFailed("请指定文件名！");
			return vo;
		}

		Map<String, String> result = new HashMap<String, String>();
		String filePath = HttpUtil.serverBasePath(request) + File.separator + FileUtil.getFilePathByFileName(filename);
		result.put("filepath", filePath);
		vo.setDatas(result);
		return vo;
	}
}

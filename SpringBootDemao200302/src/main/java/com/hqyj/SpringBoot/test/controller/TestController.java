package com.hqyj.SpringBoot.test.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hqyj.SpringBoot.config.ResourceConfigBean;
import com.hqyj.SpringBoot.test.entity.City;
import com.hqyj.SpringBoot.test.entity.Country;
import com.hqyj.SpringBoot.test.service.CityService;
import com.hqyj.SpringBoot.test.service.CountryService;
import com.hqyj.SpringBoot.test.vo.ApplicationTest;

@Controller
@RequestMapping("/test")
public class TestController {
	@Value("${server.port}")
	private int port;
	@Value("${com.thornBirth.name}")
	private String name;
	@Value("${com.thornBirth.age}")
	private int age;
	@Value("${com.thornBirth.desc}")
	private String desc;
	@Value("${com.thornBirth.random}")
	private String random;

	@Autowired
	private ApplicationTest ApplicationTest;

	private static final Logger LOGGR = LoggerFactory.getLogger(TestController.class);
	private static final Logger LOGGER = null;
	@Autowired
	private CityService cityService;
	@Autowired
	private CountryService countryService;
	@Autowired
	private ResourceConfigBean resourceConfigBean;

	@RequestMapping("/download")
	@ResponseBody
	public ResponseEntity<UrlResource> download(@RequestParam String fileName) {

		try {
			String resourcePath = resourceConfigBean.getResourcePath() + fileName;
//			Resource resource = new UrlResource(Paths.get("F:\\upload\\" + fileName).toUri());
			UrlResource resource = new UrlResource(ResourceUtils.getURL(resourcePath));

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/octet-stream")
					.header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName))
					.body(resource);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 将文件以BufferedInputStream的方式读取到byte[]里面，然后用OutputStream.write输出文件
	 */
	@RequestMapping("/download1")
	public void downloadFile1(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "F:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		if (downloadFile.exists()) {
			response.setContentType("application/octet-stream");
			response.setContentLength((int) downloadFile.length());
			response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", fileName));

			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(downloadFile);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				LOGGER.debug(e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if (fis != null) {
						fis.close();
					}
					if (bis != null) {
						bis.close();
					}
				} catch (Exception e2) {
					LOGGER.debug(e2.getMessage());
					e2.printStackTrace();
				}
			}
		}
	}

	/**
	 * 以包装类 IOUtils 输出文件
	 */
	@RequestMapping("/download2")
	public void downloadFile2(HttpServletRequest request, HttpServletResponse response, @RequestParam String fileName) {
		String filePath = "F:/upload" + File.separator + fileName;
		File downloadFile = new File(filePath);

		try {
			if (downloadFile.exists()) {
				response.setContentType("application/octet-stream");
				response.setContentLength((int) downloadFile.length());
				response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
						String.format("attachment; filename=\"%s\"", fileName));

				InputStream is = new FileInputStream(downloadFile);
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getMessage());
			e.printStackTrace();
		}
	}

	@PostMapping(value = "/files", consumes = "multipart/form-data")
	public String uploadFiles(@RequestParam MultipartFile[] files, RedirectAttributes redirectAttributes) {
		boolean isEmpty = true;
		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				continue;
			}

			try {
				String destFilePath = "F:\\upload" + File.separator + file.getOriginalFilename();
				File destFile = new File(destFilePath);
				file.transferTo(destFile);

				isEmpty = false;
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("message", "上传失败.");
				return "redirect:/test/index";
			}

		}

		if (isEmpty) {
			redirectAttributes.addFlashAttribute("message", "请选择文件");
		} else {
			redirectAttributes.addFlashAttribute("message", "上传成功.");
		}

		return "redirect:/test/index";
	}

	@PostMapping(value = "/file", consumes = "multipart/form-data")
	public String uploadFile(@RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {

		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "请选择文件");
			return "redirect:/test/index";
		}
		String resourcePath = resourceConfigBean.getResourcePath() + file.getOriginalFilename();
		try {
			File destFile = new File(ResourceUtils.getURL(resourcePath).getPath());
			file.transferTo(destFile);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "上传失败.");
			return "redirect:/test/index";
		}

		redirectAttributes.addFlashAttribute("message", "上传成功.");
		return "redirect:/test/index";
	}

	/**
	 * 127.0.0.1/test/index
	 */
	@RequestMapping("/index")
	public String indexPage(ModelMap modelmap) {
		int countryId = 522;
		List<City> cities = cityService.getCitiesByCountryId(countryId);
		cities = cities.stream().limit(10).collect(Collectors.toList());
		Country country = countryService.getCountryByCountryId(countryId);

		modelmap.addAttribute("thymeleafTitle", "scdscsadcsacd");
		modelmap.addAttribute("checked", true);
		modelmap.addAttribute("currentNumber", 99);
		modelmap.addAttribute("changeType", "checkbox");
		modelmap.addAttribute("baiduUrl", "/test/log");
		modelmap.addAttribute("city", cities.get(0));
		// modelmap.addAttribute("shopLogo",
		// "http://cdn.duitang.com/uploads/item/201308/13/20130813115619_EJCWm.thumb.700_0.jpeg");
		modelmap.addAttribute("shopLogo", "/upload/222.jpg");
		modelmap.addAttribute("country", country);
		modelmap.addAttribute("cities", cities);
		modelmap.addAttribute("updateCityUri", "/api/city");
		// modelmap.addAttribute("template", "test/index");

		return "index";
	}

	/**
	 * 127.0.0.1/test/log
	 */
	@RequestMapping("/log")
	@ResponseBody
	public String logTest() {
		// TRACE<DEBUG<INFO<WARN<ERROR
		LOGGR.trace("This is TRACE log");
		LOGGR.debug("This is DEBUG log");
		LOGGR.info("This is INFO log");
		LOGGR.warn("This is WARN log");
		LOGGR.error("This is ERROR log");
		return "This is log test.";
	}

	/**
	 * 127.0.0.1/config
	 */
	@RequestMapping("/config")
	@ResponseBody
	public String configInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append(port).append("----").append(name).append("----").append(age).append("----").append(desc)
				.append("----").append(random).append("----").append("<br>");

		sb.append(ApplicationTest.getName1()).append("----").append(ApplicationTest.getAge1()).append("----")
				.append(ApplicationTest.getDesc1()).append("----").append(ApplicationTest.getRandom1()).append("----")
				.append("<br>");
		return sb.toString();

	}

	/**
	 * 127.0.0.1/test/desc?key=fuck
	 */
	@RequestMapping("/desc")
	@ResponseBody
	public String testDesc(HttpServletRequest request, @RequestParam String key) {
		String key2 = request.getParameter("key");
		return "This is test module desc.112233" + key + "==" + key2;
	}
}

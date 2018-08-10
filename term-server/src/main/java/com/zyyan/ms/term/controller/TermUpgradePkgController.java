package com.zyyan.ms.term.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.zyyan.ms.term.common.BaseController;
import com.zyyan.ms.term.entity.TermUpgradePkg;
import com.zyyan.ms.term.service.ITermUpgradePkgService;
import com.zyyan.ms.common.entity.RespRes;
import com.zyyan.ms.common.util.CommUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers.data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * <p>
 * 设备升级包表 前端控制器
 * </p>
 *
 * @author cli
 * @since 2018-05-10
 */
@Controller
@RequestMapping("/termUpgradePkg")
public class TermUpgradePkgController extends BaseController {

	@Value("${sys.upgradepath.main}")
	private String upgradePath;
//	@Value("${sys.upgradepath.mp3}")
//	private String upgradePath;

	@Resource
	ITermUpgradePkgService upgradeService;

	@RequestMapping(value = "/findPage")
	@ResponseBody
	public Page findPage(HttpServletRequest request, Integer page, Integer rows) {
		Page<TermUpgradePkg> pg = new Page(page, rows);
		String name = request.getParameter("name");
		String type = request.getParameter("type");//区分是主程序升级还是 mp3升级
		Wrapper<TermUpgradePkg> wrapper = new EntityWrapper<>();
		if ("main".equals(type)) {
			wrapper.isNotNull("TERM_TYPE_ID");
		} else if ("mp3".equals(type)) {
			wrapper.isNull("TERM_TYPE_ID");
		}
		wrapper.like("NAME", name);
		return upgradeService.selectPage(pg, wrapper);
	}

	@RequestMapping(value = "/findUpgradeFileList")
	@ResponseBody
	public List<TermUpgradePkg> findUpgradeFileList(HttpServletRequest request) {
		String name = request.getParameter("name");
		String type = request.getParameter("type");//区分是主程序升级还是 mp3升级
		Wrapper<TermUpgradePkg> wrapper = new EntityWrapper<>();
		if ("main".equals(type)) {
			wrapper.isNotNull("TERM_TYPE_ID");
		} else if ("mp3".equals(type)) {
			wrapper.isNull("TERM_TYPE_ID");
		}
		wrapper.like("NAME", name);
		return upgradeService.selectList(wrapper);
	}

	@RequestMapping(value = "/upgrade")
	@ResponseBody
	public RespRes upgrade(MultipartHttpServletRequest request, HttpServletResponse resp) {
		String msg = "";
		RespRes respRes = RespRes.fail(msg);
		String fileType = request.getParameter("fileType");
		String termModel = request.getParameter("termModel");
		String termType = request.getParameter("termType");
		try {
			MultipartFile file = request.getFile("uploadFile");
			//备注
			String packNameGet = file.getOriginalFilename();
			File packFile = new File(upgradePath);
			//如果目录不存在，则创建
			if (!packFile.exists()) {
				packFile.mkdirs();
			}
			long fileSize = file.getSize();
			if (fileSize <= 20 * 1024 * 1024) {
				file.transferTo(new File(upgradePath + packNameGet));
				TermUpgradePkg pkg = new TermUpgradePkg();
				pkg.setName(packNameGet);
				pkg.setPkgPath(upgradePath);
				pkg.setPkgSize(fileSize);
				pkg.setTermModelId(CommUtils.notNull(termModel) ? Long.parseLong(termModel) : null);
				pkg.setTermTypeId(CommUtils.notNull(termType) ? Long.parseLong(termType) : null);
				pkg.setPkgFileName(packNameGet);
				pkg.setTime(new Date());
				upgradeService.insert(pkg);
				return RespRes.ok();
			} else {
				msg = "文件太大，不要超过 20M";
			}
		} catch (Exception ex) {
			logger.error(null, ex);
		}
		return respRes;
	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public RespRes delete(HttpServletRequest request, Integer page, Integer rows) {
		String idsStr = request.getParameter("idsStr");
		try {
			if (!CommUtils.isNull(idsStr)) {
				String[] idsArray = idsStr.split(",");
				List<Integer> pkgIds = new ArrayList<Integer>();
				for (String str : idsArray) {
					pkgIds.add(Integer.parseInt(str));
				}
				//删除升级包目录下的包文件
				TermUpgradePkg pkg;
				File file = null;
				for (Integer pkgId : pkgIds) {
					pkg = upgradeService.selectById(pkgId);
					if (pkg != null) {
						file = new File(pkg.getPkgPath() + File.separator + pkg.getName());
					}
					if (file != null && file.exists()) {
						file.delete();
					}
					upgradeService.deleteById(pkgId);
				}
			}
			return RespRes.ok();
		} catch (Exception ex) {
			logger.error(null, ex);
		}
		return RespRes.fail("删除出现异常");
	}
}

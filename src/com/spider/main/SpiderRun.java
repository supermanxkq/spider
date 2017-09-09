package com.spider.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.spider.bean.User;
import com.spider.dao.UserDao;
import com.spider.util.Utils;

public class SpiderRun {
	// 保存总条数
	public static int totalSaveNum = 0;

	public static void main(String[] args) {
		System.err.println("清空表。。。");
		new UserDao().clearTable();
		SpiderRun t = new SpiderRun();
		// 解析URL获取数据
		for (int h = 0; h < 38; h++) {
			System.err.println("开始读取第" + (h + 1) + "页的数据。。。。。。。");
			// 获取要解析的html
			Document doc = t.getDocument("http://www.kaikaidai.com/Lend/Black.aspx", h);

			// 获取数据集合
			List<Elements> entityList = t.queryEntityList(doc);
			// // 将数据存入数据库
			t.saveEntity(entityList);
			System.err.println("第" + (h + 1) + "页的数据保存完毕！！！。。。。。。。");
		}
		System.err.println("程序执行完毕，总共保存了【" + totalSaveNum + "】条数据！！！");
	}

	public List<Elements> queryEntityList(Document doc) {
		List<Elements> entityList = new ArrayList<>();
		// 获取目标HTML代码
		// 选择每个记录html-
		Elements divElement = doc.select("div.main");
		// 使用属性选择器进行选择
		Elements tableElemets = divElement.select("table.hmd_ytab");
		// 获取每条记录的详细信息
		for (int i = 0; i < tableElemets.select("table").size(); i++) {
			// 代表一个对象
			Elements trs = tableElemets.select("table").get(i).select("tr");
			entityList.add(trs);
		}
		return entityList;

	}

	public void saveEntity(List<Elements> entityList) {
		UserDao userDao = new UserDao();
		for (Elements trs : entityList) {
			User user = new User();
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < trs.size(); j++) {
				Elements tds = trs.get(j).select("td");
				for (int k = 0; k < tds.size(); k++) {
					// 不选择第0行第0列,图片
					if (j == 0 && k == 0) {
						continue;
					}
					// 不选择第4行第0列,图片下面的文字
					if (j == 4 && k == 0) {
						continue;
					}
					if (StringUtils.isNotBlank(tds.get(k).text()) && !(tds.get(k).text().endsWith("："))
							&& !(tds.get(k).text().endsWith(":"))) {
						sb.append(tds.get(k).text() + ",");
					} else if (StringUtils.isBlank(tds.get(k).text())) {
						sb.append(tds.get(k).text() + "无,");
					} else {
						if (!(tds.get(k).text().endsWith("：")) && !(tds.get(k).text().endsWith(":"))) {
							throw new NullPointerException("未知错误！" + tds.get(k).text());
						}
					}
				}
			}
			String[] resultArrays = sb.toString().split(",");
			if (resultArrays.length != 17) {
				throw new NullPointerException("上面一行获取的数据不正确！");
			}
			user.setRealName(resultArrays[0]);
			user.setEmail(resultArrays[1]);
			user.setRepaymentMoney(Utils.getNumFromStr(resultArrays[2]));
			user.setIdCard(resultArrays[3]);
			user.setTelPhone(resultArrays[4]);
			user.setOtherPayment(Utils.getNumFromStr(resultArrays[5]));
			user.setAddress(resultArrays[6]);
			user.setMobile(resultArrays[7]);
			user.setOvDays(Utils.getNumFromStr(resultArrays[8]));
			user.setCoNa(resultArrays[9]);
			user.setOvAmount(Utils.getMoneyFromStr(resultArrays[12]));
			user.setCoAddress(resultArrays[13]);
			System.out.println(sb.toString());
			int n = userDao.regeditUser(user);
			totalSaveNum += n;
		}

	}

	/**
	 * @Description: 获取每一页的内容
	 * @author xukaiqiang
	 * @date 2016年11月16日 下午2:11:07
	 * @param url
	 * @return
	 */
	public Document getDocument(String url, int currentPage) {
		try {
			Connection con = Jsoup.connect(url);
			con.data("__EVENTTARGET", "rpMessage");
			con.data("__EVENTARGUMENT", "pager$" + currentPage);
			// 请求头设置，特别是cookie设置
			con.header("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			con.header("Content-Type", "application/x-www-form-urlencoded");
			con.header("User-Agent",
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36");
			con.timeout(50000);// 设置请求超时时间
			Document doc = con.post();
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

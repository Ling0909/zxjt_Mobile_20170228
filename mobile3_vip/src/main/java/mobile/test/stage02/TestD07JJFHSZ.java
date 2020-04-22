package mobile.test.stage02;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.navigator.ViewNavigator;
import mobile.page.PageJJFHSZ;
import mobile.page.base.PageManager;
import mobile.page.module.Alert;
import mobile.test.base.TestBase;
import mobile.test.base.TestDataProvider;
import up.light.assertutil.AssertUtil;

/**
 * 基金分红设置测试
 */
public class TestD07JJFHSZ extends TestBase {
	private PageJJFHSZ mPage = PageManager.getPage(PageJJFHSZ.class);

	@BeforeClass
	public void before() {
		ViewNavigator.navigate("分红设置", mPage);
	}

	@Test(dataProvider = "dp", dataProviderClass = TestDataProvider.class)
	public void testJJFHSZ(Map<String, String> param) {
		// 输入代码并校验名称
		String vActualZCMC = mPage.doInputJJDM(param.get("代码"));
		AssertUtil.assertEquals(param.get("名称"), vActualZCMC);

		// 获取基金净值
		String vJJJZ = mPage.doGetJJJZ();
		// 选择分红方式、点击设置
		mPage.doSelectFHFS(param.get("分红方式"));
		mPage.doTrade();

		// 替换验证1中的最大可转数量
		String vCheckPoint1 = param.get("验证1").replace("{JJJZ}", vJJJZ);

		// 校验对话框1
		Alert alert = mPage.getAlert();
		String vActualCheckPoint1 = alert.doGetText();
		AssertUtil.assertEquals(vCheckPoint1, vActualCheckPoint1);
		alert.doAccept();

		// 校验对话框2
		String vCheckPoint2 = param.get("验证2");
		String vActualCheckPoint2 = alert.doGetText();
		AssertUtil.assertContains(vActualCheckPoint2, vCheckPoint2);
		alert.doAccept();

		// 参数中加入委托编号
		String vNo = vActualCheckPoint2.substring(vActualCheckPoint2.indexOf("：") + 1, vActualCheckPoint2.length());
		param.put("委托编号", vNo);
	}
}

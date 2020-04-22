package mobile.test.stage02;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.navigator.ViewNavigator;
import mobile.page.PageJY;
import mobile.page.base.PageManager;
import mobile.page.module.Alert;
import mobile.test.base.TestBase;
import mobile.test.base.TestDataProvider;
import up.light.assertutil.AssertUtil;

/**
 * 融资买入测试
 */
public class TestJ01RRRZMR extends TestBase {
	private PageJY mPage = PageManager.getPage(PageJY.class);

	@BeforeClass
	public void before() {
		ViewNavigator.navigate("融资买入", mPage);
	}

	@Test(dataProvider = "dp", dataProviderClass = TestDataProvider.class)
	public void testRRMR(Map<String, String> param) {

		if ("反例".equals(param.get("类型"))) {
			mPage.doInputErrorCode(param.get("代码"));
			Alert alert = mPage.getAlert();
			String vActualCheckPoint1 = alert.doGetText();
			String vCheckPoint1 = param.get("验证1");
			AssertUtil.assertContains(vActualCheckPoint1, vCheckPoint1);
			alert.doAccept();
		} else {
			// 输入代码并校验股票名称
			String vActualName = mPage.doInputCode(param.get("代码"));

			String vExpectName = param.get("名称");
			AssertUtil.assertEquals(vExpectName, vActualName);

			// 输入数量
			mPage.doInputNumber(param.get("数量"));

			// 读取实际价格并替换
			String vActualPrice = mPage.doGetPrice();
			String vCheckPoint1 = param.get("验证1");
			vCheckPoint1 = vCheckPoint1.replace("{PRICE}", vActualPrice);

			// 点击买入
			mPage.doTrade();

			// 获取对话框1内容并校验
			Alert alert = mPage.getAlert();
			String vActualCheckPoint1 = alert.doGetText();
			AssertUtil.assertEquals(vCheckPoint1, vActualCheckPoint1);
			alert.doAccept();

			// 获取对话框2内容并校验
			String vCheckPoint2 = param.get("验证2");
			String vActualCheckPoint2 = alert.doGetText();
			AssertUtil.assertContains(vActualCheckPoint2, vCheckPoint2);
			alert.doAccept();

			// 参数中加入委托编号
			String vNo = vActualCheckPoint2.substring(vActualCheckPoint2.indexOf("：") + 1, vActualCheckPoint2.length());
			param.put("委托编号", vNo);
		}
	}
}

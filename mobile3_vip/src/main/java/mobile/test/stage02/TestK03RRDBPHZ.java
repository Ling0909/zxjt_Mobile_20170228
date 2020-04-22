package mobile.test.stage02;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.navigator.ViewNavigator;
import mobile.page.PageRRDBPHZ;
import mobile.page.base.PageManager;
import mobile.page.module.Alert;
import mobile.test.base.TestBase;
import mobile.test.base.TestDataProvider;
import up.light.assertutil.AssertUtil;

/**
 * 担保品划转测试
 */
public class TestK03RRDBPHZ extends TestBase {
	private PageRRDBPHZ mPage = PageManager.getPage(PageRRDBPHZ.class);

	@BeforeClass
	public void before() {
		ViewNavigator.navigate("担保品划转", mPage);
	}

	@Test(dataProvider = "dp", dataProviderClass = TestDataProvider.class)
	public void testRRDBPHZ(Map<String, String> param) {
		mPage.doSwithTo(param.get("划转方式"));
		String vActualName = mPage.doInputCode(param.get("证券代码"));
		String vExpectName = param.get("证券名称");
		AssertUtil.assertEquals(vExpectName, vActualName);

		// 输入数量
		mPage.doInputNumber(param.get("划转数量"));
		mPage.doTrade();

		Alert alert = mPage.getAlert();
		String actual1 = alert.doGetText();
		AssertUtil.assertEquals(param.get("验证1"), actual1);
		alert.doAccept();

		String actual2 = alert.doGetText();
		AssertUtil.assertContains(actual2, param.get("验证2"));
		alert.doAccept();

		// 参数中加入委托编号
		String vNo = actual2.substring(actual2.indexOf("：") + 1, actual2.length());
		param.put("委托编号", vNo);
	}
}

package mobile.test.stage02;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mobile.navigator.ViewNavigator;
import mobile.page.PageRRZCCX;
import mobile.page.base.PageManager;
import mobile.test.base.TestBase;
import mobile.test.base.TestDataProvider;
import up.light.assertutil.AssertUtil;

/**
 * 融资融券资产查询测试
 */
public class TestI01RRZCCX extends TestBase {
	private PageRRZCCX mPage = PageManager.getPage(PageRRZCCX.class);

	@BeforeClass
	public void before() {
		ViewNavigator.navigate("融资融券资产查询", mPage);
	}

	@Test(dataProvider = "dp", dataProviderClass = TestDataProvider.class)
	public void testRRZCCX(Map<String, String> param) {
		Map<String, String> map = mPage.doGetVaules();
		String actualKQ = map.get("可取");
		String actualKY = map.get("可用");
		AssertUtil.assertEquals(param.get("验证1"), actualKQ);
		AssertUtil.assertEquals(param.get("验证2"), actualKY);
	}
}

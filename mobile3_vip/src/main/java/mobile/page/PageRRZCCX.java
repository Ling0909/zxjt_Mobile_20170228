package mobile.page;

import java.util.HashMap;
import java.util.Map;

import mobile.page.base.AbstractPage;
import up.light.pagefactory.TestElement;

/**
 * 融资融券资产查询界面
 * 
 * @category Stage-02
 */
public class PageRRZCCX extends AbstractPage {
	private TestElement oTextKQ;
	private TestElement oTextKY;

	public Map<String, String> doGetVaules() {
		loadAndCheck();
		Map<String, String> map = new HashMap<>();
		map.put("可取", "可取:" + getText(oTextKQ.e()));
		map.put("可用", "可用:" + getText(oTextKY.e()));
		return map;
	}

	@Override
	public void reset() {
		// do nothing
	}

}

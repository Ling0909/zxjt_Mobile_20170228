package mobile.navigator;

/**
 * 节点进入、退出处理器，可用于在适当时机添加特定的操作（如等待app启动、用户登录等）
 */
public interface INavigationHandler {

	/**
	 * 进入节点动作，调用{@code node.enter()}可使用节点的默认进入
	 * 
	 * @param node 节点对象，由框架自动注入
	 */
	void enter(ViewNode node);

	/**
	 * 退出节点动作，调用{@code node.back()}可使用节点的默认退出
	 * 
	 * @param node 节点对象，由框架自动注入
	 */
	void back(ViewNode node);

	/**
	 * 重置当前处理器，用于nodejs崩溃时重置环境，一般无需人为调用
	 */
	void reset();
}

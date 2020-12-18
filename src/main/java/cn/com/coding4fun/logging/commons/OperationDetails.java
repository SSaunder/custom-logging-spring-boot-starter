package cn.com.coding4fun.logging.commons;

public class OperationDetails {

	/**
	 * 类名
	 */
	private String className;
	/**
	 * 方法名
	 */
	private String methodName;
	/**
	 * 模块名称
	 */
	private String moduleName;
	/**
	 * 操作名称
	 */
	private String operationName;
	/**
	 * 日志类型
	 */
	private Integer logType;
	/**
	 * 日志备注
	 */
	private String remark;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "OperationDetails{" +
				"className='" + className + '\'' +
				", methodName='" + methodName + '\'' +
				", moduleName='" + moduleName + '\'' +
				", operationName='" + operationName + '\'' +
				", logType=" + logType +
				", remark='" + remark + '\'' +
				'}';
	}
}

package cn.com.coding4fun.logging.commons;

import java.util.Arrays;

public enum LoggingTypeEnum {

	/**
	 * 其他
	 */
	OTHER(0, "其它"),
	/**
	 * 添加
	 **/
	ADD(1, "添加"),
	/**
	 * 修改
	 **/
	UPDATE(2, "修改"),
	/**
	 * 删除
	 **/
	DELETE(3, "删除"),
	/**
	 * 查询
	 **/
	QUERY(4, "详情查询"),
	/**
	 * 列表查询
	 **/
	LIST(6, "列表查询"),
	/**
	 * 分页列表
	 **/
	PAGE(7, "分页列表"),
	/**
	 * 其它查询
	 **/
	OTHER_QUERY(8, "其它查询"),
	/**
	 * 文件上传
	 **/
	UPLOAD(9, "文件上传"),
	/**
	 * 文件下载
	 **/
	DOWNLOAD(10, "文件下载"),
	/**
	 * Excel导入
	 **/
	EXCEL_IMPORT(11, "Excel导入"),
	/**
	 * Excel导出
	 **/
	EXCEL_EXPORT(12, "Excel导出");

	/**
	 * 操作代码
	 */
	private Integer code;
	/**
	 * 操作描述
	 */
	private String description;

	LoggingTypeEnum(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public static LoggingTypeEnum codeOf(int code) {
		return Arrays.asList(values()).stream().filter(e -> e.getCode().equals(code)).findAny().orElse(null);
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "LoggingTypeEnum{" +
				"code=" + code +
				", description='" + description + '\'' +
				"} " + super.toString();
	}
}

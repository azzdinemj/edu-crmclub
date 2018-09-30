package com.wuxue.api.utils.plugins;

import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.util.StringUtility;

import javax.xml.bind.DatatypeConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * 自定义 生成
 * 
 * @author tly
 * @date 2018年5月8日 下午3:07:21
 */
public class MyCommentGenerator implements CommentGenerator {

	private Properties properties;

	private boolean suppressDate;

	private boolean suppressAllComments;

	/** If suppressAllComments is true, this option is ignored. */
	private boolean addRemarkComments;

	private SimpleDateFormat dateFormat;

	public MyCommentGenerator() {
		super();
		properties = new Properties();
		suppressDate = false;
		suppressAllComments = false;
		addRemarkComments = false;
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
		// add no file level comments by default
	}

	/**
	 * Mapper Sql 文件 注释
	 * 
	 * Adds a suitable comment to warn users that the element was generated, and
	 * when it was generated.
	 *
	 * @param xmlElement
	 *            the xml element
	 */
	@Override
	public void addComment(XmlElement xmlElement) {
		if (suppressAllComments) {
			return;
		}

		xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$

		StringBuilder sb = new StringBuilder();
		sb.append("  WARNING - "); //$NON-NLS-1$
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		sb.append(" MyBatis Generator Create");
		xmlElement.addElement(new TextElement(sb.toString()));

		String s = getDateString();
		if (s != null) {
			sb.setLength(0);
			sb.append("  Create Date  "); //$NON-NLS-1$
			sb.append(s);
			sb.append('.');
			xmlElement.addElement(new TextElement(sb.toString()));
		}

		xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
		// add no document level comments by default
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
		this.properties.putAll(properties);

		suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

		suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

		addRemarkComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));

		String dateFormatString = properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT);
		if (StringUtility.stringHasValue(dateFormatString)) {
			dateFormat = new SimpleDateFormat(dateFormatString);
		}
	}

	/**
	 * This method adds the custom javadoc tag for. You may do nothing if you do
	 * not wish to include the Javadoc tag - however, if you do not include the
	 * Javadoc tag then the Java merge capability of the eclipse plugin will
	 * break.
	 *
	 * @param javaElement
	 *            the java element
	 * @param markAsDoNotDelete
	 *            the mark as do not delete
	 */
	protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
		StringBuilder sb = new StringBuilder(" * ");
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		if (markAsDoNotDelete) {
			sb.append(" MyBatis Generator 自动生成Bean，自定义属性会覆盖"); //$NON-NLS-1$
		}
		String s = getDateString();
		if (s != null) {
			sb.append(' ');
			sb.append(s);
		}
		javaElement.addJavaDocLine(sb.toString());
	}

	/**
	 * Returns a formated date string to include in the Javadoc tag and XML
	 * comments. You may return null if you do not want the date in these
	 * documentation elements.
	 * 
	 * @return a string representing the current timestamp, or null
	 */
	protected String getDateString() {
		if (suppressDate) {
			return null;
		} else if (dateFormat != null) {
			return dateFormat.format(new Date());
		} else {
			return new Date().toString();
		}
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$

		sb.append(" * TABLE "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		addJavadocTag(innerClass, false);

		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerClass.addJavaDocLine("/**"); //$NON-NLS-1$

		sb.append(" * TABLE "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerClass.addJavaDocLine(sb.toString());

		addJavadocTag(innerClass, markAsDoNotDelete);

		innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * 创建实体 model 类 注释
	 */
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		if (suppressAllComments || !addRemarkComments) {
			return;
		}

		topLevelClass.addJavaDocLine("/**"); //$NON-NLS-1$

		String remarks = introspectedTable.getRemarks();
		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
			topLevelClass.addJavaDocLine(" * "); //$NON-NLS-1$
			String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
			for (String remarkLine : remarkLines) {
				topLevelClass.addJavaDocLine(" *   " + remarkLine); //$NON-NLS-1$
			}
		}
		topLevelClass.addJavaDocLine(" *"); //$NON-NLS-1$

		StringBuilder sb = new StringBuilder();
		sb.append(" * TABLE  "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		topLevelClass.addJavaDocLine(sb.toString());
		topLevelClass.addJavaDocLine(" * MyBatis Generator Create");
		// addJavadocTag(topLevelClass, true);

		topLevelClass.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * 为枚举添加注释
	 */
	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
		if (suppressAllComments) {
			return;
		}

		StringBuilder sb = new StringBuilder();

		innerEnum.addJavaDocLine("/**"); //$NON-NLS-1$

		sb.append(" *  "); //$NON-NLS-1$
		sb.append(introspectedTable.getFullyQualifiedTable());
		innerEnum.addJavaDocLine(sb.toString());

		addJavadocTag(innerEnum, false);

		innerEnum.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * 实体 model 类方法注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		if (suppressAllComments) {
			return;
		}

		field.addJavaDocLine("/**"); //$NON-NLS-1$

		field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
		String remarks = introspectedColumn.getRemarks();
		if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
			String[] remarkLines = remarks.split(System.getProperty("line.separator")); //$NON-NLS-1$
			for (String remarkLine : remarkLines) {
				field.addJavaDocLine(" * " + remarkLine); //$NON-NLS-1$
			}
		}
		field.addJavaDocLine(" * ");
		StringBuilder sb = new StringBuilder();
		sb.append(" * "); //$NON-NLS-1$
		sb.append("WARNING - "); //$NON-NLS-1$
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		sb.append(" MyBatis Generator Create");
		field.addJavaDocLine(sb.toString());

		// addJavadocTag(field, false);

		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		return;
		// Example File
		// StringBuilder sb = new StringBuilder();
		//		field.addJavaDocLine("/**"); //$NON-NLS-1$
		//		sb.append(" * TABLE "); //$NON-NLS-1$
		// sb.append(introspectedTable.getFullyQualifiedTable());
		// field.addJavaDocLine(sb.toString());
		// addJavadocTag(field, false);
		//		field.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	/**
	 * Mapper.java **Example.java 接口类方法注释
	 */
	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		String msg = createMapperAnnotation(method);
		if (StringUtils.isBlank(msg)) {
			return;
		}
		method.addJavaDocLine("/**"); //$NON-NLS-1$
		method.addJavaDocLine(" * " + msg);
		method.addJavaDocLine(" * ");
		StringBuilder sb = new StringBuilder();
		sb.append(" * "); //$NON-NLS-1$
		sb.append("WARNING - "); //$NON-NLS-1$
		sb.append(MergeConstants.NEW_ELEMENT_TAG);
		sb.append(" MyBatis Generator Create");
		method.addJavaDocLine(sb.toString());

		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	private String createMapperAnnotation(Method method) {
		String name = method.getName();
		String msg = "";
		switch (name) {
		case "countByExample":
			msg = "按条件计数\n	 * @param example 条件";
			break;
		case "deleteByExample":
			msg = "按条件删除\n	 * @param example 条件";
			break;
		case "deleteByPrimaryKey":
			msg = "按主键删除";
			break;
		case "insert":
			msg = "插入数据 所有字段\n     * @param record 操作 实体 bean 对象";
			break;
		case "insertSelective":
			msg = "插入数据 值不为 null 的字段\n     * @param record 操作 实体 bean 对象";
			break;
		case "selectByExample":
			msg = "按条件查询\n	 * @param example 条件";
			break;
		case "selectByExampleWithBLOBs":
			msg = "按条件查询（返回包含BOLD字段）\n	 * @param example 条件";
			break;
		case "selectByPrimaryKey":
			msg = "按主键查询";
			break;
		case "updateByExampleSelective":
			msg = "按条件更新值不为 null 的字段\n     * @param record 操作 实体 bean 对象\n	 * @param example 条件";
			break;
		case "updateByExample":
			msg = "按条件更新所有字段\n     * @param record 操作 实体 bean 对象\n	 * @param example 条件";
			break;
		case "updateByExampleWithBLOBs":
			msg = "按条件更新所有字段（更新包含BOLD字段）\n     * @param record 操作 实体 bean 对象\n	 * @param example 条件";
			break;
		case "updateByPrimaryKeySelective":
			msg = "按主键更新值不为 null 的字段\n     * @param record 操作 实体 bean 对象";
			break;
		case "updateByPrimaryKey":
			msg = "按主键更新所有字段\n     * @param record 操作 实体 bean 对象";
			break;
		case "updateByPrimaryKeyWithBLOBs":
			msg = "按主键更新所有字段（更新包含BLOB字段）\n     * @param record 操作 实体 bean 对象";
			break;

		default:
			break;
		}
		return msg;
	}

	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		return; // Example
		//
		// StringBuilder sb = new StringBuilder();
		//
		//		method.addJavaDocLine("/**"); //$NON-NLS-1$
		//
		//		sb.append(" * "); //$NON-NLS-1$
		// sb.append(introspectedColumn.getRemarks());
		// method.addJavaDocLine(sb.toString());
		//
		// // addJavadocTag(method, false);
		//
		//		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
		// if (suppressAllComments) {
		return;//
		// }

		// StringBuilder sb = new StringBuilder();
		//
		//		method.addJavaDocLine("/**"); //$NON-NLS-1$
		//
		//		sb.append(" * "); //$NON-NLS-1$
		//		sb.append(introspectedColumn.getRemarks()); //$NON-NLS-1$
		// method.addJavaDocLine(sb.toString());
		// // addJavadocTag(method, false);
		//
		//		method.addJavaDocLine(" */"); //$NON-NLS-1$
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		method.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
			Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source field: " //$NON-NLS-1$
				+ introspectedTable.getFullyQualifiedTable().toString() + "." //$NON-NLS-1$
				+ introspectedColumn.getActualColumnName();
		method.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		field.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn,
			Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source field: " //$NON-NLS-1$
				+ introspectedTable.getFullyQualifiedTable().toString() + "." //$NON-NLS-1$
				+ introspectedColumn.getActualColumnName();
		field.addAnnotation(getGeneratedAnnotation(comment));
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
		imports.add(new FullyQualifiedJavaType("javax.annotation.Generated")); //$NON-NLS-1$
		String comment = "Source Table: " + introspectedTable.getFullyQualifiedTable().toString(); //$NON-NLS-1$
		innerClass.addAnnotation(getGeneratedAnnotation(comment));
	}

	private String getGeneratedAnnotation(String comment) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("@Generated("); //$NON-NLS-1$
		if (suppressAllComments) {
			buffer.append('\"');
		} else {
			buffer.append("value=\""); //$NON-NLS-1$
		}

		buffer.append(MyBatisGenerator.class.getName());
		buffer.append('\"');

		if (!suppressDate && !suppressAllComments) {
			buffer.append(", date=\""); //$NON-NLS-1$
			buffer.append(DatatypeConverter.printDateTime(Calendar.getInstance()));
			buffer.append('\"');
		}

		if (!suppressAllComments) {
			buffer.append(", comments=\""); //$NON-NLS-1$
			buffer.append(comment);
			buffer.append('\"');
		}

		buffer.append(')');
		return buffer.toString();
	}
}

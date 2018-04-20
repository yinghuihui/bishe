package com.rongdu.ow.core.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tool.util.NumberUtil;

/**
 * Created by Administrator on 2015/4/22.
 */
public class ListUtil {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, Object>> treeForExt(List<Map<String, Object>> list, Boolean leafChecked,
			Boolean rootChecked, Boolean expanded) {
		for (Map rec : list) {
			List<Map<String, Object>> children = (List<Map<String, Object>>) rec.get("children");
			if (children == null || children.size() == 0) {
				rec.put("leaf", true);
				if (leafChecked != null) {
					rec.put("checked", leafChecked);
				}
			} else {
				if (rootChecked != null) {
					rec.put("checked", rootChecked);
				}
				if (expanded != null) {
					rec.put("expanded", expanded);
				}
				treeForExt(children, leafChecked, rootChecked, expanded);
			}
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, Object>> list2Tree(List<Map<String, Object>> list, String idField,
			String parentField) {
		List<Map<String, Object>> roots = new ArrayList<Map<String, Object>>();
		Map<String, Map> indexor = new LinkedHashMap<String, Map>();
		for (Map rec : list) {
			indexor.put(rec.get(idField).toString(), rec);
		}
		for (Map rec : list) {
			rec.put(idField, rec.get(idField).toString());
			Map<String, Object> father = indexor.get(String.valueOf(rec.get(parentField)));
			if (father != null) {
				
				// 父级为选中，但是子菜单为未选中时，父级菜单改为部分选中
				Long fatherCheckd = NumberUtil.getLong(StringUtil.isNull(father.get("checked")));
				Long recCheckd = NumberUtil.getLong(StringUtil.isNull(rec.get("checked")));
				if (fatherCheckd == 1||fatherCheckd == 2) {//1 - 全选  2 - 半选
					if (recCheckd == 0||recCheckd == 2) {
						father.put("checked", 2L);
					}
				}
				
				List<Map> children = (List<Map>) father.get("children");
				if (children == null) {
					children = new ArrayList<Map>();
					father.put("children", children);
				}
				children.add(rec);
			} else {
				roots.add(rec);
			}
		}
		return roots;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> listCovertToTree(List<Map> list, String idField, String parentField) {
		List<Map> roots = new ArrayList<Map>();
		Map<String, Map<String, Object>> indexor = new LinkedHashMap<String, Map<String, Object>>();
		for (Map<String, Object> rec : list) {
			indexor.put(rec.get(idField).toString(), rec);
		}
		for (Map<String, Object> rec : list) {
			Map<String, Object> father = indexor.get(String.valueOf(rec.get(parentField)));
			if (father != null) {
				List<Map<String, Object>> children = (List<Map<String, Object>>) father.get("children");
				if (children == null) {
					children = new ArrayList<Map<String, Object>>();
					father.put("children", children);
				}
				children.add(rec);
			} else {
				roots.add(rec);
			}
		}
		return roots;
	}

	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> treeCovertToExt(List<Map<String, Object>> list, Boolean leafChecked,
			Boolean rootChecked, Boolean expanded) {
		for (Map<String, Object> rec : list) {
			List<Map<String, Object>> children = (List<Map<String, Object>>) rec.get("children");
			if (children == null || children.size() == 0) {
				rec.put("leaf", true);
				if (leafChecked != null) {
					rec.put("checked", leafChecked);
				}
			} else {
				if (rootChecked != null) {
					rec.put("checked", rootChecked);
				}
				if (expanded != null) {
					rec.put("expanded", expanded);
				}
				treeCovertToExt(children, leafChecked, rootChecked, expanded);
			}
		}
		return list;
	}

	@SuppressWarnings("rawtypes")
	public static String join(List list) {
		return join(list, ",");
	}

	@SuppressWarnings("rawtypes")
	public static String join(List list, String str) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Object o = list.get(i);
			if (i > 0)
				sb.append(str);
			sb.append(o.toString());
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static long attachCnt(List<Map<String, Object>> list, String textField, String cntField) {
		long cnt = 0;
		for (Map<String, Object> rec : list) {
			List<Map<String, Object>> children = (List<Map<String, Object>>) rec.get("children");
			if (children == null || children.size() == 0) {
				rec.put("leaf", true);
				rec.put(textField + "Cnt", rec.get(textField) + "(" + rec.get(cntField) + ")");
				cnt += Long.valueOf(rec.get(cntField).toString());
			} else {
				long c = attachCnt(children, textField, cntField);
				rec.put("expanded", true);
				rec.put(textField + "Cnt", rec.get(textField) + "(" + c + ")");
				cnt += c;
			}
		}
		return cnt;
	}

	@SuppressWarnings("rawtypes")
	public static Set<Object> collectProperty(List<Map> list, String property) {
		Set<Object> sets = new LinkedHashSet<Object>();
		for (Map map : list) {
			sets.add(map.get(property));
		}
		return sets;
	}

}

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系', '3', '1', 'social', 'system/social/index', 1, 0, 'C', '0', '0', 'system:social:list', '#', 'admin', sysdate(), '', null, '社会化关系菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系查询', @parentId, '1',  '#', '', 1, 0, 'F', '0', '0', 'system:social:query',        '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系新增', @parentId, '2',  '#', '', 1, 0, 'F', '0', '0', 'system:social:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系修改', @parentId, '3',  '#', '', 1, 0, 'F', '0', '0', 'system:social:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系删除', @parentId, '4',  '#', '', 1, 0, 'F', '0', '0', 'system:social:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, path, component, is_frame, is_cache, menu_type, visible, status, perms, icon, create_by, create_time, update_by, update_time, remark)
values('社会化关系导出', @parentId, '5',  '#', '', 1, 0, 'F', '0', '0', 'system:social:export',       '#', 'admin', sysdate(), '', null, '');
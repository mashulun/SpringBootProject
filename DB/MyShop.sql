
/*统一判断删除已有表格*/

DROP TABLE IF EXISTS `admin_role`;
DROP TABLE IF EXISTS `role_permission`;
DROP TABLE IF EXISTS `permission_menu`;
DROP TABLE IF EXISTS `permission_operate`;

DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `menu`;
DROP TABLE IF EXISTS `operate`;
DROP TABLE IF EXISTS `permission`;

/* **************************************************************************** */

/*管理员表*/
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(50) ,
  `admin_pass` varchar(255) COMMENT '这个加密之前的密码是admin',
  `admin_nickname` varchar(50),
  `admin_status` int(11) DEFAULT '1',
  PRIMARY KEY (`admin_id`)
);
/*默认数据*/
insert  into `admin` values
(1,'admin','$2a$10$3UijsDJW0JbUrtOn0OaKXO0dS8q0sONCLP8gv7n05m7UJEVzWhEk2','admin',1),
(2,'xxx','$2a$10$4NsX/6du7q0BHB1JCNnapelqX3KDuOS9.LrVrqCrqcjpNtS2vtd4a','xxx',1),
(3,'yyy','$2a$10$bQdUBB6Ug/dpUzdTmFRd/.pbkgDRc1QhDVpusCKul3vTYPQ9jP7T2','yyy',1);



/*身份角色表*/
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50),
  PRIMARY KEY (`role_id`)
);
/*默认数据*/
insert  into `role` values
(1,'系统管理员'),(2,'IT管理员'),(3,'总经理'),(4,'业务分管副总经理'),
(5,'市场经理'),(6,'销售经理'),(7,'产品经理'),(8,'人力资源经理'),
(9,'财务经理'),(10,'物流经理'),(11,'售后服务经理'),(12,'市场专员'),
(13,'销售专员'),(14,'人事专员'),(15,'产品专员'),(16,'会计'),
(17,'配送专员'),(18,'仓库管理'),(19,'客服专员');



/*管理员-角色参照表*/
CREATE TABLE `admin_role` (
  `admin_id` int(11),
  `role_id` int(11),
  KEY `role_id` (`role_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `admin_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `admin_role_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`admin_id`)
);
/*默认数据*/
insert  into `admin_role`  values
(1,1),(2,5),(2,6),(2,7),(3,15);



/*菜单表*/
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(50),
  `menu_url` varchar(255),
  `pid` int(11),
  PRIMARY KEY (`menu_id`)
);
/*默认数据*/
insert  into `menu` values
(1,'人事管理','',NULL),
(2,'客户管理','',NULL),
(3,'产品管理','',NULL),
(4,'订单管理','',NULL),
(5,'站点管理','',NULL),
(6,'系统管理','',NULL),
(7,'员工管理','/admin/admin',1),
(8,'角色管理','/role/admin',1),
(9,'客户信息','/user/admin',2),
(10,'客户订单','/userorder/admin',2),
(11,'客户预购','/cart/admin',2),
(12,'客户收藏','/favorite/admin',2),
(13,'品牌管理','/brand/admin',3),
(14,'分类管理','/category/admin',3),
(15,'属性键管理','/spuattrkey/admin',3),
(16,'属性值管理','/spuattrvalue/admin',3),
(17,'产品管理','/spu/admin',3),
(18,'订单信息','/order/admin',4),
(19,'产品报表','/spuorder/admin',4),
(20,'分类报表','/categoryorder/admin',4),
(21,'品牌报表','/brandorder/admin',4),
(22,'轮播管理','/banner/admin',5),
(23,'在线客服','/service/admin',5),
(24,'站点信息','/website/admin',5),
(25,'系统设置','/system/admin',6);



/*功能操作表*/
CREATE TABLE `operate` (
  `operate_id` int(11) NOT NULL AUTO_INCREMENT,
  `operate_name` varchar(255),
  `operate_url` varchar(255),
  PRIMARY KEY (`operate_id`));
/*默认数据*/
insert  into `operate` values
(7,'员工管理','/admin/admin'),(8,'角色管理','/role/admin'),
(9,'客户信息','/user/admin'),(10,'客户订单','/userorder/admin'),
(11,'客户预购','/cart/admin'),(12,'客户收藏','/favorite/admin'),
(13,'品牌管理','/brand/admin'),(14,'分类管理','/category/admin'),
(15,'属性键管理','/spuattrkey/admin'),(16,'属性值管理','/spuattrvalue/admin'),
(17,'产品管理','/spu/admin'),(18,'订单信息','/order/admin'),
(19,'产品报表','/spuorder/admin'),(20,'分类报表','/categoryorder/admin'),
(21,'品牌报表','/brandorder/admin'),(22,'轮播管理','/banner/admin'),
(23,'在线客服','/service/admin'),(24,'站点信息','/website/admin'),
(25,'系统设置','/system/admin'),(26,'添加员工','/admin/add'),
(27,'修改员工','/admin/update'),(28,'删除员工','/admin/delete'),
(29,'添加角色','/role/add'),(30,'修改角色','/role/update'),
(31,'删除角色','/role/delete'),(32,'添加品牌','/brand/add'),
(33,'修改品牌','/brand/update'),(34,'删除品牌','/brand/delete'),
(35,'添加分类','/category/add'),(36,'修改分类','/category/update'),
(37,'删除分类','/category/delete'),(38,'添加属性键','/spuattrkey/add'),
(39,'修改属性键','/spuattrkey/update'),(40,'删除属性键','/spuattrkey/delete'),
(41,'添加属性值','/spuattrvalue/add'),(42,'修改属性值','/spuattrvalue/update'),
(43,'删除属性值','/spuattrvalue/delete'),(44,'添加产品','/spu/add'),
(45,'修改产品','/spu/update'),(46,'删除产品','/spu/delete'),
(47,'修改订单','/order/update'),(48,'添加轮播','/banner/add'),
(49,'修改轮播','/banner/update'),(50,'删除轮播','/banner/delete'),
(51,'添加客服','/service/add'),(52,'修改客服','/service/update'),
(53,'删除客服','/service/delete'),(54,'添加员工执行','/admin/save'),
(55,'修改员工执行','/admin/alter'),(56,'添加角色执行','/role/save'),
(57,'修改角色执行','/role/alter'),(58,'添加品牌执行','/brand/save'),
(59,'修改品牌执行','/brand/alter'),(60,'添加分类执行','/category/save'),
(61,'修改分类执行','/category/alter'),(62,'添加规格执行','/spuattrkey/save'),
(63,'修改规格执行','/spuattrkey/alter'),(64,'添加参数执行','/attr/save'),
(65,'修改参数执行','/attr/alter'),(66,'添加产品执行','/spu/save'),
(67,'修改产品执行','/spu/alter'),(68,'修改订单执行','/order/alter'),
(69,'添加轮播执行','/banner/save'),(70,'修改轮播执行','/banner/alter'),
(71,'添加客服执行','/service/save'),(72,'修改客服执行','/service/alter');



/*权限表*/
CREATE TABLE `permission` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50),
  `pid` int(11),
  PRIMARY KEY (`permission_id`)
);
/*默认数据*/
insert  into `permission` values
(1,'人事管理',NULL),(2,'客户管理',NULL),
(3,'产品管理',NULL),(4,'订单管理',NULL),
(5,'站点管理',NULL),(6,'系统管理',NULL),
(7,'员工管理',1),(8,'角色管理',1),(9,'客户信息',2),(10,'客户订单',2),
(11,'客户预购',2),(12,'客户收藏',2),(13,'品牌管理',3),(14,'分类管理',3),
(15,'规格管理',3),(16,'参数管理',3),(17,'产品管理',3),(18,'订单信息',4),
(19,'产品报表',4),(20,'分类报表',4),(21,'品牌报表',4),(22,'轮播管理',5),
(23,'在线客服',5),(24,'站点信息',5),(25,'系统设置',6),(26,'添加员工',7),
(27,'修改员工',7),(28,'删除员工',7),(29,'添加角色',8),(30,'修改角色',8),
(31,'删除角色',8),(32,'添加品牌',13),(33,'修改品牌',13),(34,'删除品牌',13),
(35,'添加分类',14),(36,'修改分类',14),(37,'删除分类',14),(38,'添加规格',15),
(39,'修改规格',15),(40,'删除规格',15),(41,'添加参数',16),(42,'修改参数',16),
(43,'删除参数',16),(44,'添加产品',17),(45,'修改产品',17),(46,'删除产品',17),
(47,'修改订单',18),(48,'添加轮播',22),(49,'修改轮播',22),(50,'删除轮播',22),
(51,'添加客服',23),(52,'修改客服',23),(53,'删除客服',23);



/*权限-菜单参照表*/
CREATE TABLE `permission_menu` (
  `permission_id` int(11),
  `menu_id` int(11),
  KEY `permission_id` (`permission_id`),
  KEY `menu_id` (`menu_id`),
  CONSTRAINT `permission_menu_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `permission_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`)
);
/*默认数据*/
insert  into `permission_menu` values
(1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),
(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17),(18,18),
(19,19),(20,20),(21,21),(22,22),(23,23),(24,24),(25,25);



/*身份-权限参照表*/
CREATE TABLE `role_permission` (
  `role_id` int(11),
  `permission_id` int(11),
  KEY `role_id` (`role_id`),
  KEY `permission_id` (`permission_id`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
);
/*默认数据*/
insert  into `role_permission` values
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),
(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),
(1,29),(1,30),(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),
(1,38),(1,39),(1,40),(1,41),(1,42),(1,43),(1,44),(1,45),(1,46),
(1,47),(1,48),(1,49),(1,50),(1,51),(1,52),(1,53),(15,3),(15,16),
(15,17),(15,41),(15,42),(15,43),(15,44),(15,45),(15,46);



/*权限-功能参照表*/
CREATE TABLE `permission_operate` (
  `permission_id` int(11),
  `operate_id` int(11),
  KEY `permission_id` (`permission_id`),
  KEY `operate_id` (`operate_id`),
  CONSTRAINT `permission_operate_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`),
  CONSTRAINT `permission_operate_ibfk_2` FOREIGN KEY (`operate_id`) REFERENCES `operate` (`operate_id`)
);
/*默认数据*/
insert  into `permission_operate` values
(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),
(16,16),(17,17),(18,18),(19,19),(20,20),(21,21),(22,22),(23,23),
(24,24),(25,25),(26,26),(27,27),(28,28),(29,29),(30,30),(31,31),
(32,32),(33,33),(34,34),(35,35),(36,36),(37,37),(38,38),(39,39),(40,40),
(41,41),(42,42),(43,43),(44,44),(45,45),(46,46),(47,47),(48,48),(49,49),
(50,50),(51,51),(52,52),(53,53),(26,54),(27,55),(29,56),(30,57),(32,58),
(33,59),(35,60),(36,61),(38,62),(39,63),(41,64),(42,65),(44,66),(45,67),
(47,68),(48,69),(49,70),(51,71),(52,72);




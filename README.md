# MyShop - 后台管理系统

![License](https://img.shields.io/github/license/mashulun/SpringBootProject)
![CI](https://github.com/mashulun/SpringBootProject/actions/workflows/ci.yml/badge.svg)
![Issues](https://img.shields.io/github/issues/mashulun/SpringBootProject)
![Stars](https://img.shields.io/github/stars/mashulun/SpringBootProject?style=social)

基于 Spring Boot 2.1 + MyBatis + Spring Security + Thymeleaf 的后台管理系统，采用 RBAC（基于角色的访问控制）权限模型，实现了从菜单级别到操作级别的细粒度动态权限管理。

## 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 2.1.12.RELEASE | 基础框架 |
| MyBatis | 2.1.3 (starter) | ORM 持久层 |
| Spring Security | 5.1.x (随 Boot 管理) | 认证与授权 |
| Thymeleaf | 3.0.x (随 Boot 管理) | 服务端模板引擎 |
| MySQL | 8.0.31 (connector) | 关系型数据库 |
| Druid | 1.1.23 | 数据库连接池 |
| Lombok | 1.18.28 | 代码简化 |

## 环境要求

- **JDK**: 8+
- **Maven**: 3.6+
- **MySQL**: 8.0+
- **IDE**: IntelliJ IDEA（推荐）或 Eclipse

## 快速启动

```bash
# 1. 创建数据库
mysql -u root -p < DB/MyShop.sql

# 2. 修改数据库配置（按需）
# 编辑 src/main/resources/application.properties
# 设置环境变量 DB_PASSWORD 或直接修改配置中的密码

# 3. 启动项目
mvn spring-boot:run

# 4. 访问
# http://localhost:9527
# 默认账号: admin / admin
```

## 项目结构

```plaintext
src/main/java/com/study/myshop/
├── Application.java              # 启动类
├── controller/                   # 控制层
│   ├── AdminController.java      # 员工管理（CRUD + 分页检索）
│   └── IndexController.java      # 首页与菜单导航
├── service/                      # 业务接口层
│   ├── IAdminService.java
│   ├── IIndex.java
│   ├── IRoleService.java
│   └── impl/                     # 业务实现层
│       ├── AdminServiceImpl.java
│       ├── AdminDetailServiceImpl.java  # UserDetailsService 实现
│       ├── IndexServiceImpl.java
│       └── RoleServiceImpl.java
├── repository/                   # 数据访问层 (MyBatis Mapper)
│   ├── AdminMapper.java
│   ├── MenuMapper.java
│   └── RoleMapper.java
├── po/                           # 持久化对象（对应数据库表）
│   ├── AdminPO.java              # 管理员
│   ├── RolePO.java               # 角色
│   ├── MenuPO.java               # 菜单
│   ├── PermissionPO.java         # 权限
│   ├── OperatePO.java            # 操作
│   └── ...关联表 PO（5 张）
├── vo/                           # 视图对象（表单/展示）
│   ├── AddAdminVo.java
│   ├── AdminVo.java
│   ├── AdminInfoVo.java
│   └── MenuVo.java
├── security/                     # Spring Security 模块
│   ├── SecurityConfig.java       # 安全配置（入口）
│   ├── PermissionInterceptor.java # 动态权限元数据源
│   ├── PermissionValid.java       # 访问决策管理器
│   └── handler/                   # 认证/授权事件处理器
└── exception/                     # 异常处理
    └── ExceptionCenter.java       # 全局异常处理

src/main/resources/
├── application.properties         # 应用配置
├── mapper/                        # MyBatis XML 映射文件
├── templates/                     # Thymeleaf 页面模板
│   ├── admin/                     # 员工管理页面
│   ├── index/                     # 首页（header + sidebar）
│   └── error/                     # 错误页面
└── static/assets/                 # 前端静态资源 (Dashgum 模板)

DB/
└── MyShop.sql                     # 数据库建表脚本 + 初始数据
```

## RBAC 权限模型

```
admin (管理员) ──M:N── role (角色)
                           │
                      M:N  │
                           ▼
                    permission (权限) ──M:N── menu (菜单)
                           │
                      M:N  │
                           ▼
                      operate (操作)
```

| 数据库表 | 说明 |
|----------|------|
| `admin` | 管理员表（用户名、BCrypt 加密密码、昵称、状态） |
| `role` | 角色表（系统管理员、IT管理员、销售经理等 19 种预置角色） |
| `admin_role` | 管理员-角色多对多关联 |
| `permission` | 权限表 |
| `role_permission` | 角色-权限多对多关联 |
| `menu` | 菜单表（支持两级树形结构，pid 自关联） |
| `permission_menu` | 权限-菜单多对多关联 |
| `operate` | 操作表（按钮级权限） |
| `permission_operate` | 权限-操作多对多关联 |

### 权限控制流程

1. 用户登录 → Spring Security 调用 `AdminDetailServiceImpl` 加载用户信息及角色
2. 请求到达 → `PermissionInterceptor` 根据请求 URL 查询数据库获取所需角色
3. 访问决策 → `PermissionValid` 对比当前用户角色与所需角色，决定放行或拒绝
4. 菜单渲染 → 根据用户角色动态生成侧边栏菜单（Thymeleaf `th:each` 遍历）

## 已实现功能

- [x] 用户认证（Spring Security 表单登录 + BCrypt 密码加密）
- [x] 动态 URL 级别权限控制（基于数据库配置）
- [x] 员工管理 CRUD（增删改查）
- [x] 分页查询 + 关键字搜索
- [x] 基于角色的动态菜单系统（两级树形）
- [x] 登录/登出/权限不足事件处理
- [ ] 客户管理模块
- [ ] 产品管理模块
- [ ] 订单管理模块
- [ ] 站点管理模块

## 配置说明

### 数据库连接

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/myshop?serverTimezone=Asia/Shanghai&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD:msl6159@}
```

数据库密码支持通过环境变量 `DB_PASSWORD` 覆盖，默认值为 `msl6159@`。

### Druid 连接池

`application.properties` 中提供了完整的 Druid 连接池参考配置（已注释），包含初始连接数、最大活跃连接、空闲检测、SQL 防火墙等参数。生产环境建议取消注释并根据实际情况调整。

## 前端模板

项目使用 [Dashgum](https://bootdey.com/) 免费 Bootstrap 后台管理模板，包含响应式布局、Font Awesome 图标、数据表格样式等。

## 提交规范

遵循 [Conventional Commits](https://www.conventionalcommits.org/) 规范：

```text
<type>(<scope>): <description>

[optional body]
```

### 类型

| 类型 | 说明 | 示例 |
|------|------|------|
| `feat` | 新功能 | `feat(admin): add batch delete function` |
| `fix` | 修复缺陷 | `fix(mapper): correct role permission SQL join` |
| `docs` | 文档更新 | `docs: add RBAC model explanation` |
| `style` | 代码格式调整 | `style: format controller imports` |
| `refactor` | 重构（不改变功能） | `refactor(security): extract password encoder to bean` |
| `test` | 测试相关 | `test(service): add admin service unit tests` |
| `chore` | 构建/配置变更 | `chore: update druid connection pool config` |
| `perf` | 性能优化 | `perf(mapper): add index for admin_role query` |

### 规则

1. 标题使用祈使句、首字母小写、不加句号，长度不超过 50 字符
2. 正文用 `-` 列出变更点，每行一个独立变更
3. 对特定模块的修改添加 scope 标识，如 `feat(security): ...`

## Git 分支工作流

### 日常开发流程

```bash
# 1. 从主干切出功能分支
git checkout main
git pull
git checkout -b feat/模块名-功能描述

# 2. 开发并提交（可多次提交）
git add <修改的文件>
git commit -m "feat(admin): add export function"

# 3. 切回主干，快进合并（不产生额外 merge commit）
git checkout main
git merge --ff-only feat/模块名-功能描述

# 4. 删除已合并的分支
git branch -d feat/模块名-功能描述
```

### 修复缺陷

```bash
# 切出修复分支
git checkout -b fix/问题描述

# 修复并提交
git add <修改的文件>
git commit -m "fix(security): disable CSRF for API endpoints"

# 合并到主干
git checkout main
git merge --ff-only fix/问题描述

# 删除分支
git branch -d fix/问题描述
```

### 分支命名约定

| 前缀 | 用途 | 示例 |
|------|------|------|
| `feat/` | 新功能开发 | `feat/order-management` |
| `fix/` | 缺陷修复 | `fix/login-redirect-loop` |
| `refactor/` | 代码重构 | `refactor/menu-tree-builder` |
| `docs/` | 文档变更 | `docs/update-readme` |
| `test/` | 测试相关 | `test/admin-service-coverage` |

### 注意事项

- 合并使用 `--ff-only` 快进模式，保持 git 历史线性整洁
- 功能分支完成后再合并，避免半成品代码进入主干
- 推送前确保本地主干已更新：`git pull --ff-only`

```bash
# 推送到远程
git push origin main

# 如果分支也需要推送到远程协作
git push -u origin feat/模块名-功能描述
```

## 许可证

MIT License

# Changelog

## [Unreleased]

### 新增
- 完善仓库治理：添加 `.gitignore`、`LICENSE`、`CHANGELOG.md`
- 添加 GitHub Actions CI 配置
- 添加 Issue 模板（Bug 报告 + 功能建议）
- 添加 PR 模板
- 添加 Dependabot 依赖安全更新配置
- 配置分支保护规则

## [1.0-SNAPSHOT] - 2026-07-20

### 新增
- 基于 Spring Boot 2.1 + MyBatis + Spring Security + Thymeleaf 的后台管理系统
- RBAC 权限模型（管理员-角色-权限-菜单-操作）
- Spring Security 表单登录 + BCrypt 密码加密
- 动态 URL 级别权限控制（基于数据库配置）
- 员工管理 CRUD（增删改查）
- 分页查询 + 关键字搜索
- 基于角色的动态菜单系统（两级树形）
- 登录/登出/权限不足事件处理
- 全局异常处理
- 项目 README（技术栈、RBAC 权限模型说明及快速启动指南）

### 修复
- 修复 SQL Bug、分页参数、安全问题及代码分层优化
- 修复懒加载 bug 和 column 对应错误

### 改进
- 代码规范扫描修复
- 优化方法 index
- 去除多余的 import

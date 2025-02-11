# Strong-Admin

## 项目简介
**Strong-Admin** 是一个企业级标准管理后台的后端项目，提供高效、安全、可扩展的 API 服务，涵盖用户管理、权限控制、接口管理、缓存、消息、日志、数据导入导出、文件上传下载等核心功能。

## 功能概述
- **用户管理**：支持用户注册、登录、权限管理、角色分配。
- **权限控制**：基于 RBAC（角色访问控制）的权限管理机制。
- **接口管理**：提供统一的 API 访问入口，支持接口鉴权。
- **缓存机制**：集成 Redis 提供高效缓存，提高系统性能。
- **消息队列**：支持 RocketMQ 进行异步消息处理。
- **日志管理**：记录用户操作日志，支持 ELK 日志分析。
- **数据导入导出**：支持 Excel、PDF 等格式的数据导入与导出。
- **文件上传下载**：支持OSS进行文件管理。
- **API 文档**：集成 Swagger3 进行 API 文档管理。

## 技术栈
- **后端框架**：Spring Boot 3.x
- **数据库**：MySQL
- **ORM**：MyBatis Plus
- **安全认证**：Spring Security + JWT
- **缓存**：Redis
- **消息队列**：RocketMQ
- **日志管理**：Logback + ELK
- **文档管理**：Swagger3
- **任务调度**：XXL-Job

### 2. 克隆项目
```bash
git clone https://github.com/charlieqiang/strong-admin.git
cd strong-admin
```

### 3. 配置数据库
修改 `application.yml`，填写数据库连接信息：
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/strong_admin_db?useSSL=false&serverTimezone=UTC
    username: root
    password: password
```

### 4. 启动项目
```bash
mvn spring-boot:run
```

## API 文档
启动项目后，访问 [Swagger API 文档](http://localhost:8080/swagger-ui/index.html)。

## 贡献指南
欢迎贡献代码，请遵循以下流程：
1. Fork 代码仓库
2. 创建 feature 分支 (`git checkout -b feature-xxx`)
3. 提交代码 (`git commit -m 'feat: xxx'`)
4. 推送分支 (`git push origin feature-xxx`)
5. 提交 PR，等待合并

## 许可证
本项目采用 [MIT License](LICENSE)。

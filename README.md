# NANA

NANA 后端 REST API（Spring Boot 4.1 + Java 21 + Maven）。

## 技术栈

- Java 21
- Spring Boot 4.1
- Maven

## 分层结构

- `controller` — REST 接口层
- `service` — 业务逻辑层
- `repository` — 数据访问层（当前为内存实现，后续可替换为 JPA / MyBatis）
- `model` — 实体 / DTO
- `common` — 统一响应体、错误码、全局异常处理

## 快速开始

```bash
mvn spring-boot:run
```

启动后访问：<http://localhost:8080/api/users>

## 示例接口

| 方法   | 路径                 | 说明         |
|--------|----------------------|--------------|
| GET    | /api/users           | 列出所有用户 |
| GET    | /api/users/{id}      | 查询单个用户 |
| POST   | /api/users           | 创建用户     |
| DELETE | /api/users/{id}      | 删除用户     |

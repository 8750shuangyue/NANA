package com.nana.model;

import jakarta.validation.constraints.NotBlank;

/**
 * 示例实体（内存演示用，暂无持久化）。
 */
public record User(
        Long id,
        @NotBlank(message = "用户名不能为空") String name,
        @NotBlank(message = "邮箱不能为空") String email) {
}

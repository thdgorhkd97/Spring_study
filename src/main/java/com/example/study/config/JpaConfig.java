package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정파일입니다
@EnableJpaAuditing // 감시를 활성화 시키겠다
public class JpaConfig {
}

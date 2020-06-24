package group.online_exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession     //启用redis session存储
@EnableCaching              //启用缓存
@EnableAsync
@SpringBootApplication
public class OnlineExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineExamApplication.class, args);
    }

}

package org.os.GraduationProject.util;

import org.os.GraduationProject.commons.MessageRedis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CleanCloudPic {
    private final RedisTemplate<Object, Object> jsonRedisTemplate;

    public CleanCloudPic(RedisTemplate<Object, Object> jsonRedisTemplate) {
        this.jsonRedisTemplate = jsonRedisTemplate;
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void cleanCloudPic() {
        Set<Object> set = jsonRedisTemplate.opsForSet().difference(MessageRedis.SETMEAL_PIC_RESOURCES,
                MessageRedis.SETMEAL_PIC_DB_RESOURCES);
        if (set != null) {
            for(Object picName : set){
                FileUploadUtil.deleteFile((String)picName);
                jsonRedisTemplate.opsForSet().remove(MessageRedis.SETMEAL_PIC_RESOURCES, picName);
            }
        }
    }
}

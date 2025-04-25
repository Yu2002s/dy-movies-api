package xyz.jdynb.dymovies.config;

import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class QuartzConfig {

    public void setSchedulerFactoryBeanProperties(SchedulerFactoryBean schedulerFactoryBean, DataSource dataSource, JobFactory jobFactory) throws IOException {
        schedulerFactoryBean.setJobFactory(jobFactory);
        //设置数据源
        schedulerFactoryBean.setDataSource(dataSource);
    }

    /*@Bean
    public JobDetail collectVodJobDetail() {
        return JobBuilder.newJob(CollectVodJob.class).withIdentity("collect")
                .storeDurably().build();
    }

    @Bean
    public Trigger collectVodTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10).repeatForever();
        return TriggerBuilder.newTrigger().forJob(collectVodJobDetail())
                .withIdentity("collectTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }*/

    /**
     * 自定义JobFactory，以便Job类里可以使用Spring类注入
     */
    @Bean
    public JobFactory jobFactory() {
        return new QuartzSpringBeanJobFactory();
    }

    private static class QuartzSpringBeanJobFactory extends SpringBeanJobFactory {

        private AutowireCapableBeanFactory beanFactory;

        @Override
        public void setApplicationContext(ApplicationContext context) {
            beanFactory = context.getAutowireCapableBeanFactory();
        }

        @Override
        @NonNull
        protected Object createJobInstance(@NonNull TriggerFiredBundle bundle) throws Exception {
            final Object job = super.createJobInstance(bundle);
            beanFactory.autowireBean(job);
            return job;
        }
    }

}

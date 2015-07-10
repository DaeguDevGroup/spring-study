package kr.dev.study.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.dev.study.bbs.service"}
//, includeFilters = {
//@ComponentScan.Filter(value = Service.class, type = FilterType.ANNOTATION)
//, @ComponentScan.Filter(value = Repository.class, type = FilterType.ANNOTATION)
//}
//, excludeFilters = {
//@ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION)
//}
)
public class RootAppConfig {

}

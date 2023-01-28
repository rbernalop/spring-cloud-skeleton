package java.org.rbernalop.shared.application;

import java.lang.annotation.*;

@org.springframework.stereotype.Service
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Service {
}
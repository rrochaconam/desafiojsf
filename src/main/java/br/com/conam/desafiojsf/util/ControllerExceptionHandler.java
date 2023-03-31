package br.com.conam.desafiojsf.util;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
import javax.interceptor.InterceptorBinding;


@InterceptorBinding
@Inherited
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface ControllerExceptionHandler {
	
}
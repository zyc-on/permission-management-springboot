//package com.sie.demo.validator;
//
//import javax.validation.Constraint;
//import javax.validation.Payload;
//import java.lang.annotation.Retention;
//import java.lang.annotation.Target;
//
//import static java.lang.annotation.ElementType.*;
//import static java.lang.annotation.RetentionPolicy.RUNTIME;
//
//@Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE })
//@Retention(RUNTIME)
//@Constraint(validatedBy = CheckEnumValidator.class)
//public @interface CheckEnum {
//    String message() default "枚举验证失败";
//
//    Class<?>[] groups() default { };
//
//    Class<? extends Payload>[] payload() default { };
//
//
//
//
//}

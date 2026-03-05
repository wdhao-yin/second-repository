package com.idwdhao.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect//表示这个类是aop类,切面类
public class  TimeAspect{

    //execution(* com.idwdhao.service..*.*(..))表达式理解，第一段是返回值类型（*），第二段是包路径（com.idwdhao.service），
    // 然后是所有子包（..）
    // 子包下的所有类（*），类中所有方法名（*），然后是方法的所有参数（（..））
    //公式 execution(返回值 包路径.类名.方法名(参数))。
    //注意：在返回值之前可以加入访问修饰符，在方法名（参数）之后可以加入抛出异常（这个异常是方法声明上抛出的异常）。这两个都可以省略。
    //注意 .. 表示 “层级任意”
    @Around("execution(* com.idwdhao.service..*.*(..))")//环绕通知
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //1.记录一个方法的开始时间
        long startTime = System.currentTimeMillis();

        //2.调用这个方法
        Object res=joinPoint.proceed();

        //3.记录一个方法的结束时间
        long endTime = System.currentTimeMillis();

        //4.日志输出
        //获得对应方法的签名再得到耗时。
        log.info(joinPoint.getSignature()+"方法执行耗时：{}ms",endTime-startTime);

        return res;//注意调用了要返回回去，类型要保持一致。
    }//注意 @Around通知的返回值必须设置为Object

    //切入点表达式也可以抽取出来,建一个空方法，打上注解@Pointcut，并在其中写上要抽取的表达式
    //然后在需要调用这个表达式的地方直接写这个方法的名字就行
    //方法的权限修饰看需求来，如果其他类想用就改成public
    @Pointcut("execution(* com.idwdhao.service.UserService.getUsers())")
    private void pt(){}

    @Before("pt()")//之前通知
    public void AopTest1(){
        log.info("之前通知测试...");
    }

    @After("pt()")//之后通知
    public void AopTest2(){
        log.info("之后通知测试...");
    }

    @AfterReturning("pt()")//返回后通知
    public void AopTest3(){
        log.info("返回后通知测试...");
    }

    @AfterThrowing("pt()")//异常抛出后通知
    public void AopTest4(){
        log.info("异常抛出后通知测试...");
    }

    //关于切面类的执行顺序，一般是按切面类名来排，如果要指定排名方式
    //可以使用@Order注解。
    //在写Pointcut的时候，可以用多个execution表达式，并用||链接或者用&&链接，！链接也行
    //也可以通过@annotation()，加自定义注解的方式来切入。

    @After("@annotation(com.idwdhao.aop.Mylog)")//返回后通知
    public void AopTest5(){
        log.info("使用@annotation()的方式，返回后通知测试...");
    }
    //@Around只能使用ProceedingJoinPoint而除此之外的只能使用JoinPoint，他是ProceedingJoinPoint的父类
    //joinPoint.getTarget().getClass().getName();可以获取对应方法类名
    //joinPoint.getSignature().getName();获取对应方法名
    //joinPoint.getArgs();获取对应方法传入的参数列表
    //joinPoint.proceed();放行对应方法，可以获取方法返回值

}

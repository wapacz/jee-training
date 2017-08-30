package pl.training.bank.cdi.interceptor;

import lombok.Setter;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.logging.Level;
import java.util.logging.Logger;

@Setter
@Measure
@Interceptor
public class Stopper {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object measure(InvocationContext invocationContext) throws Exception {
        long startTime = System.nanoTime();
        Object result = invocationContext.proceed();
        long totalTime = System.nanoTime() - startTime;
        String methodName = invocationContext.getMethod().getName();
        logger.log(Level.INFO, "Execution time for method " + methodName + ": " + totalTime + " ns");
        return result;
    }

}

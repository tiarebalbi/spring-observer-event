package com.tiarebalbi.events.initialization;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodParameter;
import org.springframework.util.ReflectionUtils;

import com.tiarebalbi.events.annotations.Observer;
import com.tiarebalbi.events.process.EventRegistry;

public class InitializationBeanProcess implements BeanPostProcessor {
	
	@Autowired
	private EventRegistry eventRegistry;

	@Override
	public Object postProcessBeforeInitialization(final Object bean, String beanName) throws BeansException {
		ReflectionUtils.doWithMethods(bean.getClass(), new ReflectionUtils.MethodCallback() {
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {

				int observesAnnotationsFound = 0;
				Class<?> annotatedParameterType = null;

				Class<?>[] parameterTypes = method.getParameterTypes();
				for (int i = 0; i < parameterTypes.length; i++) {
					Annotation[] paramAnnotations = new MethodParameter( method, i).getParameterAnnotations();
					for (int c = 0; c < paramAnnotations.length; c++) {
						if (paramAnnotations[c].annotationType().equals(Observer.class)) {
							observesAnnotationsFound++;
							annotatedParameterType = parameterTypes[i];
						}
					}
				}

				if (observesAnnotationsFound > 1) {
					throw new IllegalStateException("Você pode ter apenas um parâmetro anotado com Observer, mas foi encontrado outro " + observesAnnotationsFound);
				}

				if (observesAnnotationsFound == 1) {
					if (method.isVarArgs()) {
						throw new IllegalStateException( "Observer method was declared to take a variable number of arguments");
					}
					if (!method.getReturnType().getName()
							.equals("void")) {
						throw new IllegalStateException( "Observer method " + method + " must return void");
					}
					if (parameterTypes.length > 1) {
						throw new IllegalStateException( "Observer method must have exactly one parameter, but found " + parameterTypes.length);
					}

					 eventRegistry.registerEvent(bean, method, annotatedParameterType);
				}
			}
		}, ReflectionUtils.USER_DECLARED_METHODS);

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

}

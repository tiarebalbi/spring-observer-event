package com.tiarebalbi.events.process;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author Tiarê Balbi Bonamini
 * @since 1.0 
 *
 */
@Service
@Scope("singleton")
public class EventRegistry implements Event<Object> {
	
	@Autowired
	private Executor executor;
	
	private List<DefaultEvent> events = new ArrayList<DefaultEvent>();

    @Override
    public void fire(final Object event) {
            for (final DefaultEvent e : events) {
                    if (!e.isSupported(event.getClass())) {
                            continue;
                    }
                    execute(event, e);
            }

    }

    private void execute(final Object event, final DefaultEvent listener) {
            executor.execute(new Runnable() {
                    public void run() {
                            listener.doFire(event);
                    }
            });
    }

    public void registerEvent(Object bean, Method method, Class<?> parameterType) {
            events.add(new DefaultEvent(bean, method, parameterType));
    }

    private class DefaultEvent {
            protected final Class<?> parameterType;
            private final Method method;
            private final Object bean;

            private DefaultEvent(Object bean, Method method, Class<?> parameterType) {
                    this.bean = bean;
                    this.method = method;
                    this.parameterType = parameterType;
            }

            private boolean isSupported(Class<?> eventType) {
                    return parameterType.isAssignableFrom(eventType);
            }

            void doFire(Object event) {
                    try {
                            method.invoke(bean, event);
                    } catch (Throwable e) {
                            throw new RuntimeException("Ocorreu uma falha no momento da execução" + event.getClass().getName(), e);
                    }
            }

    }

}
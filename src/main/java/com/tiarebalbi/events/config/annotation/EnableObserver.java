/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tiarebalbi.events.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import com.tiarebalbi.events.config.DelegatingObserverConfiguration;
import com.tiarebalbi.events.enums.ExecutionType;

/**
 * Add this annotation to an {@code @Configuration} class to have the Observer Event loaded.
 *
 * <pre class="code">
 * &#064;Configuration
 * &#064;EnableObserver
 * public class MyContextConfiguration {
 *
 * }
 * </pre>
 *
 * @author Tiarê Balbi Bonamini
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(DelegatingObserverConfiguration.class)
public @interface EnableObserver {
	
	ExecutionType execution() default ExecutionType.ASYNC;

}

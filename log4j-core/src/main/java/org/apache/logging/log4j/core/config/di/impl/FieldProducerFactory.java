/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache license, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the license for the specific language governing permissions and
 * limitations under the license.
 */

package org.apache.logging.log4j.core.config.di.impl;

import org.apache.logging.log4j.core.config.di.Bean;
import org.apache.logging.log4j.core.config.di.BeanManager;
import org.apache.logging.log4j.core.config.di.InjectionPoint;
import org.apache.logging.log4j.core.config.di.Producer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

class FieldProducerFactory extends AbstractProducerFactory {
    private final BeanManager beanManager;

    FieldProducerFactory(final BeanManager beanManager, final Bean<?> declaringBean,
                         final Field producerField, final Method disposerMethod,
                         final Collection<InjectionPoint> disposerInjectionPoints) {
        super(declaringBean, producerField, disposerMethod, disposerInjectionPoints);
        this.beanManager = beanManager;
    }

    @Override
    public <T> Producer<T> createProducer(final Bean<T> bean) {
        final Field field = (Field) producerMember;
        return new FieldProducer<>(beanManager, declaringBean, field, disposerMethod, disposerInjectionPoints);
    }
}
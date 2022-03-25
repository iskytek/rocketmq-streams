package org.apache.rocketmq.streams.topology.real;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.rocketmq.streams.running.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SinkFactory<K, V, OK, OV> implements RealProcessorFactory<K, V, OK, OV> {
    private final String name;
    private final List<RealProcessorFactory<K, V, OK, OV>> children = new ArrayList<>();
    private final Supplier<? extends Processor<K, V, OK, OV>> supplier;

    public SinkFactory(String name, Supplier<? extends Processor<K, V, OK, OV>> supplier) {
        this.name = name;
        this.supplier = supplier;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public Processor<K, V, OK, OV> build() {
        return supplier.get();
    }

    @Override
    public void addChild(RealProcessorFactory<K, V, OK, OV> factory) {
        this.children.add(factory);
    }

    @Override
    public List<RealProcessorFactory<K, V, OK, OV>> getChildren() {
        return this.children;
    }
}

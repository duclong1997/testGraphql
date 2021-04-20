package com.demo.testGraphql.publisher.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.FluxSink;

@Component
@RequiredArgsConstructor
@Slf4j
public class EntityPublisherImpl<T> {

    protected final FluxProcessor<T, T> processor;
    protected final FluxSink<T> sink;

    public EntityPublisherImpl() {
        this.processor = DirectProcessor.<T>create().serialize();
        this.sink = processor.sink();
    }
}

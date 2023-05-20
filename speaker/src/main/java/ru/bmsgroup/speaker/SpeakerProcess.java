package ru.bmsgroup.speaker;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;

public class SpeakerProcess implements ProcessorSupplier<String, String, String, String> {
    @Override
    public Processor<String, String, String, String> get() {
       return new Processor<String,String,String,String>() {
            @Override
            public void process(Record<String, String> record) {

            }
        };
    }
}

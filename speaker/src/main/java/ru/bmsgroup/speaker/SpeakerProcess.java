package ru.bmsgroup.speaker;

import lombok.extern.java.Log;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorSupplier;
import org.apache.kafka.streams.processor.api.Record;
@Log
public class SpeakerProcess implements ProcessorSupplier<String, String, String, String> {
    @Override
    public Processor<String, String, String, String> get() {
       return new Processor<String,String,String,String>() {
            @Override
            public void process(Record<String, String> record) {
                log.info("record key: "+ record.key());
                log.info("record value: "+ record.value());
            }
        };
    }
}

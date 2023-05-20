package ru.bmsgroup.speaker;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafkaStreams
public class SpeakerKafkaConfiguration {
    @Bean
    public NewTopic speakerTube() {
        return TopicBuilder.name("SPEAKER_TUBE").build();
    }

    @Bean
    public SpeakerProcess speakerProcess() {
        return new SpeakerProcess();
    }

    @Bean
    public KStream<String, String> speakerStream(StreamsBuilder builder) {
        KStream<String, String> speakerTube = builder.stream("SPEAKER_TUBE", Consumed.with(Serdes.String(), Serdes.String()));
        speakerTube
                .process(speakerProcess())
                .to("SPEAKER_PROCESS",Produced.with(Serdes.String(), Serdes.String()));
        return speakerTube;
    }

    @Bean
    public GlobalKTable<String, String> speakerTable(StreamsBuilder builder) {
        return builder.globalTable("SPEAKER_PROCESS", Consumed.with(Serdes.String(), Serdes.String()),
                Materialized.<String, String, KeyValueStore<Bytes, byte[]>>as("SPEAKER_STORE")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(Serdes.String()));
    }
}

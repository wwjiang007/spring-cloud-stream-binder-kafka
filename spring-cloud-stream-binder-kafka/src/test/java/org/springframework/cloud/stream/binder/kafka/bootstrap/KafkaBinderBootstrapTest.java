/*
 * Copyright 2017 the original author or authors.
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

package org.springframework.cloud.stream.binder.kafka.bootstrap;

import org.junit.ClassRule;
import org.junit.Test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.test.rule.KafkaEmbedded;

/**
 * @author Marius Bogoevici
 */
public class KafkaBinderBootstrapTest {

	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, 10);

	@Test
	public void testKafkaBinderConfiguration() throws Exception {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SimpleApplication.class)
				.web(false)
				.run("--spring.cloud.stream.kafka.binder.brokers=" + embeddedKafka.getBrokersAsString(),
					"--spring.cloud.stream.kafka.binder.zkNodes=" + embeddedKafka.getZookeeperConnectionString());
		applicationContext.close();
	}

	@SpringBootApplication
	static class SimpleApplication {
	}

}

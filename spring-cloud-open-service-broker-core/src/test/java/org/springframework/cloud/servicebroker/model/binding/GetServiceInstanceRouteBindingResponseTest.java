/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.servicebroker.model.binding;

import java.util.HashMap;
import java.util.Map;

import com.jayway.jsonpath.DocumentContext;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import org.springframework.cloud.servicebroker.JsonUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.springframework.cloud.servicebroker.JsonPathAssert.assertThat;

class GetServiceInstanceRouteBindingResponseTest {

	@Test
	void responseWithDefaultsIsBuilt() {
		GetServiceInstanceRouteBindingResponse response = GetServiceInstanceRouteBindingResponse.builder()
				.build();

		assertThat(response.getParameters()).hasSize(0);
		assertThat(response.getMetadata()).isNull();
		assertThat(response.getRouteServiceUrl()).isNull();

		DocumentContext json = JsonUtils.toJsonPath(response);

		assertThat(json).hasNoPath("$.parameters");
		assertThat(json).hasNoPath("$.metadata");
		assertThat(json).hasNoPath("$.route_service_url");
	}

	@Test
	void responseWithValuesIsBuilt() {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("field4", "value4");
		parameters.put("field5", "value5");

		GetServiceInstanceRouteBindingResponse response = GetServiceInstanceRouteBindingResponse.builder()
				.parameters("field1", "value1")
				.parameters("field2", 2)
				.parameters("field3", true)
				.parameters(parameters)
				.metadata(BindingMetadata.builder()
						.expiresAt("2019-12-31T23:59:59.0Z")
						.build())
				.routeServiceUrl("https://routes.app.local")
				.build();

		assertThat(response.getParameters()).hasSize(5);
		assertThat(response.getParameters().get("field1")).isEqualTo("value1");
		assertThat(response.getParameters().get("field2")).isEqualTo(2);
		assertThat(response.getParameters().get("field3")).isEqualTo(true);
		assertThat(response.getParameters().get("field4")).isEqualTo("value4");
		assertThat(response.getParameters().get("field5")).isEqualTo("value5");

		assertThat(response.getRouteServiceUrl()).isEqualTo("https://routes.app.local");

		DocumentContext json = JsonUtils.toJsonPath(response);

		assertThat(json).hasPath("$.parameters.field1").isEqualTo("value1");
		assertThat(json).hasPath("$.parameters.field2").isEqualTo(2);
		assertThat(json).hasPath("$.parameters.field3").isEqualTo(true);
		assertThat(json).hasPath("$.parameters.field4").isEqualTo("value4");
		assertThat(json).hasPath("$.parameters.field5").isEqualTo("value5");

		assertThat(json).hasPath("$.metadata.expires_at").isEqualTo("2019-12-31T23:59:59.0Z");

		assertThat(json).hasPath("$.route_service_url").isEqualTo("https://routes.app.local");
	}

	@Test
	void responseWithValuesIsDeserialized() {
		GetServiceInstanceRouteBindingResponse response = JsonUtils.readTestDataFile(
				"getRouteBindingResponse.json",
				GetServiceInstanceRouteBindingResponse.class);

		assertThat(response.getRouteServiceUrl()).isEqualTo("https://route.local");
		assertThat(response.getParameters())
				.hasSize(2)
				.containsOnly(entry("param1", "param-a"), entry("param2", "param-b"));
		assertThat(response.getMetadata().getExpiresAt()).isEqualTo("2019-12-31T23:59:59.0Z");
	}

	@Test
	void equalsAndHashCode() {
		EqualsVerifier
				.forClass(GetServiceInstanceRouteBindingResponse.class)
				.withRedefinedSuperclass()
				.verify();
	}

}

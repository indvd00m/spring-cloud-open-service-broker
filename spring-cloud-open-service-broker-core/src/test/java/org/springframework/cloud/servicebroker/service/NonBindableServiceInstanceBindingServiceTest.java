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

package org.springframework.cloud.servicebroker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.DeleteServiceInstanceBindingRequest;

import static org.junit.jupiter.api.Assertions.assertThrows;

class NonBindableServiceInstanceBindingServiceTest {

	private NonBindableServiceInstanceBindingService service;

	@BeforeEach
	void setUp() {
		service = new NonBindableServiceInstanceBindingService();
	}

	@Test
	void createServiceInstanceBinding() {
		assertThrows(UnsupportedOperationException.class, () ->
				service.createServiceInstanceBinding(CreateServiceInstanceBindingRequest.builder().build())
						.block());
	}

	@Test
	void deleteServiceInstanceBinding() {
		assertThrows(UnsupportedOperationException.class, () ->
				service.deleteServiceInstanceBinding(DeleteServiceInstanceBindingRequest.builder().build())
						.block());
	}

}

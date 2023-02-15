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

package org.springframework.cloud.servicebroker.exception;

import org.junit.jupiter.api.Test;

import org.springframework.cloud.servicebroker.model.error.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThat;

class ServiceInstanceUpdateNotSupportedExceptionTest {

	@Test
	void createServiceInstanceUpdateNotSupportedExceptionWithNullValues() {
		ServiceBrokerException ex = new ServiceInstanceUpdateNotSupportedException(null, null, null, null, null);
		ErrorMessage message = ex.getErrorMessage();

		assertThat(message.getError()).isNull();
		assertThat(message.getMessage()).isEqualTo("Service instance update not supported: null");
		assertThat(message.isInstanceUsable()).isNull();
		assertThat(message.isUpdateRepeatable()).isNull();
		assertThat(ex.getCause()).isNull();
	}

	@Test
	void createServiceInstanceUpdateNotSupportedExceptionWithAllValues() {
		Throwable throwable = new RuntimeException("can't run");
		ServiceBrokerException ex = new ServiceInstanceUpdateNotSupportedException("helloError", "hello", true, false,
				throwable);
		ErrorMessage message = ex.getErrorMessage();

		assertThat(message.getError()).isEqualTo("helloError");
		assertThat(message.getMessage()).isEqualTo("Service instance update not supported: hello");
		assertThat(message.isInstanceUsable()).isTrue();
		assertThat(message.isUpdateRepeatable()).isFalse();
		assertThat(ex.getCause()).isExactlyInstanceOf(RuntimeException.class);
		assertThat(ex.getCause().getMessage()).isEqualTo("can't run");
	}

}

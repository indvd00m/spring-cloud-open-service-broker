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

package org.springframework.cloud.servicebroker.autoconfigure.web.reactive;

import org.junit.jupiter.api.Test;

class BasePathEmptyIntegrationTest extends AbstractBasePathWebApplicationIntegrationTest {

	@Test
	void noBasePathFound() {
		assertFound("", "null");
	}

	@Test
	void noBasePathWithPlatformIdFound() {
		assertFound("/123", "123");
	}

	@Test
	void alternativeDoublePathNotFound() {
		assertNotFound("/api/broker");
	}

	@Test
	void alternativeTriplePathNotFound() {
		assertNotFound("/api/broker/123");
	}

	@Test
	void alternativeQuadruplePathNotFound() {
		assertNotFound("/api/broker/123/456");
	}

}

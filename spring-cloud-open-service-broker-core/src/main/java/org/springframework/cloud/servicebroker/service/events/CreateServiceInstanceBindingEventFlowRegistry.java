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

package org.springframework.cloud.servicebroker.service.events;

import java.util.List;

import reactor.core.publisher.Flux;

import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingRequest;
import org.springframework.cloud.servicebroker.model.binding.CreateServiceInstanceBindingResponse;
import org.springframework.cloud.servicebroker.service.events.flows.CreateServiceInstanceBindingCompletionFlow;
import org.springframework.cloud.servicebroker.service.events.flows.CreateServiceInstanceBindingErrorFlow;
import org.springframework.cloud.servicebroker.service.events.flows.CreateServiceInstanceBindingInitializationFlow;

/**
 * Event flow registry for create service instance binding requests
 *
 * @author Roy Clarkson
 */
public class CreateServiceInstanceBindingEventFlowRegistry
		extends EventFlowRegistry<CreateServiceInstanceBindingInitializationFlow,
		CreateServiceInstanceBindingCompletionFlow, CreateServiceInstanceBindingErrorFlow, CreateServiceInstanceBindingRequest,
		CreateServiceInstanceBindingResponse> {

	/**
	 * Construct a new {@link CreateServiceInstanceBindingEventFlowRegistry}
	 */
	@Deprecated
	public CreateServiceInstanceBindingEventFlowRegistry() {
		super();
	}

	/**
	 * Construct a new {@link CreateServiceInstanceBindingEventFlowRegistry}
	 *
	 * @param initializationFlows the initialization flows
	 * @param completionFlows the completion flows
	 * @param errorFlows the error flows
	 */
	public CreateServiceInstanceBindingEventFlowRegistry(
			final List<CreateServiceInstanceBindingInitializationFlow> initializationFlows,
			final List<CreateServiceInstanceBindingCompletionFlow> completionFlows,
			final List<CreateServiceInstanceBindingErrorFlow> errorFlows) {
		super(initializationFlows, completionFlows, errorFlows);
	}

	@Override
	public Flux<Void> getInitializationFlows(CreateServiceInstanceBindingRequest request) {
		return getInitializationFlowsInternal()
				.flatMap(flow -> flow.initialize(request));
	}

	@Override
	public Flux<Void> getCompletionFlows(CreateServiceInstanceBindingRequest request,
			CreateServiceInstanceBindingResponse response) {
		return getCompletionFlowsInternal()
				.flatMap(flow -> flow.complete(request, response));
	}

	@Override
	public Flux<Void> getErrorFlows(CreateServiceInstanceBindingRequest request, Throwable t) {
		return getErrorFlowsInternal()
				.flatMap(flow -> flow.error(request, t));
	}

}

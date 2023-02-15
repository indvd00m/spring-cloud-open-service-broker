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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Details of a response to a request to create a new service instance binding associated with an application.
 *
 * <p>
 * Objects of this type are constructed by the service broker application, and used to build the response to the
 * platform.
 *
 * @author sgreenberg@pivotal.io
 * @author Josh Long
 * @author Scott Frederick
 * @author Roy Clarkson
 * @see <a href="https://github.com/openservicebrokerapi/servicebroker/blob/master/spec.md#response-6">Open Service
 * 		Broker API specification</a>
 */
public class CreateServiceInstanceAppBindingResponse extends CreateServiceInstanceBindingResponse {

	private final Map<String, Object> credentials;

	private final String syslogDrainUrl;

	private final List<VolumeMount> volumeMounts;

	private final List<Endpoint> endpoints;

	/**
	 * Construct a new {@link CreateServiceInstanceAppBindingResponse}
	 */
	public CreateServiceInstanceAppBindingResponse() {
		this(false, null, BindingStatus.NEW, null, new HashMap<>(), null, new ArrayList<>(), new ArrayList<>());
	}

	/**
	 * Construct a new {@link CreateServiceInstanceAppBindingResponse}
	 *
	 * @param async is the operation asynchronous
	 * @param operation description of the operation being performed
	 * @param bindingStatus does the service binding already exist
	 * @param credentials the service binding credentials
	 * @param syslogDrainUrl the syslog drain URL
	 * @param volumeMounts the set of volume mounts
	 * @param endpoints the set of endpoints
	 */
	public CreateServiceInstanceAppBindingResponse(boolean async, String operation, BindingStatus bindingStatus,
			BindingMetadata metadata, Map<String, Object> credentials, String syslogDrainUrl,
			List<VolumeMount> volumeMounts, List<Endpoint> endpoints) {
		super(async, operation, bindingStatus, metadata);
		this.credentials = credentials;
		this.syslogDrainUrl = syslogDrainUrl;
		this.volumeMounts = volumeMounts;
		this.endpoints = endpoints;
	}

	/**
	 * Construct a new {@link CreateServiceInstanceAppBindingResponse}
	 *
	 * @param async is the operation asynchronous
	 * @param operation description of the operation being performed
	 * @param bindingExisted does the service binding already exist
	 * @param credentials the service binding credentials
	 * @param syslogDrainUrl the syslog drain URL
	 * @param volumeMounts the set of volume mounts
	 * @param endpoints the set of endpoints
	 */
	@Deprecated
	public CreateServiceInstanceAppBindingResponse(boolean async, String operation, boolean bindingExisted,
			BindingMetadata metadata, Map<String, Object> credentials, String syslogDrainUrl,
			List<VolumeMount> volumeMounts, List<Endpoint> endpoints) {
		super(async, operation, bindingExisted, metadata);
		this.credentials = credentials;
		this.syslogDrainUrl = syslogDrainUrl;
		this.volumeMounts = volumeMounts;
		this.endpoints = endpoints;
	}

	/**
	 * Get the credentials that the bound application can use to access the service instance.
	 *
	 * @return the service binding credentials
	 */
	public Map<String, Object> getCredentials() {
		return this.credentials;
	}

	/**
	 * Get the URL to which the platform should drain logs for the bound application.
	 *
	 * @return the syslog drain URL
	 */
	public String getSyslogDrainUrl() {
		return this.syslogDrainUrl;
	}

	/**
	 * Get the set of volume mounts that can be used in an application container file system.
	 *
	 * @return the set of volume mounts
	 */
	public List<VolumeMount> getVolumeMounts() {
		return this.volumeMounts;
	}

	/**
	 * Get the set of endpoints that can be used by an application to connect to the service instance.
	 *
	 * @return the set of endpoints
	 */
	public List<Endpoint> getEndpoints() {
		return this.endpoints;
	}

	/**
	 * Create a builder that provides a fluent API for constructing a {@literal CreateServiceInstanceAppBindingResponse}.
	 *
	 * @return the builder
	 */
	public static CreateServiceInstanceAppBindingResponseBuilder builder() {
		return new CreateServiceInstanceAppBindingResponseBuilder();
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CreateServiceInstanceAppBindingResponse)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		CreateServiceInstanceAppBindingResponse that = (CreateServiceInstanceAppBindingResponse) o;
		return that.canEqual(this) &&
				Objects.equals(credentials, that.credentials) &&
				Objects.equals(syslogDrainUrl, that.syslogDrainUrl) &&
				Objects.equals(volumeMounts, that.volumeMounts) &&
				Objects.equals(endpoints, that.endpoints);
	}

	@Override
	public final boolean canEqual(Object other) {
		return other instanceof CreateServiceInstanceAppBindingResponse;
	}

	@Override
	public final int hashCode() {
		return Objects.hash(super.hashCode(), credentials, syslogDrainUrl, volumeMounts, endpoints);
	}

	@Override
	public String toString() {
		return super.toString() +
				"CreateServiceInstanceAppBindingResponse{" +
				"credentials=" + credentials +
				", syslogDrainUrl='" + syslogDrainUrl + '\'' +
				", volumeMounts=" + volumeMounts +
				", endpoints=" + endpoints +
				'}';
	}

	/**
	 * Provides a fluent API for constructing a {@link CreateServiceInstanceAppBindingResponse}.
	 */
	public static final class CreateServiceInstanceAppBindingResponseBuilder {

		private final Map<String, Object> credentials = new HashMap<>();

		private String syslogDrainUrl;

		private final List<VolumeMount> volumeMounts = new ArrayList<>();

		private final List<Endpoint> endpoints = new ArrayList<>();

		private BindingStatus bindingStatus;

		private BindingMetadata metadata;

		private boolean async;

		private String operation;

		private CreateServiceInstanceAppBindingResponseBuilder() {
		}

		/**
		 * Add a set of credentials from the provided {@literal Map} to the credentials that the bound application can
		 * use to access the service instance.
		 *
		 * <p>
		 * This value will set the {@literal credentials} field in the body of the response to the platform
		 *
		 * @param credentials a {@literal Map} of credentials
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder credentials(Map<String, Object> credentials) {
			this.credentials.putAll(credentials);
			return this;
		}

		/**
		 * Add a key/value pair to the that the bound application can use to access the service instance.
		 *
		 * <p>
		 * This value will set the {@literal credentials} field in the body of the response to the platform
		 *
		 * @param key the credential key
		 * @param value the credential value
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder credentials(String key, Object value) {
			this.credentials.put(key, value);
			return this;
		}

		/**
		 * Set the URL to which the platform should drain logs for the bound application. Can be {@literal null} to
		 * indicate that the service binding does not support syslog drains.
		 *
		 * <p>
		 * This value will set the {@literal syslog_drain_url} field in the body of the response to the platform
		 *
		 * @param syslogDrainUrl the syslog URL
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder syslogDrainUrl(String syslogDrainUrl) {
			this.syslogDrainUrl = syslogDrainUrl;
			return this;
		}

		/**
		 * Add a set of volume mounts from the provided {@literal List} to the volume mounts that can be used in an
		 * application container file system.
		 *
		 * <p>
		 * This value will set the {@literal volume_mounts} field in the body of the response to the platform.
		 *
		 * @param volumeMounts a {@literal List} of volume mounts
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder volumeMounts(List<VolumeMount> volumeMounts) {
			this.volumeMounts.addAll(volumeMounts);
			return this;
		}

		/**
		 * Add a set of volume mounts from the provided array to the volume mounts that can be used in an application
		 * container file system.
		 *
		 * <p>
		 * This value will set the {@literal volume_mounts} field in the body of the response to the platform.
		 *
		 * @param volumeMounts one more volume mounts
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder volumeMounts(VolumeMount... volumeMounts) {
			Collections.addAll(this.volumeMounts, volumeMounts);
			return this;
		}

		/**
		 * Add a set of endpoints from the provided {@literal List} to the endpoints that can be used by an application
		 * to connect to the service instance.
		 *
		 * <p>
		 * This value will set the {@literal endpoints} field in the body of the response to the platform.
		 *
		 * @param endpoints one more endpoints
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder endpoints(List<Endpoint> endpoints) {
			this.endpoints.addAll(endpoints);
			return this;
		}

		/**
		 * Add a set of endpoints from the provided array to the endpoints that can be used by an application to connect
		 * to the service instance.
		 *
		 * <p>
		 * This value will set the {@literal endpoints} field in the body of the response to the platform.
		 *
		 * @param endpoints one more endpoints
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder endpoints(Endpoint... endpoints) {
			Collections.addAll(this.endpoints, endpoints);
			return this;
		}

		/**
		 * Set the binding status indicating whether the service binding already exists with the same
		 * parameters, different parameters, or is a new binding.
		 * <p>
		 * This value will be used to determine the HTTP response code to the platform. A {@literal NEW} value will
		 * result in a response code {@literal 201 CREATED or 202 ACCEPTED} depending on whether it is an async
		 * request or not, a {@literal EXISTS_WITH_IDENTICAL_PARAMETERS} value will result in a response code
		 * {@literal 200 OK}, and a {@literal EXISTS_WITH_DIFFERENT_PARAMETERS} value will result in a response code
		 * {@literal 409 CONFLICT}.
		 *
		 * @param bindingStatus the status indicating whether the request is a new service binding or already exists
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder bindingStatus(BindingStatus bindingStatus) {
			this.bindingStatus = bindingStatus;
			return this;
		}

		/**
		 * Set a boolean value indicating whether the service binding already exists with the same parameters as the
		 * requested service binding. A {@literal true} value indicates a service binding exists and no new resources
		 * were created by the service broker, <code>false</code> indicates that new resources were created.
		 *
		 * <p>
		 * This value will be used to determine the HTTP response code to the platform. A {@literal true} value will
		 * result in a response code {@literal 200 OK}, and a {@literal false} value will result in a response code
		 * {@literal 201 CREATED or 202 ACCEPTED}, depending on whether it is an async request or not.
		 *
		 * @param bindingExisted {@literal true} to indicate that the binding exists, {@literal false} otherwise
		 * @return the builder
		 */
		@Deprecated
		public CreateServiceInstanceAppBindingResponseBuilder bindingExisted(boolean bindingExisted) {
			if (bindingExisted) {
				this.bindingStatus = BindingStatus.EXISTS_WITH_IDENTICAL_PARAMETERS;
			}
			else {
				this.bindingStatus = BindingStatus.NEW;
			}
			return this;
		}

		/**
		 * Set the service instance binding metadata
		 *
		 * <p>
		 * This value will set the {@literal metadata} field in the body of the response to the platform.
		 *
		 * @param metadata metadata about this service binding
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder metadata(BindingMetadata metadata) {
			this.metadata = metadata;
			return this;
		}

		/**
		 * Set a boolean value indicating whether the requested operation is being performed synchronously or
		 * asynchronously.
		 *
		 * <p>
		 * This value will be used to determine the HTTP response code to the platform. A {@literal true} value will
		 * result in a response code {@literal 202 ACCEPTED}; otherwise the response code will be determined by the
		 * value of {@link #bindingExisted(boolean)}.
		 *
		 * @param async {@literal true} to indicate that the operation is being performed asynchronously, {@literal
		 * 		false} to indicate that the operation was completed
		 * @return the builder
		 * @see #bindingExisted(boolean)
		 */
		public CreateServiceInstanceAppBindingResponseBuilder async(boolean async) {
			this.async = async;
			return this;
		}

		/**
		 * Set a value to inform the user of the operation being performed in support of an asynchronous response. This
		 * value will be passed back to the service broker in subsequent {@link GetLastServiceBindingOperationRequest}
		 * requests.
		 *
		 * <p>
		 * This value will set the {@literal operation} field in the body of the response to the platform.
		 *
		 * @param operation description of the operation being performed
		 * @return the builder
		 */
		public CreateServiceInstanceAppBindingResponseBuilder operation(String operation) {
			this.operation = operation;
			return this;
		}

		/**
		 * Construct a {@link CreateServiceInstanceAppBindingResponse} from the provided values.
		 *
		 * @return the newly constructed {@literal CreateServiceInstanceAppBindingResponse}
		 */
		public CreateServiceInstanceAppBindingResponse build() {
			return new CreateServiceInstanceAppBindingResponse(async, operation, bindingStatus, metadata, credentials,
					syslogDrainUrl, volumeMounts, endpoints);
		}

	}

}

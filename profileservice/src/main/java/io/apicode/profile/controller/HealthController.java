package io.apicode.profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthComponent;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.apicode.profile.model.Health;

@RestController
public class HealthController {

	@Autowired
	private HealthEndpoint healthEndpoint;

	@GetMapping(path = "/", produces = { "application/json" })
	public Health serviceCheck() {

		Health health = new Health();

		HealthComponent readinessHealthComponent = healthEndpoint.healthForPath("readiness");
		HealthComponent livenessHealthComponent = healthEndpoint.healthForPath("liveness");

		String service = "Service Profile Service Working correctly";

		health.setLiveness(livenessHealthComponent);
		health.setReadiness(readinessHealthComponent);
		health.setServicedetails(service);

		return health;
	}
}

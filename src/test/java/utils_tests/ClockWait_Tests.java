package utils_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import utils.ProgPause;

class ClockWait_Tests {
	
	@ParameterizedTest
	@ValueSource(ints = {10, 1, 5, 7, 61})
	void test_forSeconds(int sec) {
		Instant start = Instant.now();
		ProgPause.forSeconds(sec);
		Instant end = Instant.now();

		double secs = Math.rint((end.toEpochMilli() - start.toEpochMilli()) / 1000);		
		
		assertEquals(sec, secs);
	}

}
